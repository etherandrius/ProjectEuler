package problem38;

public class Problem38 {

	public static void main(String[] arg) {
		int[] multiples = new int[9];
		long concadeMax = 0;
		int iMax = 0;
		int nMax = 0;

		int[] A = {969, 996};
		System.out.println(concade(A, 2));
		A[0]=996;
		A[1]=969;
		System.out.println(concade(A, 2));
		
		
		for (int i = 2; i < 10000; i++) {
			multiples[0] = i;
			int digits = (int) Math.log10(multiples[0]) + 1;
			for (int j = 2; j < 10; j++) {
				multiples[j - 1] = i * j;
				digits += (int) Math.log10(multiples[j - 1]) + 1;
				if (digits == 9) {

					if (isUnique9(multiples, j)) {
						long newConcade = concade(multiples, j);
						// ------

						//System.out.print("i : " + i + ",\t" + newConcade + ",\t");
						printArray(multiples, j);

						// -----------
						if (newConcade > concadeMax) {
							concadeMax = newConcade;
							iMax = i;
							nMax = j;
						}
					}
					break;
				}
				if (digits > 9)
					break;

			}

		}

		System.out.println(concadeMax);
		System.out.println(iMax);
		System.out.println(nMax);

	}

	
	/*
	 * Broken in some cases ex;
	 * {996, 969} gives 996969
	 * {996, 969} gives 969996
	 * 
	 * 996969 > 969996;
	 * 
	 *  Works for this problem since digits are unique
	 */
	public static long concade(int[] A, int n)
	{ 
		long result = 0;
		int currentMaxI = 0;

		int[] firstDigits = new int[n];
		for (int i = 0; i < n; i++)
			if (A[i] > 10)
				firstDigits[i] = A[i] / ((int) Math.pow(10, (int) Math.log10(A[i])));
			else
				firstDigits[i] = A[i];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				if (firstDigits[j] > firstDigits[currentMaxI])
					currentMaxI = j;
				else if (firstDigits[j] == firstDigits[currentMaxI])
					if (digitAverage(A[j]) > digitAverage(A[currentMaxI]))
						currentMaxI = j;
			result *= (int) Math.pow(10, (int) Math.log10(A[currentMaxI]) + 1);
			result += A[currentMaxI];
			firstDigits[currentMaxI] = 0;
		}
		return result;
	}

	public static double digitAverage(int n) {
		int digits = (int) Math.log10(n) + 1;
		double average = 0;
		for (int i = 0; i < digits; i++)
			average += (n / (int) (Math.pow(10, i))) % 10;

		return average / digits;
	}

	public static int getDigits(int[] A, int n) {
		int digits = 0;
		for (int i = 0; i < n; i++)
			digits += (int) Math.log10(A[i]) + 1;

		return digits;
	}

	public static boolean isUnique9(int[] A, int length) {
		boolean[] digits = new boolean[9];

		for (int i = 0; i < length; i++)
			checkDigits(A[i], digits);

		for (int i = 0; i < 9; i++)
			if (!digits[i])
				return false;
		return true;
	}

	public static void checkDigits(int n, boolean[] digits) {
		for (int i = 0; i < Integer.toString(n).length(); i++) {
			int m = (int) (n / Math.pow(10, i)) % 10;
			if (m > 0)
				digits[m - 1] = true;
			else
				break;
		}
	}

	public static void printArray(int[] Array, int n) {
		System.out.print("[");
		System.out.print(Array[0]);
		for (int i = 1; i < n; i++)
			System.out.print(", " + Array[i]);
		System.out.println("]");
	}

}
