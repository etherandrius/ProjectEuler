package problem37;

public class Problem37 {

	public static void main(String[] arg) {
		int n = 1000000;
		boolean[] primes = new boolean[n + 1]; // false => prime; true=>
												// non-prime;
		primes[0] = true;
		primes[1] = true;

		for (int i = 2; i < primes.length; i++)
			if (!primes[i])
				for (int j = i + i; j < primes.length; j += i)
					primes[j] = true;

		int sum = 0;

		for (int i = 10; i < primes.length; i++) {
			search: {
				if (!primes[i]) {
					int left = i;
					int right = i;
					int length = (int) (Math.log10(i) + 1);
					for (int j = 0; j < length - 1; j++) {
						left /= 10;
						right = right % (int) Math.pow(10, length - 1 - j);
						if (primes[left] || primes[right])
							break search;

					}
					// System.out.println(i);
					sum += i;
				}
			}

		}

		System.out.println("sum " + sum);

	}

}
