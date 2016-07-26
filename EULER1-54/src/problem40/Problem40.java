package problem40;

public class Problem40 {
	public static void main(String[] arg) {
		int A[] = { 1, 10, 100, 1000, 10000, 100000, 1000000 };
		int result = 1;

		for (int i = 0; i < A.length; i++)
			result *= getDigit(A[i]);

		System.out.println(result);

	}

	public static int getDigit(int n) {
		if (n < 10)
			return n;

		int digits = 9; // number of digits before the first number with d
						// digits;
		int d;
		for (int i = 2;; i++) {
			digits += (int) (9 * Math.pow(10, i - 1)) * i;
			if (n <= digits) {
				digits -= (int) (9 * Math.pow(10, i - 1)) * i;
				d = i;
				break;
			}
		}

		int remainD = n - digits;

		return nthDigit((int) Math.pow(10, d - 1) + (remainD - remainD % d) / d, (remainD) % d);

	}

	public static int nthDigit(int num, int n) // returns nth digit of integer
												// num counting from left
	{ // assumes you enter a proper n;
		if (n == 0)
			return num % 10;
		n = (int) Math.log10(num) + 1 - n;
		return ((int) (num / (Math.pow(10, n))) % 10);
	}

}
