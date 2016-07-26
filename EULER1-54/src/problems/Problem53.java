package problems;

import java.math.BigInteger;

import general.methods;

public class Problem53 {

	public static void main(String[] arg) {

		int result = 0;
		for (int n = 0; n < 101; n++)
			for (int r = 0; r <= n / 2; r++) {
				if (methods.C(n, r).compareTo(BigInteger.valueOf(1000000)) == 1) {
					
					if (n % 2 == 0)
						--result;
					result += 2 * (n / 2 - r + 1);
					break;
				}

			}

		System.out.println(result);

	}

}
