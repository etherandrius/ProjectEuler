import java.math.BigInteger;


//sum of all even Fibonacci numbers less than 4million
public class Problem2 {

	
	public static void main(String[] arg)
	{
	BigInteger a = BigInteger.valueOf(1);
	BigInteger b = BigInteger.valueOf(2);
	BigInteger c = BigInteger.valueOf(0);
	BigInteger sum = BigInteger.valueOf(0);
	
	while(b.compareTo(BigInteger.valueOf(4000000))==-1)
	{
		sum=sum.add(b);
		c=a.add(b);
		a=b.add(c);
		b=c.add(a);
	}
	
		System.out.print("Sum : " + sum);
	}
	}
