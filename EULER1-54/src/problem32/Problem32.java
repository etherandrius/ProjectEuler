package problem32;

import java.util.Vector;

public class Problem32 {

	public static void main(String[] arg) {

		Vector<Integer> pandigitals = new Vector<Integer>(20, 20);

		for (int a = 1; a < 10000; a++)
			for (int b = (int) (1 + 1000 / a); b < 1 + 9999 / a; b++) {
				search: {
					if (isPandigital(a, b, a * b)) {
						int c = a * b;
						for (int i = 0; i < pandigitals.size(); i++)
							if (pandigitals.elementAt(i) == c)
								break search;

						pandigitals.add(c);
					}
				}
			}
		System.out.println(pandigitals);
		int sum = 0;
		for (int i = 0; i < pandigitals.size(); i++)
			sum += pandigitals.elementAt(i);
		System.out.println(sum);
	}

	public static boolean isPandigital(int a, int b, int c) {
		boolean[] digits = new boolean[9];

		checkDigits(a, digits);
		checkDigits(b, digits);
		checkDigits(c, digits);
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

}
