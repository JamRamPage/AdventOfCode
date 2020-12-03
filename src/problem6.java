import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//323 lines long:
// /3=

public class problem6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        boolean[][] treeMap = parseFile("Day3.text");
        int treeProduct = 0;
        int trees = 0;
        int x = 0;
        int y = 0;
        //3,1
        while (y < 323) {
            while (x < 31) {
                if (y > 322) {
                    break;
                }
                boolean square = treeMap[x][y];
                if (square) {
                    //Is a tree, increment trees
                    trees++;
                }
                y++;x++;x++;x++;
            }
            int offset = x - 30 - 1;
            x=offset;
        }
        treeProduct+=trees;

        trees = 0;
        x = 0;
        y = 0;
        //1,1
        while (y < 323) {
            while (x < 31) {
                if (y > 322) {
                    break;
                }
                boolean square = treeMap[x][y];
                if (square) {
                    //Is a tree, increment trees
                    trees++;
                }
                y++;x++;
            }
            int offset = x - 30 - 1;
            x=offset;
        }
        treeProduct*=trees;

        trees = 0;
        x = 0;
        y = 0;
        //5,1
        while (y < 323) {
            while (x < 31) {
                if (y > 322) {
                    break;
                }
                boolean square = treeMap[x][y];
                if (square) {
                    //Is a tree, increment trees
                    trees++;
                }
                y++;x++;x++;x++;x++;x++;
            }
            int offset = x - 30 - 1;
            x=offset;
        }
        treeProduct*=trees;

        trees = 0;
        x = 0;
        y = 0;
        //7,1
        while (y < 323) {
            while (x < 31) {
                if (y > 322) {
                    break;
                }
                boolean square = treeMap[x][y];
                if (square) {
                    //Is a tree, increment trees
                    trees++;
                }
                y++;x++;x++;x++;x++;x++;x++;x++;
            }
            int offset = x - 30 - 1;
            x=offset;
        }
        treeProduct*=trees;

        trees = 0;
        x = 0;
        y = 0;
        //1,2
        while (y < 323) {
            while (x < 31) {
                if (y > 322) {
                    break;
                }
                boolean square = treeMap[x][y];
                if (square) {
                    //Is a tree, increment trees
                    trees++;
                }
                y++;y++;x++;
            }
            int offset = x - 30 - 1;
            x=offset;
        }
        treeProduct*=trees;
        

		System.out.println(treeProduct);
 	}
	
	public static boolean[][] parseFile(String file) throws IllegalArgumentException {
		// ArrayList holds the data in the file:
        boolean[][] passwords = new boolean[31][323];
        int row = 0;
		BufferedReader buffer = null;
		try {
			FileReader in = new FileReader(file);
			buffer = new BufferedReader(in);
            String nextLine = buffer.readLine();
            // Process line
            for (int x = 0; x < 31; x++) {
                String square = nextLine.substring(x, x + 1);
                if (square.equals(".")) {
                    passwords[x][row] = false;
                } else {
                    passwords[x][row] = true;
                }
            }
			while(nextLine != null) {
                row++;
				nextLine = buffer.readLine();
				if(nextLine != null) {
					for (int x = 0; x < 31; x++) {
                        String square = nextLine.substring(x, x + 1);
                        if (square.equals(".")) {
                            passwords[x][row] = false;
                        } else {
                            passwords[x][row] = true;
                        }
                    }
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