package problem27;

public class problem27 {

	public static void main(String[] arg) {
		System.out.println(Long.MAX_VALUE);
		long aMax = 0;
		long bMax = 0;
		long maxMax = 0;
		for (int a = -999; a < 1000; a++)
			for (int b = -999; b < 1000; b++)
				if (howManyPrimes(a, b) > maxMax) {
					maxMax = howManyPrimes(a, b);
					aMax = a;
					bMax = b;
				}
		System.out.println(aMax * bMax);
		System.out.println(aMax);
		System.out.println(bMax);
		System.out.println(howManyPrimes(aMax, bMax));

	}

	public static long howManyPrimes(long a, long b)// n^2+an+b
	{
		for (int n = 0;; n++)
			if (!isPrime(n * n + a * n + b))
				return n;

	}

	public static boolean isPrime(long n) {
		if (n < 0)
			return false;
		if (n % 2 == 0)
			return false;
		if (n % 5 == 0)
			return false;

		for (int i = 4; i < Math.sqrt(n); i += 4)
			if (n % (i - 1) == 0 || n % (i + 1) == 0)
				return false;
		return true;
	}

}
