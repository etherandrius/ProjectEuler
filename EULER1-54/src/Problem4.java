

public class Problem4 {

	public static void main (String[] arg)
	{
			
	Integer _max =0;
	for(int i=999; i>99; i--)
		for(int j=999; j>99; j--)
			if(isPalindrome(i*j))
			{
				_max=Integer.max(_max, i*j);
				break;
			}
		System.out.print(_max);
	
	}
	
	
	public static boolean isPalindrome (int n)
	{
	int length = String.valueOf(n).length();
	for(int i=1; i<Math.floor(length/2)+1; i++)
	if((  (int)((n/Math.pow(10, length-i))%10)      )!=(    (int)((n/Math.pow(10, i-1))%10)     ))
	return false;
	return true;
	}
	
}
