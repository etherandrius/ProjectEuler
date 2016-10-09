package problem102;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class problem102 {
	
	public static void main(String[] args) throws IOException{
		
		int result = 0;

		Scanner scan = new Scanner(new File("p102_triangles.txt"));
		while(scan.hasNextLine()){
			String line = scan.nextLine();
			Triangle T = new Triangle(line.split(","));
			T.print();
			if(T.containsOrigin()) ++result;
		}		
		scan.close();
		
		System.out.println(result);
		
	}
}
