import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class problem17 {

	
	public static void main(String[] args) {
		List<BigInteger> numbers = parseFile("Day9.text");
		for (int i = 25; i < numbers.size(); i++) {
			BigInteger number = numbers.get(i);
			int x = i - 25;
			int y = i - 24;
			boolean found = false;
			while (!found && x < i - 1) {
				while (y < i) {
					BigInteger xN = numbers.get(x);
					BigInteger yN = numbers.get(y);
					if (xN.add(yN).compareTo(number) == 0) {
						found = true;
						break;
					}
					y++;
				}
				if (found) {
					break;
				}
				x++;
				y = x + 1;
			}
			if (!found) {
				System.out.println(numbers.get(i));
				break;
			}
		}
	}

	public static List<BigInteger> parseFile(String file) throws IllegalArgumentException {
		// ArrayList holds the data in the file:
        List<BigInteger> numbers = new ArrayList<BigInteger>();
		BufferedReader buffer = null;
		try {
			FileReader in = new FileReader(file);
            buffer = new BufferedReader(in);
            String nextLine = buffer.readLine();
            // Process line
            numbers.add(new BigInteger(nextLine));
			while(nextLine != null) {
				nextLine = buffer.readLine();
				if (nextLine != null) {
					numbers.add(new BigInteger(nextLine));
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
		return numbers;
	}
	
}