package problem101;

import java.util.ArrayList;

public class problem101 {

	public static ArrayList<Long> u;
	public static ArrayList<Long> un;

	public static void nicePrint(ArrayList<Long> pol) {

		for (int i = 0; i < pol.size(); i++)
			System.out.print(pol.get(i) + "*n^" + i + " ");
		System.out.println();
	}

	public static long Gcd(long a, long b) { // a > 0 && b > 0
		while (a != 0) {
			long c = a;
			a = b % a;
			b = c;
		}
		return b;
	}

	public static long Evaluate(ArrayList<Long> pol, int x) {
		if (pol.size() == 1)
			return pol.get(0);
		long result = pol.get(pol.size() - 1) * x;
		for (int i = pol.size() - 2; i > 0; i--) {
			result += pol.get(i);
			result *= x;
		}
		return result + pol.get(0);
	}

	public static long getElem(int index) {
		if (index < un.size())
			return un.get(index);
		for (int i = un.size(); i <= index; i++)
			un.add(Evaluate(u, i));
		return un.get(index);
	}

	public static void initFunction() {
		u = new ArrayList<Long>(11);
		// change this to your liking;
		u.add(1L);
		u.add(-1L);
		u.add(1L);
		u.add(-1L);
		u.add(1L);
		u.add(-1L);
		u.add(1L);
		u.add(-1L);
		u.add(1L);
		u.add(-1L);
		u.add(1L);
		// u.add(0L); u.add(0L); u.add(0L); u.add(1L);
		un = new ArrayList<Long>(13);
		for (int i = 0; i < u.size() + 2; i++)
			un.add(Evaluate(u, i));
	}

	public static void mulRow(Matrix matrix, int row, int mul) {
		for (int col = 0; col < matrix.colS; col++)
			matrix.m[row][col] *= mul;
	}

	public static void subRows(Matrix matrix, int row, long mul, int rowT, long mulT) {
		for (int col = 0; col < matrix.colS; col++) {
			matrix.m[rowT][col] *= mul;
			matrix.m[rowT][col] -= mulT * matrix.m[row][col];
		}
	}

	public static void reduceCol(Matrix matrix, int row, int col) {

		for (int rowT = 0; rowT < matrix.rowS; rowT++)
			if (matrix.m[rowT][col] < 0)
				mulRow(matrix, row, -1);

		for (int rowT = 0; rowT < matrix.rowS; rowT++)
			if (rowT != row) {
				// matrix.m[row][col] // main row
				// matrix..[rowT][col] .. target row

				if (matrix.m[rowT][col] == 0)
					continue;

				long gcd = Gcd(matrix.m[rowT][col], matrix.m[row][col]);
				long mul = matrix.m[row][col] / gcd;
				long mulT = matrix.m[rowT][col] / gcd;
				subRows(matrix, row, mul, rowT, mulT);

			}
	}

	public static void cleanRow(Matrix matrix, int row) {

		long gcd = Math.abs(matrix.m[row][row]);
		for (int col = 0; col < matrix.colS && gcd != 1; col++)
			if (matrix.m[row][col] != 0)
				gcd = Gcd(gcd, Math.abs(matrix.m[row][col]));

		if (gcd == 1 && matrix.m[row][row] > 0)
			return;
		if (matrix.m[row][row] < 0)
			gcd = -gcd;
		for (int col = 0; col < matrix.colS; col++)
			matrix.m[row][col] /= gcd;

	}

	public static void reducedRowEshalonForm(Matrix matrix) {

		for (int row = 0; row < matrix.rowS; row++) {
			reduceCol(matrix, row, row);
			for (int roww = 0; roww < matrix.rowS; roww++)
				cleanRow(matrix, roww);
		}

	}

	public static ArrayList<Long> findPolynomial(int n) {
		ArrayList<Long> pol = new ArrayList<Long>(n);

		if (n + 1 > un.size())
			return pol;
		Matrix matrix = new Matrix(n, un); // n <= un.side();

		reducedRowEshalonForm(matrix);

		for (int row = 0; row < matrix.rowS; row++)
			pol.add(matrix.m[row][matrix.colS - 1]);
		return pol;
	}

	public static void main(String[] args) {
		initFunction();

		long result = 0;

		ArrayList<Long> pol;
		for (int i = 1; i < u.size(); i++) {
			pol = findPolynomial(i);
			for (int j = i; j < i + 5; j++)
				if (getElem(j) != Evaluate(pol, j)) {
					result += Evaluate(pol, j);
					break;
				}
		}

		System.out.println(result);
	}

}
