package problem39;

public class problem39 {

	public static void main(String[] arg) {
		int max = 0;
		int maxI = 0;
		for (int p = 102; p < 1000; p += 2) {
			int currentMax = getNumOfTringles(p);
			if (currentMax > max) {
				max = currentMax;
				maxI = p;
			}
		}
		System.out.println(maxI);

	}

	public static int getNumOfTringles(int p)// p - perimeter
	{
		int result = 0;
		for (int a = 1; a < p; a++)
			for (int b = 1; b < a; b++) {
				int d = a * a + b * b;
				int c = (int) Math.sqrt(d);
				if (c * c != d)
					continue;
				if (a + b + c == p) {
					result++;
					continue;
				}
				if (a + b + c > p)
					break;

			}

		return result;
	}

}
