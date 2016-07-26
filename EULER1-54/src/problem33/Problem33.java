package problem33;

import java.util.Vector;

public class Problem33 {

	public static void main(String[] arg) {
		Vector<Integer> numerator = new Vector<Integer>(4, 1);
		Vector<Integer> denominator = new Vector<Integer>(4, 1);

		for (int a = 11; a < 100; a++)
			for (int b = a + 1; b < 100; b++) {
				if (a % 10 != 0 && b % 10 != 0) {
					int[] Fraction = new int[2];
					for (int index = 0; index <= 1; index++)
						if (sharedDigit(a, b, index)) {
							Fraction = generateFrac(a, b, index);
							if (a * Fraction[1] == b * Fraction[0]) {
								numerator.add(Fraction[0]);
								denominator.add(Fraction[1]);

							}

						}
				}
			}

		int numeratorI = 1;
		int denominatorI = 1;
		for (int i = 0; i < numerator.size(); i++) {
			numeratorI *= numerator.elementAt(i);
			denominatorI *= denominator.elementAt(i);
		}

		System.out.println(denominatorI / gcd(numeratorI, denominatorI));
	}

	public static int gcd(int a, int b) {
		if (b == 0)
			return a;
		else
			return gcd(b, a % b);
	}

	public static boolean sharedDigit(int a, int b, int index) {
		String A = Integer.toString(a);
		String B = Integer.toString(b);

		if (A.charAt(index) == B.charAt(0) || A.charAt(index) == B.charAt(1))
			return true;
		else
			return false;
	}

	public static int[] generateFrac(int a, int b, int index) {
		int[] Array = new int[2];
		String A = Integer.toString(a);
		String B = Integer.toString(b);
		if (index == 1)
			Array[0] = Character.getNumericValue(A.charAt(0));
		else
			Array[0] = Character.getNumericValue(A.charAt(1));

		if (A.charAt(index) == B.charAt(0))
			Array[1] = Character.getNumericValue(B.charAt(1));
		else
			Array[1] = Character.getNumericValue(B.charAt(0));
		return Array;
	}

}
