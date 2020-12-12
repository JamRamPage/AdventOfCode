import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class problem24 {

	public static void main(String[] args) {
		List<String> instructions = parseFile("Day12.text");
		int index = 0;
		int x = 0;
		int y = 0;
		int xW = 10;
		int yW = 1;
		while (index < instructions.size()) {
			String instruction = instructions.get(index);
			String opcode = instruction.substring(0, 1);
			int amount = Integer.parseInt(instruction.substring(1));
			if (opcode.equals("N")) {
				yW += amount;
			}
			if (opcode.equals("S")) {
				yW-=amount;
			}
			if (opcode.equals("E")) {
				xW+=amount;
			}
			if (opcode.equals("W")) {
				xW-=amount;
			}
			if (opcode.equals("L")) {
				// Rotate waypoint Widdershins	
				//TODO
				int temp = 0;
				if (amount == 90) {
					// x = -x, y = y
					temp = xW;
					xW = yW;
					yW = temp;
					xW = -xW;
				}else if (amount == 180) {
					xW = -xW;
					yW = -yW;
				}else if (amount == 270) {
					temp = xW;
					xW = yW;
					yW = temp;
					yW = -yW;
				}
			}
			if (opcode.equals("R")) {
				// Rotate waypoint clockwise
				//TODO
				int temp = 0;
				if (amount == 90) {
					// x = -x, y = y
					temp = xW;
					xW = yW;
					yW = temp;
					yW = -yW;
				}else if (amount == 180) {
					xW = -xW;
					yW = -yW;
				}else if (amount == 270) {
					temp = xW;
					xW = yW;
					yW = temp;
					xW = -xW;
				}
			}
			if (opcode.equals("F")) {
				// Move ship by waypoint * amount
				x += amount * xW;
				y += amount * yW;
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