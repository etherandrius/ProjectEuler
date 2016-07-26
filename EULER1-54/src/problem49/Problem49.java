package problem49;

public class Problem49 {

	public static void main(String[] arg) {
		int limit = 50000;

		boolean[] sieve = new boolean[limit];
		for (int i = 2; i < limit; i++)
			if (!sieve[i])
				for (int j = i + i; j < limit; j += i)
					sieve[j] = true;

		for (int i = 1001; i < limit; i += 2)
			if (!sieve[i])
				for (int j = i + 2; j + j - i < limit; j += 2)
					if (!sieve[j])
						if (!sieve[j + j - i])
							if (permutations(i, j, j + j - i)) {

								System.out.println(i + "\t" + j + "\t" + (j + j - i) + "\t difference  " + (j - i));

							}

	}

	public static boolean permutations(int a, int b, int c) {
		int length = (int) Math.log10(a) + 1;
		int[] digits = new int[length];
		for (int i = 0; i < length; i++)
			digits[i] = (int) (a / Math.pow(10, i)) % 10;

		for (int i = 0; i < length; i++) {
			boolean state1 = false;
			boolean state2 = false;
			for (int j = 0; j < length; j++) {
				// System.out.println("==============");
				if (digits[i] == (int) (b / Math.pow(10, j)) % 10) {
					// System.out.println((int)(b/Math.pow(10, j))%10);
					state1 = true;
				}
				// System.out.println("cxczcxzc");
				if (digits[j] == (int) (b / Math.pow(10, i)) % 10) {
					// System.out.println((int)(b/Math.pow(10, j))%10);
					state2 = true;
				}
			}
			if (state1 == true && state2 == true)
				continue;
			else
				return false;

		}

		for (int i = 0; i < length; i++) {
			boolean state1 = false;
			boolean state2 = false;
			for (int j = 0; j < length; j++) {
				if (digits[i] == (int) (c / Math.pow(10, j)) % 10) {
					state1 = true;
				}
				if (digits[j] == (int) (c / Math.pow(10, i)) % 10) {
					state2 = true;
				}
			}
			if (state1 == true && state2 == true)
				continue;
			else
				return false;

		}

		return true;
	}

}
