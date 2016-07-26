package problem44;

public class Problem44 {

	public static void main(String[] arg) {

		for (long a = 1; 6 * a - 4 < 5482660; a++)
			for (long b = a - 1; b > 0; b--)
				if (isPentagonal(P(a) - P(b)))
					if (isPentagonal(P(a) + P(b)))
						System.out.println("a : " + P(a) + ", b : " + P(b) + ", D :  " + (P(a) - P(b)));

	}

	public static long P(long n) {
		return (n * (3 * n - 1)) / 2;
	}

	public static boolean isPentagonal(long a) {
		return ((1 + Math.sqrt(24 * a + 1)) / 6) % 1 == 0;
	}

}
