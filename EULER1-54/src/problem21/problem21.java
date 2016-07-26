package problem21;

public class problem21 {

	public static void main(String[] arg) {
		int n = 10000;
		boolean[] check = new boolean[n];
		int b;
		int sum = 0;
		for (int a = 0; a < n - 1; a++)
			if (check[a] == false) {
				b = d(a + 1);

				if (a + 1 == b) {
					check[a] = true;
					continue;
				}
				if (a + 1 == d(b)) {
					//System.out.println(a + 1 + ", " + b + ", " + ",  " + d(b) + ", " + d(a + 1));
					check[a] = true;
					check[b - 1] = true;
					sum += b + a + 1;
				}

			}
		System.out.println(sum);
	}

	public static int d(int n) {
		int d = 1;
		for (int i = 2; i < n; i++)
			if (n % i == 0)
				d += i;

		return d;
	}

}
