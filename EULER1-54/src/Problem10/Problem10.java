package Problem10;

public class Problem10 {

	public static void main(String[] arg)
	{
		int n= 2000000;
		long sum=0;
		boolean[] primes=new boolean[n];
		primes[0]=true;
		for(int i=1; i<n; i++)
			if (primes[i]==false)
			{
				sum+=i+1;
				for(int j=i+i+2; j<=n; j+=i+1)
					primes[j-1]=true;
			}
		System.out.println(sum);
		
	}
	
}
