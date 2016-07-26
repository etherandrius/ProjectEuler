package problem12;

import java.util.Scanner;

public class problem12_2 {

	public static void main(String[] arg)
	{
		
		
		
		System.out.println("First triangle number to have at least n divisors");
		Scanner scan = new Scanner(System.in);
		int n=0;
		System.out.print(" Enter a non-zero Integer n : ");
		while(n==0)
			if(scan.hasNextInt())
				n = scan.nextInt();
			else 
			{
				System.out.println("ENTER AN INTEGER : ");
				scan.nextLine();
			}
		scan.close();
		
		long startTime = System.nanoTime();
		
		
		for(int i=1;;i++)
		{
			if(i%2==0)
			{
			if(d(i/2)*d(i+1)>=n)
			{
				//System.out.println((i*(i+1))/2);
				System.out.println(" i : " + i + " Triangle number : " + (i*(i+1))/2  + "  #d : " +  d(i/2)*d(i+1));
				break;
			}
			}
			else
			if(d(i)*d((i+1)/2)>=n)
			{
				//System.out.println((i*(i+1))/2);
				System.out.println(" i : " + i + " Triangle number : " + (i*(i+1))/2 + "  #d : " +  d(i)*d((i+1)/2));
				break;
			}
		}
		long endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns"); 
		
	}
	// calculates #of divisors
	public static int  d(int n)
	{
		int d=2;
		for(int i=2; i<n; i++)
		if(n%i==0)
		d++;
		return d;
	}
	
	}
