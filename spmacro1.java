import java.util.*;

class spmacro1 {
    static List<String> MDT = new ArrayList<>(); // Macro Definition Table
    static Map<String, Integer> MNT = new LinkedHashMap<>(); // Macro Name Table
    static List<String> intermediateCode = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of lines in the program:");
        int n = Integer.parseInt(sc.nextLine());

        List<String> program = new ArrayList<>();
        System.out.println("Enter the program lines:");
        for (int i = 0; i < n; i++) {
            program.add(sc.nextLine().trim());
        }

        boolean inMacro = false;
        int mdtPointer = 0;

        for (int i = 0; i < n; i++) {
            String line = program.get(i);
            String[] parts = line.split("\\s+");

            if (parts[0].equalsIgnoreCase("MACRO")) {
                inMacro = true;
                String macroName = program.get(i + 1).split("\\s+")[0];
                MNT.put(macroName, mdtPointer);
                continue;
            }

            if (parts[0].equalsIgnoreCase("MEND")) {
                MDT.add("MEND");
                mdtPointer++;
                inMacro = false;
                continue;
            }

            if (inMacro) {
                MDT.add(line);
                mdtPointer++;
            } else {
                intermediateCode.add(line);
            }
        }

        // Display results
        System.out.println("\n=== Macro Name Table (MNT) ===");
        System.out.println("MacroName\tMDT Index");
        for (Map.Entry<String, Integer> e : MNT.entrySet()) {
            System.out.println(e.getKey() + "\t\t" + e.getValue());
        }

        System.out.println("\n=== Macro Definition Table (MDT) ===");
        for (int i = 0; i < MDT.size(); i++) {
            System.out.println(i + "\t" + MDT.get(i));
        }

        System.out.println("\n=== Intermediate Code (without Macro Definitions) ===");
        for (String line : intermediateCode) {
            System.out.println(line);
        }

        sc.close();
    }
}
