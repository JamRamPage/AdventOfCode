import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class problem14 {

	public static void main(String[] args) {
		List<String> rules = parseFile("Day7.text");
		int total = 0;
		// Recursively search the lines for "Shiny Gold"
		// Then bags containing that bag's 'first colour'
		// Tag each rule that represents a new bag
		// Until none are found
		total = searchAndCount(rules, "shiny gold", 0);

		
		System.out.println(total);
	}
	 
	public static int searchAndCount(List<String> rules, String searchString, int total) {
		// Search for the rule that starts with the searchString
		boolean foundRule = false;
		int x = 0;
		String rule = "";
		while (x < 594) {
			rule = rules.get(x);
			String outerBag = getOuterBagColour(rule);
			if (outerBag.equals(searchString)) {
				break;
			}
			x++;
		}
		if (x == 594) {
			return total;
		}
		String[] words = rule.split(" ");
		// Base case - if bags is empty
		if (words[4].equals("no")) {
			return 0;
		}
		// Call searchAndCount on the other bags in the rule.
		// Add the bag numbers in the rule.
		List<String> bags = new ArrayList<String>();
		List<Integer> numbers = new ArrayList<Integer>();
		for (int y = 5; y < words.length; y += 4) {
			bags.add(words[y] + " " + words[y + 1]);
			if (isNumeric(words[y - 1])) {
				numbers.add(Integer.parseInt((words[y - 1])));
			}
		}
		for (int z = 0; z < bags.size(); z++) {
			String bag = bags.get(z);
			int number = numbers.get(z);
			total += number + number * searchAndCount(rules, bag, 0);
		}
		return total;
	}

	public static String getOuterBagColour(String rule) {
		String result = "";
		String[] words = rule.split(" ");
		result = words[0] + " " + words[1];
		return result;
	}

	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
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