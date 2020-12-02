import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class problem4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        List<String> passwords = parseFile("problem3.text");
        int valid = 0;
        for (int i = 0 ; i < passwords.size(); i++) {
            String password = passwords.get(i);
            //Yes, i'm that lazy that i'm not going to change these names
            // But crazy enough to write a diatribe about it.
            int lowerBound = 0;
            int higherBound = 1;
            lowerBound = Integer.parseInt(password.substring(0,password.indexOf("-")));
            //System.out.print(lowerBound + " ");
            higherBound = Integer.parseInt(password.substring(password.indexOf("-") + 1, password.indexOf(" ")));
            //System.out.println(higherBound);
            String checkChar = password.substring(password.indexOf(" ") + 1, password.indexOf(":"));
            //System.out.println(checkChar);
            String actualPassword = password.substring(password.indexOf(":") + 2);
            //System.out.println(actualPassword);
            
            int occurrences = 0;
            if (actualPassword.substring(lowerBound - 1, lowerBound).equals(checkChar)) {
                occurrences++;
            }
            if (actualPassword.substring(higherBound - 1, higherBound).equals(checkChar)) {
                occurrences++;
            }

            if (occurrences == 1) {
                valid++;
            }
        }
		System.out.println(valid);
 	}
	
	public static List<String> parseFile(String file) throws IllegalArgumentException {
		// ArrayList holds the data in the file:
		List<String> passwords = new ArrayList<String>();
		BufferedReader buffer = null;
		try {
			FileReader in = new FileReader(file);
			buffer = new BufferedReader(in);
            String nextLine = buffer.readLine();
            passwords.add(nextLine);
			while(nextLine != null) {
				nextLine = buffer.readLine();
				if(nextLine != null) {
					passwords.add(nextLine);
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
		return passwords;
	}
	
}