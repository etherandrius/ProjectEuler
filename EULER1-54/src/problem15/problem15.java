package problem15;

import java.math.BigInteger;

public class problem15 {

	static BigInteger routes = BigInteger.valueOf(0);

	public static void main(String[] arg) {
		int x = 20;
		int y = 20;// x width; y height
		BigInteger[] A = new BigInteger[y + 1];
		for (int i = 0; i <= y; i++)
			A[i] = BigInteger.valueOf(1);
		BigInteger[] B = new BigInteger[y + 1];
		B = A.clone();
		for (int k = 0; k < x - 1; k++) {
			for (int i = 0; i <= y; i++)
				for (int j = 0; j < i; j++) {
					A[i] = A[i].add(B[j]);
				}
			B = A.clone();
		}
		for (int i = 0; i <= y; i++) {
			System.out.println(A[i] + ",      " + B[i]);
			routes = routes.add(A[i]);

		}

		System.out.println(routes);

	}

}
