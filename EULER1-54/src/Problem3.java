import java.util.Scanner;
import java.util.Vector;



// Prime factors
public class Problem3 {

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
		
		Vector<Integer> primesV = new Vector<Integer>(0, 25);
		
		boolean[] primes=new boolean[n];
		primes[0]=true;
		for(int i=1; i<n; i++)
			if (primes[i]==false)
			{
				primesV.add(i+1);
				for(int j=i+i+2; j<=n; j+=i+1)
					primes[j-1]=true;
			}
		
		
		Vector<Integer> primeFactors = new Vector<Integer>(0, 1);
		for(int i=0; i<primesV.size(); i++)
			if(n%primesV.elementAt(i)==0)
				primeFactors.add(primesV.elementAt(i));
		
		System.out.println(" Largest prime of " + n + " is " + primeFactors.lastElement());
		System.out.println();
		System.out.println("all the prime factors : ");
		System.out.print(primeFactors.elementAt(0));
		for(int i=1; i<primeFactors.size(); i++)
			 System.out.print(",  " + primeFactors.elementAt(i));
			
		
	}
	
}
