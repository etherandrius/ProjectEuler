package problem26;


public class Problem26v2 {

	public static void main(String[] arg)
	{
		int max=0;
		int maxd=0;
		for(int i=2; i<10000; i++)
			if(reacurringDigits(i)>=max)
			{
				max=reacurringDigits(i);
				maxd=i;
			}
		System.out.print(maxd +  ",  ");
		System.out.println(max);		
	}
	
	// solving 10^s mod d = 10^(s+t) mod d
	public static int reacurringDigits(int d)
	{
		int[] powers = new int[d];
		for(int i=0; i<d ;i++)
		{
			powers[i]=pow(10, i, d);
			
		for(int j=0; j<i; j++)
		{
			if(powers[i]==powers[j])
			return (i-j);
		}

		}
		return -1;
		
	}
	
	// Solving a^b mod c
	public static int pow(int a, int b, int c)
	{
			int result=1;
			if(a>c)
				a=a%c;
			
			String bitsS = Integer.toBinaryString(b);
			int[] bits = new int[bitsS.length() - bitsS.replace("1", "").length()];
			int bitsIndex=bits.length-1;
			
			for(int i=0; i<bitsS.length(); i++)
				if(bitsS.charAt(i)=='1')
				{
					bits[bitsIndex]=bitsS.length()-1-i;
					bitsIndex--;
				}
			
			for(int j=0; j<bits.length; j++)
			{
				if(j>0)
				for(int i=0; i<bits[j]-bits[j-1]; i++)
					a=(int)Math.pow(a, 2)%c;
				else 
				for(int i=0; i<bits[j]; i++)
					a=(int)Math.pow(a, 2)%c;
					
				result *=a;
				result%=c;
			}
			return result;
	}	
}



