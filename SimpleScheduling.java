// 5.Write a program to simulate CPU Scheduling Algorithms: FCFS(Non preemptive)
// 6.Write a program to simulate CPU Scheduling Algorithms: SJF (Preemptive)
// 7.Write a program to simulate CPU Scheduling Algorithms:  Priority (Non-Preemptive)


import java.util.*;

class Process {
    int id;
    int arrivalTime;
    int burstTime;
    int priority;
    int remainingTime;
    int waitingTime;
    int turnaroundTime;

    Process(int id, int arrivalTime, int burstTime, int priority) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
    }
}

public class SimpleScheduling {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of processes: ");
        int n = sc.nextInt();

        List<Process> processes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Arrival Time of Process " + (i+1) + ": ");
            int at = sc.nextInt();
            System.out.print("Burst Time of Process " + (i+1) + ": ");
            int bt = sc.nextInt();
            System.out.print("Priority of Process " + (i+1) + ": ");
            int pr = sc.nextInt();
            processes.add(new Process(i+1, at, bt, pr));
        }

        System.out.println("\nFCFS Scheduling:");
        fcfs(new ArrayList<>(processes));

        System.out.println("\nSJF (Preemptive) Scheduling:");
        sjfPreemptive(new ArrayList<>(processes));

        System.out.println("\nPriority (Non-Preemptive) Scheduling:");
        priorityNonPreemptive(new ArrayList<>(processes));

        sc.close();
    }

    static void fcfs(List<Process> processes) {
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
        int time = 0;
        for (Process p : processes) {
            if (time < p.arrivalTime)
                time = p.arrivalTime;
            p.waitingTime = time - p.arrivalTime;
            time += p.burstTime;
            p.turnaroundTime = time - p.arrivalTime;
            System.out.println("Process " + p.id + " -> Waiting Time: " + p.waitingTime +
                    ", Turnaround Time: " + p.turnaroundTime);
        }
    }

    static void sjfPreemptive(List<Process> processes) {
        int time = 0, completed = 0;
        int n = processes.size();
        while (completed < n) {
            Process shortest = null;
            for (Process p : processes) {
                if (p.arrivalTime <= time && p.remainingTime > 0) {
                    if (shortest == null || p.remainingTime < shortest.remainingTime) {
                        shortest = p;
                    }
                }
            }
            if (shortest == null) {
                time++;
                continue;
            }
            shortest.remainingTime--;
            time++;
            if (shortest.remainingTime == 0) {
                shortest.turnaroundTime = time - shortest.arrivalTime;
                shortest.waitingTime = shortest.turnaroundTime - shortest.burstTime;
                completed++;
            }
        }
        for (Process p : processes) {
            System.out.println("Process " + p.id + " -> Waiting Time: " + p.waitingTime +
                    ", Turnaround Time: " + p.turnaroundTime);
        }
    }

    static void priorityNonPreemptive(List<Process> processes) {
        int time = 0, completed = 0;
        int n = processes.size();
        while (completed < n) {
            Process highest = null;
            for (Process p : processes) {
                if (p.arrivalTime <= time && p.remainingTime > 0) {
                    if (highest == null || p.priority < highest.priority) {
                        highest = p;
                    }
                }
            }
            if (highest == null) {
                time++;
                continue;
            }
            highest.remainingTime = 0;
            time += highest.burstTime;

            highest.turnaroundTime = time - highest.arrivalTime;
            highest.waitingTime = highest.turnaroundTime - highest.burstTime;
            completed++;
        }
        for (Process p : processes) {
            System.out.println("Process " + p.id + " -> Waiting Time: " + p.waitingTime +
                    ", Turnaround Time: " + p.turnaroundTime);
        }
    }
}


// Input:-
// Enter number of processes: 3
// Arrival Time of Process 1: 0
// Burst Time of Process 1: 5
// Priority of Process 1: 2

// Arrival Time of Process 2: 1
// Burst Time of Process 2: 3
// Priority of Process 2: 1

// Arrival Time of Process 3: 2
// Burst Time of Process 3: 8
// Priority of Process 3: 3

// Output:-
// Process 1 -> Waiting Time: 0, Turnaround Time: 5
// Process 2 -> Waiting Time: 4, Turnaround Time: 7
// Process 3 -> Waiting Time: 6, Turnaround Time: 14
