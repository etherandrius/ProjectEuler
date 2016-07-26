import java.math.BigInteger;
import java.util.Scanner;


public class Problem3_2 {

	public static void main(String[] arg)
	{
		
		//--------Intput-------------------
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter an Integer : ");
		String input = scan.nextLine();
		System.out.println();
		
		BigInteger n=null;
		while(n==null)
		if(input.matches("[0-9]+"))
			n = new BigInteger(input);
		else
		{
			System.out.print("ENTER AN IINNTTEEGGERR : ");
			input = scan.nextLine();
			System.out.println();
			
		}
		
		scan.close();
		//-----input-done--------------------
		for(BigInteger i = BigInteger.valueOf(2); i.compareTo(n)==-1;)
			if(n.mod(i)==BigInteger.valueOf(0))
			{
				n=n.divide(i);
				System.out.println("n : " + n + ",  i : " + i);
			}
			else
			{
				i=i.add(BigInteger.valueOf(1));
				System.out.print(i + ", ");
			}	
				
			System.out.println("Largest Prime Factord of n : "  + n);

	}
	
}
