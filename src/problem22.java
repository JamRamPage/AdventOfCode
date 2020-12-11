import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class problem22 {

	static int rows = 97;
	static int cols = 98;
	static int changes = 0;
	public static void main(String[] args) {
		String[][] numbers = parseFile("Day11.text");
		boolean finished = false;
		boolean temp = true;
		String[][] buffer = new String[rows][cols];
		while(!finished) {
			changes = 0;
			if (temp) {
				// Copy to buffer
				buffer = step(numbers);
			} else {
				// Copy to numbers
				numbers = step(buffer);
			}
			temp = !temp;
			if (changes == 0) {
				// End loop, we're finished
				finished = true;
			}
		}
		int occupied = 0;
		for (int x = 0 ; x < rows; x++) {
			for (int y = 0; y < cols; y++) {
				if (numbers[x][y].equals("#")) {
					occupied++;
				}
			}
		}
		System.out.println(occupied);
	}

	public static String[][] step(String[][] old) {
		String[][] output = new String[rows][cols];
		int i = 0;int j=0;
		for (int x = 0 ; x < rows; x++) {
			for (int y = 0; y < cols; y++) {
				int neighbours = 0;
				// Adjust this to look in each direction
				//UP LEFT:
				try {
					boolean found = false;
					i = x - 1;j = y - 1;
					while (!found) {
						if (old[i][j].equals("L")) {
							break;
						} else if (old[i][j].equals("#")) {
							neighbours++;
							break;
						}
						i--;j--;
					}
				}catch(IndexOutOfBoundsException e) {}

				//UP:
				try {
					boolean found = false;
					i = x - 1;j = y;
					while (!found) {
						if (old[i][j].equals("L")) {
							break;
						} else if (old[i][j].equals("#")) {
							neighbours++;
							break;
						}
						i--;
					}
				}catch(IndexOutOfBoundsException e) {}

				//UP RIGHT:
				try {
					boolean found = false;
					i = x - 1;j = y + 1;
					while (!found) {
						if (old[i][j].equals("L")) {
							break;
						} else if (old[i][j].equals("#")) {
							neighbours++;
							break;
						}
						i--;j++;
					}
				}catch(IndexOutOfBoundsException e) {}

				//RIGHT:
				try {
					boolean found = false;
					i = x;j = y + 1;
					while (!found) {
						if (old[i][j].equals("L")) {
							break;
						} else if (old[i][j].equals("#")) {
							neighbours++;
							break;
						}
						j++;
					}
				}catch(IndexOutOfBoundsException e) {}

				//DOWN RIGHT:
				try {
					boolean found = false;
					i = x + 1;j = y + 1;
					while (!found) {
						if (old[i][j].equals("L")) {
							break;
						} else if (old[i][j].equals("#")) {
							neighbours++;
							break;
						}
						i++;j++;
					}
				}catch(IndexOutOfBoundsException e) {}

				//DOWN:
				try {
					boolean found = false;
					i = x + 1;j = y;
					while (!found) {
						if (old[i][j].equals("L")) {
							break;
						} else if (old[i][j].equals("#")) {
							neighbours++;
							break;
						}
						i++;
					}
				}catch(IndexOutOfBoundsException e) {}

				//DOWN LEFT:
				try {
					boolean found = false;
					i = x + 1;j = y - 1;
					while (!found) {
						if (old[i][j].equals("L")) {
							break;
						} else if (old[i][j].equals("#")) {
							neighbours++;
							break;
						}
						i++;j--;
					}
				}catch(IndexOutOfBoundsException e) {}

				//LEFT:
				try {
					boolean found = false;
					i = x;j = y - 1;
					while (!found) {
						if (old[i][j].equals("L")) {
							break;
						} else if (old[i][j].equals("#")) {
							neighbours++;
							break;
						}
						j--;
					}
				}catch(IndexOutOfBoundsException e) {}
				output[x][y] = newVal(old[x][y], neighbours);
			}
		}
		return output;
	}

	public static String newVal(String prev, int neighbours) {
		if (prev.equals("L") && neighbours == 0) {
			changes++;
			return "#";
		}
		if (prev.equals("#") && neighbours >= 5) {
			changes++;
			return "L";
		}
		return prev;
	}

	public static String[][] parseFile(String file) throws IllegalArgumentException {
		// Array holds the data in the file:
        String[][] seats = new String[rows][cols];
		BufferedReader buffer = null;
		try {
			FileReader in = new FileReader(file);
			buffer = new BufferedReader(in);
			int row = 0; int col = 0;
            String nextLine = buffer.readLine();
            // Process line
            for (char seat : nextLine.toCharArray()) {
				seats[row][col] = String.valueOf(seat);
				col++;
			}
			row++;col=0;
			while(nextLine != null) {
				nextLine = buffer.readLine();
				// Process line
				if (nextLine != null) {
					for (char seat : nextLine.toCharArray()) {
						seats[row][col] = String.valueOf(seat);
						col++;
					}
					row++;col=0;
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