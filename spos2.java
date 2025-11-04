import java.util.*;

class spos2 {
    static Map<String, Integer> symbolTable = new HashMap<>();
    static Map<String, String> opcodeTable = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initializeOpcodeTable();
        initializeSymbolTable();
        System.out.println("Enter number of lines in intermediate code:");
        int n = Integer.parseInt(sc.nextLine());
        List<String> intermediateCode = new ArrayList<>();
        System.out.println("Enter the intermediate code lines:");
        for (int i = 0; i < n; i++) {
            intermediateCode.add(sc.nextLine().trim());
        }
        System.out.println("\nMachine Code Output:");
        for (String line : intermediateCode) {
            if (line.equals("STOP")) {
                System.out.println("11");
                break;
            }
            String[] parts = line.split("\\s+");
            String instr = parts[0];
            switch (instr) {
                case "START":
                    System.out.println("Initialization at " + parts[1]);
                    break;
                case "READ":
                    String operand = parts[1];
                    String address = getAddress(operand);
                    System.out.println(opcodeTable.get("READ") + " " + address);
                    break;
                case "PRINT":
                    operand = parts[1];
                    address = getAddress(operand);
                    System.out.println(opcodeTable.get("PRINT") + " " + address);
                    break;
                case "MOVEREG":
                    String reg = parts[1].replace(",", "");
                    String value = parts[2].replace("'", "");
                    System.out.println(opcodeTable.get("MOVEREG") + " " + reg + " " + value);
                    break;
                case "ADD":
                case "SUB":
                case "DIV":
                    String reg1 = parts[1].replace(",", "");
                    String reg2 = parts[2];
                    System.out.println(opcodeTable.get(instr) + " " + reg1 + " " + reg2);
                    break;
                case "MOVEM":
                    reg = parts[1].replace(",", "");
                    operand = parts[2];
                    address = getAddress(operand);
                    System.out.println(opcodeTable.get("MOVEM") + " " + reg + " " + address);
                    break;
                case "ADS":
                    System.out.println(opcodeTable.get("ADS") + " 1");
                    break;
                case "BDS":
                    System.out.println(opcodeTable.get("BDS") + " 1");
                    break;
                case "CDS":
                    System.out.println(opcodeTable.get("CDS") + " 1");
                    break;
                default:
                    System.out.println("Unknown instruction: " + instr);
            }
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

    static void initializeSymbolTable() {
        symbolTable.put("A", 200);
        symbolTable.put("B", 201);
        symbolTable.put("C", 202);
    }

    static String getAddress(String operand) {
        if (symbolTable.containsKey(operand)) {
            return String.valueOf(symbolTable.get(operand));
        }
        return "000";
    }
}








// INPUT:-
// Enter number of lines in intermediate code:
// 11
// Enter the intermediate code lines:
// START 200
// READ A
// READ B
// MOVEREG 5, A
// ADD A, B
// MOVEM A, 6
// PRINT A
// ADS
// BDS
// CDS
// STOP










// OUTPUT:-
// Machine Code Output:
// Initialization at 200
// 01 200
// 01 201
// 03 5 A
// 04 A B
// 07 A 000
// 02 200
// 08 1
// 09 1
// 10 1
// 11












