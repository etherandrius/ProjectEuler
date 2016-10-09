package problem114;

public class Problem114 {
	
	public static long counter = 0;
	public static int M = 3;
	public static int N = 50;
	
	
	public static void F(int m, int n){ // m < n
		
	}

	public static void F_Recursive(int index){
		if(index > N - M )
			counter++;
		else{
			index++;
			F_Recursive(index);  
			for(int i = M; i <= N - index; i++)
				F_Recursive(index + i);
		}
	}

	
	public static void F_Recursive(int m, int n,  int index){
		if(index > n - m )
			counter++;
		else{
			F_Recursive( m, n, index + 1);  
			for(int i = m; i <= n - index; i++)
				F_Recursive(m, n, index + i + 1);
		}
	}

	
	public static void main(String[] args){
		
		F_Recursive(0);
		
		System.out.println(counter);
	}
	
	
	
}
