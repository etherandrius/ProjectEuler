package problem47;

public class Problem47 {

	public static void main(String[] arg) {
		long startTime = System.currentTimeMillis();
		int[] primes = getPrimes(1000000);

		for (int i = 645;; i++) {
			if (getNumOfPrimes(i, primes) == 4)
				if (getNumOfPrimes(i + 1, primes) == 4)
					if (getNumOfPrimes(i + 2, primes) == 4)
						if (getNumOfPrimes(i + 3, primes) == 4) {
							System.out.println(i);
							break;

						}

		}

		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime);

	}

	public static int[] getPrimes(int limit) {
		int length = 0;

		boolean[] sieve = new boolean[limit];
		sieve[0] = true;
		sieve[1] = true;
		for (int i = 2; i < limit; i++)
			if (!sieve[i]) {
				length++;
				for (int j = i + i; j < limit; j += i)
					sieve[j] = true;
			}

		int[] primes = new int[length];

		for (int i = 0, index = 0; i < limit; i++)
			if (!sieve[i]) {
				primes[index] = i;
				index++;
			}

		return primes;
	}

	public static int getNumOfPrimes(int n, int[] sieve) {
		int num = 0;
		for (int i = 0; i < sieve.length; i++)
			if (n * n < sieve[i])
				return num;
			else if (n % sieve[i] == 0) {
				num++;
				n /= sieve[i];
			}

		return num;
	}

}
