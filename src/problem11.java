import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//323 lines long:
// /3=

public class problem11 {

	public static void main(String[] args) {
		List<String> groups = parseFile("Day6.text");
		int total = 0;
		for (String group : groups) {
			String[] people = group.split(" ");
			List<String> questionsYessed = new ArrayList<String>();
			for (String person : people) {
				// loop through characters - if not in questionsYessed,
				// Add to questionsYessed and add to total
				for (int x = 0; x < person.length(); x++) {
					String answer = person.substring(x, x + 1);
					if (!questionsYessed.contains(answer)) {
						questionsYessed.add(answer);
					}
				}
			}
			total += questionsYessed.size();
		}
		System.out.println(total);
 	}
	
	public static List<String> parseFile(String file) throws IllegalArgumentException {
		// ArrayList holds the data in the file:
        List<String> groups = new ArrayList<String>();
		BufferedReader buffer = null;
		try {
			FileReader in = new FileReader(file);
            buffer = new BufferedReader(in);
            String group = "";
            String nextLine = buffer.readLine();
            // Process line
            if (!nextLine.equals("")) {
                //Add to current passport
                group += nextLine + " ";
            } else {
                // Start new passport
                groups.add(group);
                group = "";
            }
			while(nextLine != null) {
				nextLine = buffer.readLine();
				if(nextLine != null) {
                    if (!nextLine.equals("")) {
                        //Add to current passport
                        group += nextLine + " ";
                    } else {
                        // Start new passport
                        groups.add(group);
                        group = "";
                    }
				} else {
                    //EOF - add last passport
                    groups.add(group);
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
		return groups;
	}
	
}