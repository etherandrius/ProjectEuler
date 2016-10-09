package problem113;

public class Problem113 {	
	
	public static int LEN = 100;
	public static long nonB = 0;
	
	public static long C(int n, int k){
		if(k == 0) return 1;
		return ( C(n, k-1) * (n-k+1) )/k;
	}
		
	public static void main(String[] args){
		
		long temp;
		for(int  k = 1; k < 10; k++){
			temp = 0;
			for(int i = 0; i <= LEN - k; i++ )				
				temp += C(LEN - 1 - i, k - 1) * ( 2 + i );
			nonB += temp * C(9, k);
		}

		nonB -= LEN*9;
		System.out.println(nonB);		

		
	}
	
}

