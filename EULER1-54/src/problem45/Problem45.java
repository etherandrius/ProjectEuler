package problem45;

public class Problem45 {

	public static void main(String[] arg) {
		for (long b = 166;; b++)// 5
		{
			long penta = (3 * b * b - b) / 2;
			if (Math.sqrt(3 * b * b - b + .25) % 1 - 0.5 < 0.1) {
				long hold = (long) Math.sqrt(3 * b * b - b + .25);
				if (hold * (hold + 1) / 2 == penta) {
					long c = (hold + 1) / 2;
					if (c * (2 * c - 1) == penta) {
						System.out.println("a : " + hold);
						System.out.println("c : " + c);
						System.out.println("b : " + b + ", " + penta);
						break;
					}
				}

				// System.out.println("b : " + b + ", P : " +
				// Math.sqrt(3*b*b-b+.25));

			}

		}

	}

}
