package Problem25;

import java.math.BigInteger;

public class problem25 {

	public static void main(String[] arg) {
		BigInteger a = BigInteger.valueOf(1);
		BigInteger b = BigInteger.valueOf(1);
		BigInteger c;
		for (int i = 3;; i++) {
			c = b;
			b = a.add(b);
			if (b.compareTo(BigInteger.valueOf(10).pow(999)) == 1) {
				System.out.print(i);
				break;
			}
			a = c;
		}
	}

}
