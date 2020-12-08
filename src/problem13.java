import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class problem13 {

	public static void main(String[] args) {
		List<String> rules = parseFile("Day7.text");
		boolean[] outerBags = new boolean[rules.size()];
		// Recursively search the lines for "Shiny Gold"
		// Then bags containing that bag's 'first colour'
		// Tag each rule that represents a new bag
		// Until none are found
		search(rules,"shiny gold",outerBags);

		// Finally, go through the boolean array to count the bags
		int number = 0;
		for (boolean b : outerBags) {if (b) {number++;}}number--;
		System.out.println(number);
	}
	 
	public static void search(List<String> rules, String searchString, boolean[] outerBags) {
		// Look for the searchString in each line
		// Call getOuterBagColour with that line 
		//(if result == searchString then stop, this is the end, else recurse)
		// Set outerBags[index] = true
		for (int index = 0; index < rules.size(); index++) {
			String rule = rules.get(index);
			if (rule.contains(searchString)) {
				String outerBag = getOuterBagColour(rule);
				outerBags[index] = true;
				if (!outerBag.equals(searchString)) {
					search(rules, outerBag, outerBags);
				}
			}
		}
	}

	public static String getOuterBagColour(String rule) {
		String result = "";
		String[] words = rule.split(" ");
		result = words[0] + " " + words[1];
		return result;
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