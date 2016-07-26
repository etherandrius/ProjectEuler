
public class Problem1 {

	public static void main(String[] arg)
	{
			long sum=0;
			for(int i=1; i<1000; i++)
				if(i%5==0 || i%3==0)
					sum+=i;
			System.out.print(sum);
		
		
	}
	
}
