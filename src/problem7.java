import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//323 lines long:
// /3=

public class problem7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        List<String> passports = parseFile("Day4.text");
        int valid = 0;
        for (String passport : passports) {
            boolean[] fieldsPresent = new boolean[8];
            List<String> fields = Arrays.asList(passport.split(" "));
            for (String field : fields) {
                String name = field.substring(0,3);
                if (name.equals("byr")) {
                    fieldsPresent[0] = true;
                } else if (name.equals("iyr")) {
                    fieldsPresent[1] = true;
                } else if (name.equals("eyr")) {
                    fieldsPresent[2] = true;
                } else if (name.equals("hgt")) {
                    fieldsPresent[3] = true;
                } else if (name.equals("hcl")) {
                    fieldsPresent[4] = true;
                } else if (name.equals("ecl")) {
                    fieldsPresent[5] = true;
                } else if (name.equals("pid")) {
                    fieldsPresent[6] = true;
                } else if (name.equals("cid")) {
                    fieldsPresent[7] = true;
                }
            }
            boolean validPassport = true;
            for (int x = 0; x < 7 ; x++) {
                if (!fieldsPresent[x]) {
                    validPassport = false;
                    break;
                }
            }
            if (validPassport) {
                valid++;
            }
        }
        System.out.println(valid);
 	}
	
	public static List<String> parseFile(String file) throws IllegalArgumentException {
		// ArrayList holds the data in the file:
        List<String> passports = new ArrayList<String>();
        int row = 0;
		BufferedReader buffer = null;
		try {
			FileReader in = new FileReader(file);
            buffer = new BufferedReader(in);
            String passport = "";
            String nextLine = buffer.readLine();
            // Process line
            if (!nextLine.equals("")) {
                //Add to current passport
                passport += nextLine + " ";
            } else {
                // Start new passport
                passports.add(passport);
                passport = "";
            }
			while(nextLine != null) {
                row++;
				nextLine = buffer.readLine();
				if(nextLine != null) {
                    if (!nextLine.equals("")) {
                        //Add to current passport
                        passport += nextLine + " ";
                    } else {
                        // Start new passport
                        passports.add(passport);
                        passport = "";
                    }
				} else {
                    //EOF - add last passport
                    passports.add(passport);
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
		return passports;
	}
	
}