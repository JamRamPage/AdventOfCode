import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//323 lines long:
// /3=

public class problem8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        List<String> passports = parseFile("Day4.text");
        int valid = 0;
        for (String passport : passports) {
            boolean[] fieldsPresent = new boolean[8];
            List<String> fields = Arrays.asList(passport.split(" "));
            for (String field : fields) {
                String name = field.substring(0,3);
                String value = field.substring(4);
                if (name.equals("byr") && value.length() == 4 && Integer.parseInt(value) >= 1920 && Integer.parseInt(value) <= 2002) {
                    fieldsPresent[0] = true;
                } else if (name.equals("iyr") && value.length() == 4  && Integer.parseInt(value) >= 2010 && Integer.parseInt(value) <= 2020) {
                    fieldsPresent[1] = true;
                } else if (name.equals("eyr") && value.length() == 4  && Integer.parseInt(value) >= 2020 && Integer.parseInt(value) <= 2030) {
                    fieldsPresent[2] = true;
                } else if (name.equals("hgt")) {
                    String unit = value.substring(value.length() - 2, value.length());
                    String number = value.substring(0,value.indexOf(unit));
                    if (unit.equals("cm") && Integer.parseInt(number) >= 150 && Integer.parseInt(number) <= 193) {
                        fieldsPresent[3] = true;
                    }else if (unit.equals("in") && Integer.parseInt(number) >= 59 && Integer.parseInt(number) <= 76) {
                        fieldsPresent[3] = true;
                    }
                } else if (name.equals("hcl")) {
                    if (value.matches("#([0-9]|[a-f]){6}")) {
                        fieldsPresent[4] = true;
                    }
                } else if (name.equals("ecl")) {
                    if (value.equals("amb") || value.equals("blu") || value.equals("brn") || value.equals("gry") || value.equals("grn") || value.equals("hzl") || value.equals("oth")) {
                        fieldsPresent[5] = true;
                    }
                } else if (name.equals("pid")) {
                    if (value.matches("[0-9]{9}")){
                        fieldsPresent[6] = true;    
                    }
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