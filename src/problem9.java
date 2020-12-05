import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//323 lines long:
// /3=

public class problem9 {

	public static void main(String[] args) {
        List<String> seats = parseFile("Day5.text");
		int highestID = 0;
		for (String seat : seats) {
			int lbRow = 0;
			int ubRow = 127;
			int rowNo = 0;
			int colNo = 0;
			for (int x = 0; x < seat.length() - 3; x++) {
				String character = seat.substring(x, x + 1);
				if (character.equals("F")) {
					// Take the lower half
					ubRow = (lbRow + ubRow + 1) / 2;
					rowNo = lbRow;
				} else {
					// Take the upper half
					lbRow = (lbRow + ubRow + 1) / 2;
					rowNo = ubRow;
				}
			}
			int lbCol = 0;
			int ubCol = 7;
			// Now look at the last 3 chars to get the col number
			for (int x = seat.length() - 3; x < seat.length(); x++) {
				String character = seat.substring(x, x + 1);
				if (character.equals("L")) {
					// Take the lower half
					ubCol = (ubCol + lbCol + 1) / 2;
					colNo = lbCol;
				} else {
					// Take the upper half
					lbCol = (ubCol + lbCol + 1) / 2;
					colNo = ubCol;
				}
			}
			// Now work out the seatID
			int seatID = rowNo * 8 + colNo;
			if (seatID > highestID) {
				highestID = seatID;
			}
		}
        System.out.println(highestID);
 	}
	
	public static List<String> parseFile(String file) throws IllegalArgumentException {
		// ArrayList holds the data in the file:
        List<String> seats = new ArrayList<String>();
		BufferedReader buffer = null;
		try {
			FileReader in = new FileReader(file);
            buffer = new BufferedReader(in);
            String nextLine = buffer.readLine();
            // Process line
            seats.add(nextLine);
			while(nextLine != null) {
				nextLine = buffer.readLine();
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