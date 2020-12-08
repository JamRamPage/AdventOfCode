import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class problem16 {

	
	public static void main(String[] args) {
		List<String> instructions = parseFile("Day8.text");
		boolean[] visitedInstructions = new boolean[instructions.size()];
		int accumulator = 0;
		int programCounter = 0;
		while (programCounter < instructions.size()) {
			visitedInstructions[programCounter] = true;
			// get the opcode and operand of the current instruction based on the PC
			String opcode = getOpcode(instructions.get(programCounter));
			String operand = getOperand(instructions.get(programCounter));
			// Execute the appropriate instruction.
			if (opcode.equals("acc")) {
				accumulator = acc(accumulator, operand);
				programCounter++;
			}else if (opcode.equals("jmp")) {
				//Try swapping with a nop and tracing out
				int x = traceProgram(instructions, accumulator, programCounter, visitedInstructions, "nop", operand);
				if (x != 0) {
					// We terminated!
					System.out.println(x);
					break;
				}
				programCounter = jmp(programCounter, operand);
			}else if (opcode.equals("nop")) {
				//Try swapping with a jmp and tracing out
				int x = traceProgram(instructions, accumulator, programCounter, visitedInstructions, "jmp", operand);
				if (x != 0) {
					// We terminated!
					System.out.println(x);
					break;
				}
				nop();
				programCounter++;
			}
		}
	}

	public static int traceProgram(List<String> instructions, int accumulator, int programCounter, boolean[] visitedInstructions, String opcode, String operand) {
		visitedInstructions[programCounter] = false;
		while (programCounter < instructions.size() && !visitedInstructions[programCounter]) {
			visitedInstructions[programCounter] = true;
			// Execute the appropriate instruction.
			if (opcode.equals("acc")) {
				accumulator = acc(accumulator, operand);
				programCounter++;
			}else if (opcode.equals("jmp")) {
				programCounter = jmp(programCounter, operand);
			}else if (opcode.equals("nop")) {
				nop();
				programCounter++;
			}
			// get the opcode and operand of the current instruction based on the PC
			if (programCounter < instructions.size()) {
				opcode = getOpcode(instructions.get(programCounter));
				operand = getOperand(instructions.get(programCounter));
			}
		}
		if (programCounter >= instructions.size()) {
			return accumulator;
		}
		return 0;		
	}

	public static String getOpcode(String instruction) {
		String[] words = instruction.split(" ");
		return words[0];
	}

	public static String getOperand(String instruction) {
		String[] words = instruction.split(" ");
		return words[1];
	}

	public static int acc(int accumulator, String operand) {
		accumulator += Integer.parseInt(operand);
		return accumulator;
	}

	public static int jmp(int programCounter, String operand) {
		programCounter += Integer.parseInt(operand);
		return programCounter;
	}

	public static void nop() {	
	}

	public static List<String> parseFile(String file) throws IllegalArgumentException {
		// ArrayList holds the data in the file:
        List<String> rules = new ArrayList<String>();
		BufferedReader buffer = null;
		try {
			FileReader in = new FileReader(file);
            buffer = new BufferedReader(in);
            String nextLine = buffer.readLine();
            // Process line
            rules.add(nextLine);
			while(nextLine != null) {
				nextLine = buffer.readLine();
				if (nextLine != null) {
					rules.add(nextLine);
				}
			}
			// Handles errors when reading the file:
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find the file");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Cannot read the file");
			e.printStackTrace();
		} finally {
			if (buffer != null) {
				try {
					buffer.close();
				} catch (IOException e) {
					System.out.println("Could not close the file");
					e.printStackTrace();
				}
			}
		}
		return rules;
	}
	
}