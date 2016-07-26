package problem28;

public class problem28 {

	public static void main(String[] arg) {
		int sum = 0;
		int square = 1;
		// generates odd primes;
		for (int i = 1; i <= 1001 * 1001; i += square + 1) {
			if (i % 2 == 1)
				if ((int) Math.sqrt(i) * (int) Math.sqrt(i) == i)
					square = (int) Math.sqrt(i);
			sum += i;
		}
		System.out.println(sum);

	}
}
