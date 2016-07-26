import java.util.Scanner;


public class Problem8 {

	public static void main(String[] arg)
	{
		Scanner scan = new Scanner(System.in);
		String n="";
		for(int i=0; i<20; i++)
			n = n.concat(scan.nextLine());
		scan.close();
		long _max=0;
		long c_max;
		int at=0;
		for(int i=1; i<1000-13; i++)
		{
			c_max=1;
			for(int j=i; j<i+13; j++)
				c_max*=Character.getNumericValue(n.charAt(j));
			_max=Long.max(_max, c_max);
			System.out.println(" i : " + i + ",  " + _max + ",  " + c_max);
			if(Long.compare(_max, c_max)==0)
				at = i;
			
		}
		System.out.println(_max);
		System.out.println(at);
	}
	
}
