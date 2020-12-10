import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class problem18 {

	
	public static void main(String[] args) {
		List<BigInteger> numbers = parseFile("Day9.text");
		// 69316178
		BigInteger number = new BigInteger("69316178");
		BigInteger total = new BigInteger("0");
		BigInteger smallNum = new BigInteger("0");
		BigInteger bigNum = new BigInteger("0");
		BigInteger num = new BigInteger("0");
		int start = 0;
		int end = 1;
		boolean found = false;
		while (!found && start < numbers.size()) {
			num = numbers.get(start);
			total = num;
			smallNum = num;
			bigNum = num;
			while (end < numbers.size() && total.compareTo(number) == -1){
				num = numbers.get(end);
				total = total.add(num);
				if (num.compareTo(smallNum) == -1) {
					smallNum = num;
				}
				if (num.compareTo(bigNum) == 1) {
					bigNum = num;
				}
				end++;
			}	
			if (total.compareTo(number) == 0) {
				found = true;
			}
			start++;
			end = start+1;
		}
		System.out.println(smallNum.add(bigNum));
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