package problem111v2;

import java.util.ArrayList;

// Not pretty, but instant
public class Problem111v2 {
	
	public static long LIMIT = 10000000000L;
	public static int LEN = 10;

	public static ArrayList<Integer> PRIMES;

	public static int[] maxDigits = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
	public static long[] sums = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	public static ArrayList<Integer> getPrimes(int up) {
		ArrayList<Integer> primes = new ArrayList<Integer>(up / 100);
		boolean[] bool = new boolean[up + 1];
		for (int i = 2; i <= up; i++)
			if (bool[i] == false) {
				primes.add(i);
				for (int j = i + i; j <= up; j += i)
					bool[j] = true;
			}
		primes.trimToSize();
		return primes;
	}

	public static boolean isPrime(long prime) {
		for (int i = 0; i < PRIMES.size() && PRIMES.get(i) <= (int) Math.sqrt(prime); i++)
			if (prime % PRIMES.get(i) == 0)
				return false;
		return true;
	}

	public static void evaluate(int[] val, boolean[] digits, int limit, int r, int depth) {
		if (depth >= val.length) {
			
			long prime = val[0];
			for (int i = 1; i < val.length; i++) {
				prime *= 10;
				prime += val[i];
			}
			
			if (isPrime(prime)) {
				maxDigits[r] = limit;
				sums[r] += prime;
			}
			
		} else {
			if (digits[depth])
				evaluate(val, digits, limit, r, depth + 1);
			else
				for (int i = 0; i < 10; i++)
					if (i != r) {
						val[depth] = i;
						evaluate(val, digits, limit, r, depth + 1);
					}
		}

	}

	public static void linkDigits(boolean[] digits, int target, int limit, int depth) {
		// depth == digits.len - 1
		if (target == 0 || depth < 0) {
			if (target == 0)
				for (int r = 0; r < 10; r++)
					if (maxDigits[r] <= limit) {
						int[] val = new int[digits.length];
						for (int i = 0; i < digits.length; i++)
							if (digits[i])
								val[i] = r;

						if (digits[0]) {
							if (r != 0)
								evaluate(val, digits, limit, r, 1);
						} else
							for (int i = 1; i < 10; i++)
								if (i != r) {
									val[0] = i;
									evaluate(val, digits, limit, r, 1);
								}
					}
		} else {
			if (target > depth + 1)
				return;
			linkDigits(digits, target, limit, depth - 1);
			digits[depth] = true;
			linkDigits(digits, target - 1, limit, depth - 1);
			digits[depth] = false;
		}
	}

	public static void main(String[] args) {

		PRIMES = getPrimes((int) Math.sqrt(LIMIT) + 1);

		for (int i = 10, state = 1; i > 2 && state == 1; i--) {
			linkDigits(new boolean[LEN], i, i, LEN - 1);
			state = 0;
			for (int it : maxDigits)
				if (it < i) {
					state = 1;
					break;
				}
		}

		long sum = 0;

		for (long it : sums)
			sum += it;
		System.out.print(sum);

	}

}
