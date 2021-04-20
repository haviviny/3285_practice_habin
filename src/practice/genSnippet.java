package practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class genSnippet {
	
	File file = new File("./input.txt");
	Scanner scan = new Scanner(file);
	
	String file_content[] = null;
	
	for (int i = 0; scan.hasNextLine(); i++) {
			file_content[i] = scan.nextLine();
	}
}