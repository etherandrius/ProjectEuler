import java.util.Scanner;
import java.util.Vector;


public class Problem7 {

	public static void main(String[] arg)
	{
		Scanner scan = new Scanner(System.in);
		int n=0;
		System.out.println(" Prints n'th prime ");
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
		
		
		Vector<Integer> primesV = new Vector<Integer>(0, 1);
		int limit = (int)(2*n*Math.log(n));
		boolean[] primes=new boolean[limit];
		primes[0]=true;
		search: {
		for(int i=1;i<limit; i++)
			if (primes[i]==false)
			{
				primesV.add(i+1);
				//System.out.println(primesV.lastElement());
				if(primesV.capacity()==n)
					break search;
				for(int j=i+i+2; j<=limit; j+=i+1)
					primes[j-1]=true;
			}
		}
		System.out.println(" : " + primesV.lastElement());

	}
}
