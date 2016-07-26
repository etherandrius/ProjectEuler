package problem34;

public class Problem34 {

	public static void main(String[] arg) {
		int[] factorials = { 1, 1, 2, 6, 24, 120, 720, 5040, 40320, 3628800 };

		int sum = 0;

		for (int i = 3; i < 999999; i++) {
			int[] digits = getDigits(i);
			int maxD = 0;
			for (int n = 0; n < digits.length; n++)
				maxD = Integer.max(maxD, digits[n]);

			if (i < factorials[maxD])
				continue;
			if (i > factorials[maxD] * digits.length)
				continue;
			if (isCurious(i, digits, factorials))
				sum += i;
		}

		System.out.println(sum);
	}

	public static boolean isCurious(int n, int[] digits, int[] factorials) {
		int sum = 0;
		for (int i = 0; i < digits.length; i++)
			sum += factorials[digits[i]];
		return n == sum;
	}

	public static int[] getDigits(int a) {
		String A = Integer.toString(a);
		int[] array = new int[A.length()];
		for (int i = 0; i < A.length(); i++)
			array[i] = Character.getNumericValue(A.charAt(i));
		return array;
	}
}
