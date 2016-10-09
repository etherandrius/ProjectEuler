package problem104;

import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;

public class Problem104 {
	// find smallest fibonacci number such that first nine digits and last nine digits are pandigial to 1-9
	
	// F_541  first # such that last  9 digits are pandigital to 1-9
	// F_2749 first # such that first 9 digits are pandigital to 1-9
	
	public static int Fp = 1; // previous Fibonacci number 
	public static int F = 1;  // current  Fibonacci number
	public static int n = 2; // # of current F_n	
	
	public static void go_b(){
		int T = F;
		F += Fp;
		F %= 1000000000;
		Fp = T;
		n++;
	}

	public static boolean first9(BigInteger F){
//		 49-58
		if(F.toString().length() < 9) return false;
		TreeMap<Character, Integer> digits = new TreeMap<Character, Integer>();		
		digits.put('0', 0);		
		digits.put('1', 1);
		digits.put('2', 1);
		digits.put('3', 1);
		digits.put('4', 1);
		digits.put('5', 1);
		digits.put('6', 1);
		digits.put('7', 1);
		digits.put('8', 1);
		digits.put('9', 1);
		for(int i = 0; i < 9; i++){
			char index = F.toString().charAt(i);
			digits.put(index, digits.get(index) -1);
		}
		for(Map.Entry<Character, Integer> i : digits.entrySet()){
			if( i.getValue() != 0) return false;
		}
		return true;
	}
	
	public static boolean isP9(int F){
		int[] digits = {1, 1, 1, 1, 1, 1, 1, 1, 1};
		while(F>0){
			int a = F%10;
			if(a == 0) return false;
			digits[a-1]--;
			F/=10;			
		}
		for(int i : digits) if( i != 0) return false;
		return true;
	}
	
	public static boolean F(int n){
		double A = (1 + Math.sqrt(5))/2;
		double Fib = A;
		int counter = 1;
		while( n > 1){
			Fib *=A;
			if(counter%512 == 0){
				Fib /= Math.pow(10, ((int)Math.log10(Fib)) + 1 - 100);
				Fib -= Fib%1;				
				counter = 1;
			}
			counter++;
			n--;
		}		
		Fib /=Math.sqrt(5);
		Fib /= Math.pow(10, ((int)Math.log10(Fib)) - 8);		
		return isP9((int)Fib);
	}
	
	public static void main(String[] agrs){
		
		for(int i = 0; i < 10000; i++){
		while(!isP9(F)) go_b();
		System.out.println("-- " + n + ", " + i);
		// F_b's last 9 digits are pandigital to 1-9
		if(F(n)){
			System.out.println(F + "\t" + n);			
			break;
		}		
		System.out.println(F);
		go_b();
		}
				
	}
	
}




//6 - 2 = 4
//4 2	2
//2 2	5
//1 1	8
//8







