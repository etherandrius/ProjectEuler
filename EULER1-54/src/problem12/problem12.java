package problem12;

import java.util.Scanner;
import java.util.Vector;

public class problem12 {
	

	
	public static void main(String[] arg)
	{
		Scanner scan = new Scanner(System.in);
		int n=0;
		System.out.println("Prints smallest number that has exactly n divisors");
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
		
		
		//------------------------		
		Vector<Integer> primesV = new Vector<Integer>(0, (int)Math.log(n));
		boolean[] primes=new boolean[n];
		primes[0]=true;
		for(int i=1; i<n; i++)
			if (primes[i]==false)
			{
				primesV.add(i+1);
				for(int j=i+i+2; j<=n; j+=i+1)
					primes[j-1]=true;
			}
		//------------------------
		int newn=n;
		Vector<Integer> primeFactors = new Vector<Integer>(0, 1);
		for(int i=0; i<primesV.size();)
			if(newn%primesV.elementAt(i)==0)
			{
				newn=newn/primesV.elementAt(i);
				primeFactors.add(primesV.elementAt(i));
				//powers[i]++;
				//System.out.println(" i : " + i + " : " + primesV.elementAt(i) + "^" + powers[i]);
			}	
				else i++;
		int size =primeFactors.size();
		int[] powers = new int[size];
		for(int i=0; i<size; i++)
			powers[i]=primeFactors.elementAt(primeFactors.size()-1-i)-1;
			
		for(int i=0; i<size; i++)
			for(int j=size-1; j>i;j--)
			if(powers[i]!=0 && powers[j]!=0)
			if(Math.pow(primesV.elementAt(i), powers[j]*(powers[i]+1))<Math.pow(primesV.elementAt(j), powers[j]))
			{
				powers[i]=powers[j]*(powers[i]+1);
				powers[j]=0;
				i=0;
				break;
			}
		
		long min= 1;
		for(int i=0; i<primeFactors.size(); i++)
		{
			min*=Math.pow(primesV.elementAt(i), powers[i]);
			System.out.println(" i : " + i + " : " + primesV.elementAt(i) + "^" + powers[i]);
		}
			System.out.println(min);		
			
			
	}
	
	
}
