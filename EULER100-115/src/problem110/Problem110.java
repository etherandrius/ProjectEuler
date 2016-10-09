package problem110;

import java.util.ArrayList;

// works
public class Problem110 {
	//Let  k := # of n^2 divisors less than n;
	// finds smallest n such that k > LIMIT
	
	// finds number that has required number of divisors and improves on it. A greedy algorithm
	// not sure if algorithm works correctly for all inputs.
	
	public static final int LIMIT = 4000000;
	public static ArrayList<Integer> PRIMES;
	
	public static long eval(ArrayList<Integer> powers){
		long result = 1;
		for(int i = 0; i < powers.size(); i++) result *= Math.pow(PRIMES.get(i), powers.get(i));
		return result;
	}

	public static boolean check110(ArrayList<Integer> Array){
		int result = 1;
		for(int it : Array) result *= it*2 + 1;
		return result > LIMIT * 2;
	}
		
	public static boolean genSets(ArrayList<Integer> Array, int result, int limit,
			int start/*inclusive*/, int end/*exclusive*/, int depth/*=start*/){
		
		if( depth >= end ){
			int reset = Array.get(start);			
			while( result * PRIMES.get(start) < limit){
				result *=PRIMES.get(start);
				Array.set(start, Array.get(start) + 1); 
			}
			if( check110(Array) == true ) return true;
			Array.set(start, reset);
			return false;
		}
		else{
			if ( genSets(Array, result, limit, start, end, depth+1) ) return true;
			result *= PRIMES.get(depth);
			int reset = Array.get(depth);
			
			while( result < limit ){
				if ( Array.get(depth) < Array.get(depth - 1) ){
					Array.set(depth, Array.get(depth) + 1);
					if( genSets( Array, result, limit, start, end, depth + 1 ) ) return true;
				}
				result *= PRIMES.get(depth);
			}

			Array.set(depth, reset);
		}
		return false;
	}
	
	public static boolean genSets(ArrayList<Integer> Array, int key, int start/*inclusive*/, int end/*exclusive*/){
		if( key >= start && key < end ) return false;
		int limit = 1;
		int reset = Array.get(key);
		
		while ( ((Array.get(key) > 0)) && ((key > 0) ? (Array.get(key) <= Array.get(key - 1))
				: true) && ((key < Array.size() - 1) ? (Array.get(key + 1) <= Array.get(key)) : true)) {

			Array.set(key, Array.get(key) - 1);
			limit *= PRIMES.get(key);
			if( genSets( Array, 1, limit, start, end, 1  ) ) return true;
		}
				
		Array.set(key,  reset);
		return false;
	}
	
	public static void solve110(){
		
		ArrayList<Integer> powers = new ArrayList<Integer>(PRIMES.size()); // powers of n;
		for(int i = 0; i < PRIMES.size(); i++) powers.add(1);
		
		boolean state = true;
		
		while(state == true){
			state = false;
			int fall = 0;
			for (int i = powers.size() - 1; i > 0; i--)
				if (powers.get(i) > fall) {
					fall = powers.get(i);
					while( genSets(powers, i, 0, i) ) {
					while( genSets(powers, i, 0, i) ); 
						i--;
						state = true;
					}
				}
			
			fall = powers.get(0);
			for(int i = 0; i < powers.size() - 1; i++)
				if( powers.get(i+1) < fall ){
					fall = powers.get(i+1);
					while( genSets(powers, i, i+1, powers.size()) ){
					while( genSets(powers, i, i+1, powers.size()) );
						i++;
						state =true;
					}
				}			
		}
		System.out.println(powers + "\t" + eval(powers));
	}
	
	public static ArrayList<Integer> getPrimes(int number) throws Exception{
		// get approximately number "number" of primes
		int store = number;
		number = 2 * (number+1) * (int)(Math.log(number+1));
		ArrayList<Integer> p = new ArrayList<Integer>();
		boolean[] sieve = new boolean[number];
		for(int i = 2; i < number; i++)
			if( sieve[i] == false){
				p.add(i);
				for(int j = i+i; j < number; j+=i)
					sieve[j] = true;
			}
		if( p.size() > store ) while( p.size() > store+1) p.remove( p.size() - 1);
		if ( p.size() < store ) throw new Exception("Not enought Primes generated"); 
		return p;
	}
	
	public static void main(String[] args) throws Exception {
		int count = 0;
		int power = 1;
		while( power < LIMIT ){
			count++;
			power *= 3;
		}		
		PRIMES = getPrimes(count);
		solve110();
		
	}
}