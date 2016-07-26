package problems;

public class Problem51 {

	public static int _primes = 0;
	public static long _number = 0;
	public static void main(String[] arg) {

		int limit = 1000000;
		boolean[] sieve = new boolean[limit];
		
		for(int i=2; i<limit; i++)
			if(!sieve[i])
			{
				calculate(i);
				for(int j = i+i; j<limit; j+=i)
					sieve[j]=true;;
			}
		
		
		System.out.println(_primes);
		System.out.println(_number);
	}

	public static void calculate(long number) {

		int length = (int) Math.log10(number) + 1;
		boolean[] state = new boolean[length];
		for (int i = 1; i < length; i++) {
			depth(i, state, number, length - 1);
		}

	}

	public static void isCurious(boolean[] state, long number) {

		int i = 0;

		if (state[state.length - 1])
			i++;


		int numOfPrimes = 0;
		long smallest = 0;
		long mockNumber = number;

		for (int j = 0; j < state.length; j++)
			if (state[j])
				mockNumber -= (int) ((int) (number / Math.pow(10, j) % 10) * Math.pow(10, j));

		
		for (; i < 10; i++) {
			long pPrime = mockNumber; // potential prime
			for (int j = 0; j < state.length; j++) {
				if (state[j]) {
					pPrime += (int) (i * Math.pow(10, j));
				}
			}
			if (isPrime(pPrime)) {
				++numOfPrimes;
				if (smallest == 0)
					smallest = pPrime;
			}
		}

		if (numOfPrimes > _primes) {
			_primes = numOfPrimes;
			_number = smallest;
		}

	}

	public static boolean isPrime(long n) {
		if (n == 2)
			return true;
		if (n % 2 == 0)
			return false;
		for (int i = 3; i * i < n; i += 2)
			if (n % i == 0)
				return false;
		return true;
	}

	public static void depth(int depth, boolean[] state, long number, int limit) {

		if (depth == 0) {
			// printArray(state);
			isCurious(state, number);
			return;
		}

		for (int i = 0; i <= limit; i++)
			if (!state[i]) {
				if (_primes < 8) {

					state[i] = true;
					depth(depth - 1, state, number, i);
					state[i] = false;

				} else
					break;
			}
	}

	public static void printArray(boolean[] Array) {

		System.out.print("[ " + Array[0]);
		for (int i = 1; i < Array.length; i++)
			System.out.print(", " + Array[i]);
		System.out.print(" ]");
		System.out.println();
	}

}
