import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class problem23 {

	public static void main(String[] args) {
		List<String> instructions = parseFile("Day12.text");
		int index = 0;
		int x = 0;
		int y = 0;
		// 0 = North, 1 = East, 2 = South, 3 = West
		int facing = 1;
		while (index < instructions.size()) {
			String instruction = instructions.get(index);
			String opcode = instruction.substring(0, 1);
			int amount = Integer.parseInt(instruction.substring(1));
			if (opcode.equals("N")) {
				y+=amount;
			}
			if (opcode.equals("S")) {
				y-=amount;
			}
			if (opcode.equals("E")) {
				x+=amount;
			}
			if (opcode.equals("W")) {
				x-=amount;
			}
			if (opcode.equals("L")) {
				facing-=amount/90;
				if (facing < 0) {
					facing += 4;
				}
			}
			if (opcode.equals("R")) {
				facing+=amount/90;
				if (facing >= 4) {
					facing -= 4;
				}
			}
			if (opcode.equals("F")) {
				if (facing == 0) {
					// Move North
					y+=amount;
				}
				if (facing == 1) {
					// Move East
					x+=amount;
				}
				if (facing == 2) {
					// Move South
					y-=amount;
				}
				if (facing == 3) {
					// Move West
					x-=amount;
				}
			}
			index++;
		}
		System.out.println(Math.abs(x) + Math.abs(y));
	}

	public static List<String> parseFile(String file) throws IllegalArgumentException {
		// Array holds the data in the file:
        List<String> seats = new ArrayList<String>();
		BufferedReader buffer = null;
		try {
			FileReader in = new FileReader(file);
			buffer = new BufferedReader(in);
			int row = 0; int col = 0;
            String nextLine = buffer.readLine();
            // Process line
            seats.add(nextLine);
			row++;col=0;
			while(nextLine != null) {
				nextLine = buffer.readLine();
				// Process line
				if (nextLine != null) {
					seats.add(nextLine);
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
		return seats;
	}
	
}