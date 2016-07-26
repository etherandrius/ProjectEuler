import java.util.Scanner;


public class Problem9 {

	public static void main(String[] arg)
	{
		System.out.println(" Exploring Pythagora's triplets   a^2+b^2=c^2");
		System.out.println(" choose n, where n=a+b+c holds");
		Scanner scan = new Scanner(System.in);
		int n=0;
		System.out.print(" Enter a non-zero Integer greater than 3: ");
		while(n==0)
			if(scan.hasNextInt())
				n = scan.nextInt();
			else 
			{
				System.out.println("ENTER AN INTEGER : ");
				scan.nextLine();
			}
		scan.close();
		if(n<3)
			return;
		int c=0;
		for(int a=1; a<1000; a++)
			for(int b=1; b<a; b++)
			{
				c=1000-a-b;
				if((c*c)==(a*a+b*b))
				{
					System.out.println("a^2 = " + a*a);
					System.out.println("b^2 = " + b*b);
					System.out.println("c^2 = " + c*c);
					System.out.println("abc = " + a*b*c);
				}
			}
		
		
	}
	
	
}
