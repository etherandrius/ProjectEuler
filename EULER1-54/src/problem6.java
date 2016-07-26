import java.util.Scanner;


public class problem6 {

	
	public static void main(String[] arg)
	{
			Scanner scan = new Scanner(System.in);
			int n=0;
			System.out.print(" Enter a non-zero Integer : ");
			while(n==0)
				if(scan.hasNextInt())
					n = scan.nextInt();
				else 
				{
					System.out.println("ENTER AN INTEGER : ");
					scan.nextLine();
				}
			scan.close();
			
		int sumOfSquares = 0;
		for(int i=1; i<=n; i++)
			sumOfSquares += i*i;
		int squareOfSums = 0;
		for(int i=1; i<=n; i++)
			squareOfSums+=i;
			squareOfSums*=squareOfSums;
		
		System.out.println(squareOfSums-sumOfSquares);
			
		
	}
}
