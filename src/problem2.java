import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class problem2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> numbers = parseFile("problem1.text");
		System.out.println();
		boolean done = false;
		for(int a = 0; a < numbers.size() ; a++) {
			int nA = numbers.get(a);
			for(int b = 0; b < numbers.size() ; b++) {
				int nB = numbers.get(b);
				for (int c = 0; c < numbers.size() ; c++) {
					int nC = numbers.get(c);
					if (nA + nB + nC == 2020) {
						System.out.println(nA);
						System.out.println(nB);
						System.out.println(nC);
						System.out.println(nA * nB * nC);
						done = !done;
						break;
					}
				}
				if(done) {
					break;
				}
			}
			if(done) {
				break;
			}
		}
 	}
	
	public static List<Integer> parseFile(String file) throws IllegalArgumentException {
		// ArrayList holds the data in the file:
		List<Integer> numbers = new ArrayList<Integer>();
		BufferedReader buffer = null;
		try {
			FileReader in = new FileReader(file);
			buffer = new BufferedReader(in);
			String nextLine = buffer.readLine();
			while(nextLine != null) {
				nextLine = buffer.readLine();
				if(nextLine != null) {
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
