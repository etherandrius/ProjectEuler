package problem48;

import java.math.BigInteger;

public class Problem48 {

	public static void main(String[] arg) {
		long startTime = System.currentTimeMillis();

		long result = 0;
		for (int i = 1; i < 1001; i++) {
			result += pow(i, i, 10000000000L);
			result %= 10000000000L;
		}
		System.out.println(result);

		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime);

	}

	public static long pow(long a, long b, long c) {
		BigInteger result = BigInteger.valueOf(1);
		if (a > c)
			a %= c;

		String bitsS = Long.toBinaryString(b);
		int[] bits = new int[bitsS.length() - bitsS.replace("1", "").length()];
		int bitsIndex = bits.length - 1;

		for (int i = 0; i < bitsS.length(); i++)
			if (bitsS.charAt(i) == '1') {
				bits[bitsIndex] = bitsS.length() - 1 - i;
				bitsIndex--;
			}

		BigInteger A = BigInteger.valueOf(a);
		BigInteger C = BigInteger.valueOf(c);

		for (int j = 0; j < bits.length; j++) {
			if (j > 0)
				for (int i = 0; i < bits[j] - bits[j - 1]; i++)
					A = (A.pow(2)).mod(C);
			else
				for (int i = 0; i < bits[j]; i++)
					A = (A.pow(2)).mod(C);

			result = result.multiply(A);
			result = result.mod(C);
		}
		return result.longValue();
	}

}
