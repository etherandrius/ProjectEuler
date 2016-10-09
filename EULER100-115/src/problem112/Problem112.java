package problem112;

public class Problem112 {
	
	public static int bouncy = 0;
	public static int total = 99;
	
	public static boolean isBouncy(int number){	
		if( number < 100 ) return false;
		int prev = number%10;
		number /= 10;
		while( prev == number%10){
			prev = number%10;
			number /= 10;
		}
		boolean state = prev > number%10;
		while( number > 9 ){
			prev = number%10;
			number /= 10;
			if( prev != number%10 )
			if( prev > number%10 != state) return true;
		}
		return false;
	}

	public static void main(String[] args){
		
		while( (double)bouncy / ((double)total) < 0.99 ){
			if( isBouncy(++total) )
				bouncy++;
		}
		
		System.out.print(bouncy + "\t");
		System.out.println(total);
		System.out.println((double)bouncy / (double)total);
		
	}

}
