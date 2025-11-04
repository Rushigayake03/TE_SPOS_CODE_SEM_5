// 9.Write a program to simulate Page replacement algorithm.

import java.util.*;

public class PagingSimulation {

    static void lru(int pages[], int capacity) {
        Set<Integer> frames = new HashSet<>();
        Map<Integer, Integer> recent = new HashMap<>();
        int hits = 0;

        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];
            if (frames.contains(page)) {
                hits++;
            } else {
                if (frames.size() < capacity) {
                    frames.add(page);
                } else {
                    // Find least recently used page
                    int lruPage = -1, minIndex = Integer.MAX_VALUE;
                    for (int p : frames) {
                        int lastUsed = recent.getOrDefault(p, -1);
                        if (lastUsed < minIndex) {
                            minIndex = lastUsed;
                            lruPage = p;
                        }
                    }
                    frames.remove(lruPage);
                    frames.add(page);
                }
            }
            recent.put(page, i);
        }
        System.out.println("LRU Page Faults: " + (pages.length - hits));
    }

    static void optimal(int pages[], int capacity) {
        Set<Integer> frames = new HashSet<>();
        int hits = 0;

        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];
            if (frames.contains(page)) {
                hits++;
            } else {
                if (frames.size() < capacity) {
                    frames.add(page);
                } else {
                    int farthest = -1, pageToReplace = -1;
                    for (int p : frames) {
                        int j;
                        for (j = i + 1; j < pages.length; j++) {
                            if (pages[j] == p)
                                break;
                        }
                        if (j > farthest) {
                            farthest = j;
                            pageToReplace = p;
                        }
                    }
                    frames.remove(pageToReplace);
                    frames.add(page);
                }
            }
        }
        System.out.println("Optimal Page Faults: " + (pages.length - hits));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();
        int[] pages = new int[n];

        System.out.println("Enter page reference sequence:");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        System.out.print("Enter frame capacity: ");
        int capacity = sc.nextInt();

        lru(pages, capacity);
        optimal(pages, capacity);

        sc.close();
    }
}

// Input:-
// Enter number of pages: 12
// Enter page reference sequence:
// 1 2 3 4 1 2 5 1 2 3 4 5
// Enter frame capacity: 3

// Output:-
// LRU Page Faults: 9
// Optimal Page Faults: 8



