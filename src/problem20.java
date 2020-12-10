import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class problem20 {

	public static void main(String[] args) {
		List<Integer> numbers = parseFile("Day10.text");
		Collections.sort(numbers);
		int number = 0;
		int mIndex = 0;
		BigInteger temp = new BigInteger("27453908572908483");
		BigInteger[] M = new BigInteger[numbers.get(numbers.size() - 1) + 1];

		M[0] = new BigInteger("-1");
		M[1] = new BigInteger("1");; // One way of getting to one jolt
		M[2] = new BigInteger("1");; // One way of getting to one jolt
		M[3] = new BigInteger("1");; // One way of getting to one jolt
		for (int x = 1; x <= numbers.get(numbers.size() - 1); x++) {
			if (numbers.contains(x) && x != 1 && x != 2 && x != 3) {
				M[x] = new BigInteger("0");
			} else if (!numbers.contains(x)) {
				// Not in list, M[x] = -1 - we want to skip this
				M[x] = new BigInteger("-1");
			}
		}
		// Initialized M with dummy values, now calculate ways.
		for (int index = 0; index < numbers.size(); index++) {
			number = numbers.get(index);
			mIndex = number;
			temp = M[mIndex];
			while (mIndex > 0 && (number - mIndex) < 3) {
				mIndex--;
				if (M[mIndex].compareTo(BigInteger.valueOf(-1)) != 0) {
					temp = temp.add(M[mIndex]);
				}
			}
			M[number] = temp;
		}
		System.out.println(M[M.length - 1]);
	}

	public static List<Integer> parseFile(String file) throws IllegalArgumentException {
		// ArrayList holds the data in the file:
        List<Integer> numbers = new ArrayList<Integer>();
		BufferedReader buffer = null;
		try {
			FileReader in = new FileReader(file);
            buffer = new BufferedReader(in);
            String nextLine = buffer.readLine();
            // Process line
            numbers.add(Integer.parseInt(nextLine));
			while(nextLine != null) {
				nextLine = buffer.readLine();
				if (nextLine != null) {
					numbers.add(Integer.parseInt(nextLine));
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