package problem46;

public class Problem46 {

	public static void main(String[] arg) {
		// make a sieve
		boolean[] primes = new boolean[1000000];
		primes[0] = true;
		primes[1] = true;
		for (int i = 2; i < primes.length; i++)
			if (!primes[i])
				for (int j = i + i; j < primes.length; j += i)
					primes[j] = true;

		for (int i = 3; i < primes.length; i += 2)
			if (primes[i]) {
				boolean state = false;
				for (int j = 1; 2 * j * j < i; j--)// j is twice a square
				{
					if (!primes[i - 2 * j * j]) {
						state = true;
						break;
					}
				}
				if (state == false) {
					System.out.println(i);
					break;
				}
			}

	}

	public static boolean isPrimeOdd(int n) {
		int limit = (int) Math.sqrt(n);
		for (int i = 4; i <= limit; i += 4)
			if (n % (i - 1) == 0 || n % (i + 1) == 0)
				return false;
		return true;

	}

}
