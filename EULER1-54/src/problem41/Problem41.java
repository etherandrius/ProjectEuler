package problem41;

public class Problem41 {

	public static void main(String[] arg) {
		int limit = 10000000;

		boolean[] primes = new boolean[limit];
		primes[0] = true;
		primes[1] = true;

		for (int i = 2; i < limit; i++)
			if (!primes[i])
				for (int j = i + i; j < limit; j += i)
					primes[j] = true;

		int maxI = 0;
		for (int i = 2; i < limit; i++)
			if (!primes[i])
				if (isPandigital(i))
					if (i > maxI)
						maxI = i;

		System.out.println(maxI);

	}

	public static boolean isPandigital(long n)// only works 100% when n is less
												// than 10 digits, otherwise an
												// error might occur
	{
		int limit = largestDigitForPan(n); // limit returns the largest digit
											// that is needed for n to be
											// pandigital
		if (limit == -1)
			return false;

		if (limit < 10)
			return checkDigitsSub10(n, limit);

		int[] digitsReq = countDigitsForPan(limit);
		int[] digitsInN = countDigits(n);

		for (int i = 0; i < 10; i++)
			if (digitsReq[i] != digitsInN[i])
				return false;

		return true;
	}

	private static int[] countDigits(long n) {

		int[] digits = new int[10];

		int length = (int) Math.log10(n) + 1;

		for (int j = 0; j < length; j++)
			digits[(int) (n / Math.pow(10, j)) % 10]++;

		return digits;

	}

	private static int[] countDigitsForPan(int limit) {
		int[] digits = new int[10];

		int sizeI = 10;
		int lengthI = 1;
		for (int i = 1; i <= limit; i++) {
			if (i >= sizeI) {
				sizeI *= 10;
				lengthI++;
			}

			for (int j = 0; j < lengthI; j++)
				digits[(int) (i / Math.pow(10, j)) % 10]++;

		}

		return digits;
	}

	private static boolean checkDigitsSub10(long n, int limit) {
		boolean[] digits = new boolean[limit];
		for (int i = 0; i < limit; i++) {
			int m = (int) (n / Math.pow(10, i)) % 10;

			if (m == 0 || m > limit)
				return false;

			digits[m - 1] = true;
		}

		for (int i = 0; i < limit; i++)
			if (!digits[i])
				return false;

		return true;
	}

	private static int largestDigitForPan(long n)// returns
	{
		n = (int) Math.log10(n) + 1; // n becomes number of digits of n;

		if (n < 10)
			return (int) n;

		int d;
		int digits = 9;
		for (int i = 2;; i++) {
			digits += (int) (9 * Math.pow(10, i - 1)) * i;
			if (n <= digits) {
				digits -= (int) (9 * Math.pow(10, i - 1)) * i;
				d = i;
				break;
			}
		}

		long remainD = n - digits;

		System.out.println(remainD);
		System.out.println(digits);
		System.out.println(d);

		if (remainD % d == 0)
			return (int) Math.pow(10, d - 1) + (int) (remainD - remainD % d) / d - 1;
		else
			return -1;
	}

	public static void printArray(int[] Array) {
		System.out.print("[");
		System.out.print(Array[0]);
		for (int i = 1; i < Array.length; i++)
			System.out.print(", " + Array[i]);
		System.out.println("]");
	}

}
