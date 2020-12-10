import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class problem19 {

	
	public static void main(String[] args) {
		List<Integer> numbers = parseFile("Day10.text");
		Collections.sort(numbers);
		int voltage = 0;
		int oneJolts = 0;
		int threeJolts = 1;
		int jump = numbers.get(0) - 0;
		voltage++;
		if (jump == 1) {
			oneJolts++;
		}else if (jump == 3) {
			threeJolts++;
		}
		for (int x = 1; x < numbers.size(); x++) {
			jump = numbers.get(x) - voltage;
			voltage += jump;
			if (jump == 1) {
				oneJolts++;
			}else if (jump == 3) {
				threeJolts++;
			}
		}
		System.out.println(oneJolts * threeJolts);
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