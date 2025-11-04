// 1.Design suitable Data strctures and implement Pass-I of a two-pass
// assembler for pseudo-machine. Implementation should consist of a
// few instructions from each category and few assembler directives.



import java.util.*;

class spos1 {
    static Map<String, Integer> symbolTable = new HashMap<>();
    static Map<String, String> opcodeTable = new HashMap<>();
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initializeOpcodeTable();
        System.out.println("Enter number of lines in the program:");
        int n = Integer.parseInt(sc.nextLine());
        List<String> program = new ArrayList<>();
        System.out.println("Enter the program lines:");
        
        for (int i = 0; i < n; i++) {
            program.add(sc.nextLine().trim());
        }

        int locationCounter = 200;
        System.out.println("\nIntermediate Code:");
        
        for (String line : program) {
            if (line.equals("STOP")) {
                System.out.println("STOP");
                break;
            }
            
            String[] parts = line.split("\\s+");
            String instruction = parts[0];

            switch (instruction) {
                case "START":
                    locationCounter = Integer.parseInt(parts[1]);
                    System.out.println("START " + locationCounter);
                    break;

                case "READ":
                case "PRINT":
                    String operand = parts[1];
                    if (!symbolTable.containsKey(operand)) {
                        symbolTable.put(operand, locationCounter++);
                    }
                    System.out.println(instruction + " " + operand);
                    break;

                case "MOVEREG":
                    String reg = parts[1].replace(",", "");
                    String val = parts[2].replace("'", "").replace(",", "");
                    System.out.println("MOVEREG " + reg + ", " + val);
                    break;

                case "ADD":
                case "SUB":
                case "DIV":
                    String reg1 = parts[1].replace(",", "");
                    String reg2 = parts[2];
                    System.out.println(instruction + " " + reg1 + ", " + reg2);
                    break;

                case "MOVEM":
                    reg = parts[1].replace(",", "");
                    String regTarget = parts[2];
                    System.out.println("MOVEM " + reg + ", " + regTarget);
                    break;

                case "ADS":
                case "BDS":
                case "CDS":
                    System.out.println(instruction);
                    break;

                default:
                    System.out.println("Unknown instruction: " + instruction);
            }
        }

        System.out.println("\nSymbol Table:");
        for (Map.Entry<String, Integer> entry : symbolTable.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        
        sc.close();
    }

    static void initializeOpcodeTable() {
        opcodeTable.put("READ", "01");
        opcodeTable.put("PRINT", "02");
        opcodeTable.put("MOVEREG", "03");
        opcodeTable.put("ADD", "04");
        opcodeTable.put("SUB", "05");
        opcodeTable.put("DIV", "06");
        opcodeTable.put("MOVEM", "07");
        opcodeTable.put("ADS", "08");
        opcodeTable.put("BDS", "09");
        opcodeTable.put("CDS", "10");
        opcodeTable.put("STOP", "11");
    }
}


// INPUT:-
// 11
// START 200
// READ A
// READ B
// MOVEREG '5'
// MOVEREG A
// ADD A, B
// MOVEM A, '6'
// PRINT C
// ADS 1
// BDS 1
// CDS 1
// STOP











// OUTPUT:-
// Intermediate Code:
// START 200
// READ A
// READ B
// MOVEREG 5
// MOVEREG A
// ADD A, B
// MOVEM A, 6
// PRINT C
// ADS 1
// BDS 1
// CDS 1
// STOP

// Symbol Table:
// A -> 200
// B -> 201
// C -> 202






