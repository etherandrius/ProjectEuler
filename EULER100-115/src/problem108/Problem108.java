package problem108;

public class Problem108 {
	
	public static int d(long n){
		int result = 1;
		for(long x = n+1; x < 2*n; x++)
			if( x*n % (x-n) == 0 )
				++result; 
		if( result <= 0 ) System.out.println("A\nA\nA\nA\nA\nA\nA\nA\nA\n");
		System.out.print( "d(n) : " + result  + "\tn : " );
		return result;
	}
	
	public static void main(String[] args){
		
		int LIMIT = 1000;
		int n = 180000;
		
		d( 960 );
		
		
//		while( d(n) < LIMIT ){
//		
//			System.out.println(n);
//			n++;
//		}
//		System.out.println(n);
		
	}
	

}
