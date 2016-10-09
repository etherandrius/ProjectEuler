package problem111;

import java.util.ArrayList;

// Quite a dumb approach to problem111 from ProjetEuler

//correct but takes 5 minutes
// All primes are 100% primes did not use Probabilistic primality test
// uses Sieve AND TAKES UP 3GB OF RAM hooray!!!!!!
public class Problem111 {
	
	public static long LIMIT_UP  = 10000000000L;  // 10^10
	public static long LIMIT_DOWN = 1000000000L; // 10^9
	
	public static int[] maxDigits = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
	public static long[] sums 	  = new long[10];	
	public static int[] N = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	
	public static ArrayList<Integer> getPrimes(int up){
		ArrayList<Integer> primes = new ArrayList<Integer>(up / 100);
		boolean[] bool = new boolean[up + 1];		
		for(int i = 2; i <= up; i++)
			if( bool[i] == false){
				primes.add(i);
				for(int j = i+i; j <= up; j += i)
					bool[j] = true;
			}
		primes.trimToSize();
		return primes;
	}
	
	public static void clearNonPrimes(ArrayList<Integer> primes, boolean[] isP, long counter){
		for(int it : primes )
			for(int i = 0; i < isP.length; i++)
					if( (i+counter)% it == 0 ){
						while( i < isP.length){
							isP[i] = true;
							i += it;
						}
						break;
					}
	}
	
	public static void Decompose(long prime){
		int digits[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		long fake = prime;
		while(fake > 0){
			digits[(int)(fake%10)]++;
			fake /= 10;
		}
		
		for(int i = 0; i < 10; i++)
			if( digits[i] == maxDigits[i] ) {
				sums[i] += prime;
				N[i]++;
			}
			else if( digits[i] > maxDigits[i]){
				maxDigits[i] = digits[i];
				sums[i] = prime;
				N[i] = 1;
			}
		
	}
	
	public static void main(String[] args){
				
		ArrayList<Integer> primes = getPrimes((int)Math.sqrt(LIMIT_UP) + 1);
		int cell = 50000000; // 10 ^ 8
		boolean[] isP;
		long counter = LIMIT_DOWN; // always even
		while( counter <  LIMIT_UP){
		isP = new boolean[cell];
		
		System.out.print(counter + ".");
		
		clearNonPrimes(primes, isP, counter);
		
		System.out.print("......");
		
		for(int i = 0; i < isP.length; i++)
			if( isP[i] == false)
				Decompose(i + counter);
				
		counter += cell;
		System.out.println("..Done");
		}
		
		System.out.println();
		System.out.println();
		System.out.println();		
		System.out.println();
		
		long sum = 0;
		
		for(long it : sums ) System.out.print(it + " ");
		System.out.println();
		for(int it : maxDigits ) System.out.print(it + " ");
		System.out.println();
		for(int it : N ) System.out.print(it + " ");
		System.out.println();
		
//		long sum = 0;
		
		for(long it : sums ) sum += it;
		System.out.print(sum);
		
		
		
		
	}
	

}


//
//38000000042 12882626601 97447914665 23234122821 4444444447 5555555557 6666666661 59950904793 285769942206 78455389922 
//8 9 8 9 9 9 9 9 8 9 
//8 11 39 7 1 1 1 9 32 8 
//612407567715

