package problem13;

import java.io.File;
import java.math.BigInteger;
import java.util.Scanner;

public class problem13 {

	public static void main(String[] arg) throws Exception
	{
		Scanner input = new Scanner(new File("Problem13file.txt"));
		BigInteger[] grid =new BigInteger[100];
		for(int i=0; i<100; i++)
		{
			grid[i] = input.nextBigInteger();
			
		}
		BigInteger sum =BigInteger.valueOf(0);
		for(int i=0; i<100; i++)
			sum=sum.add(grid[i]);
		System.out.println(sum);
		input.close();
		
	}
	
}
