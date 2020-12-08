import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class problem16 {

	public static void main(String[] args) {
		List<String> rules = parseFile("Day8.text");
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