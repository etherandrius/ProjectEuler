package problem50;

public class Problem50v2 {

	public static void main(String[] arg) {
		int limit = 1000000;
		int[] primes = getPrimes50(limit);
		long[] cdf = getCDF(primes);// cdf[i+1]= cdf[i] + function[i];
		// last element of cdf is at cdf[primes.length];
		// cdf[i] = f[i-1] + f[i-2] + ... + f[1] + f[0];

		search: {
			for (int i = 547; i > 0; i--)
				for (int j = 0;; j++) {
					int currentSum = (int) (cdf[i + j] - cdf[j]);

					if (i + j > primes.length)
						break;
					if (currentSum > limit)
						break;

					if (isPrime(currentSum, primes)) {
						System.out.println(currentSum);
						System.out.println("i+j : " + (i + j) + ",  " + primes[i + j - 1]);
						System.out.println("j : " + j + ",  " + primes[j]);
						System.out.println("primes from " + primes[j] + " up to " + primes[i + j - 1] + " are used");
						break search;
					}

				}
		}

	}

	public static long[] getCDF(int[] f) {
		long[] CDF = new long[f.length + 1];
		CDF[0] = 0;
		for (int i = 0; i < f.length; i++)
			CDF[i + 1] += CDF[i] + f[i];
		return CDF;
	}

	public static int[] getPrimes50(int limit) {
		boolean[] sieve = new boolean[limit];
		int index = 0;
		for (int i = 2; i < limit; i++)
			if (!sieve[i]) {
				index++;
				for (int j = i + i; j < limit; j += i)
					sieve[j] = true;
			}
		int[] primes = new int[index];
		index = 0;
		for (int i = 2; i < limit; i++)
			if (!sieve[i]) {
				primes[index] = i;
				index++;
			}
		return primes;
	}

	public static boolean isPrime(int n, int[] primes)// true only if
														// primes.lastelement>n;
	{
		for (int i = 0; i < primes.length; i++)
			if (primes[i] > n)
				return false;
			else if (primes[i] == n)
				return true;
		return false;
	}

}
