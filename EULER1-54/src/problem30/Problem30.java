package problem30;

public class Problem30 {

	public static void main(String[] arg) {
		int n = 5;
		int digitSum = 0;

		for (int i = 0; i < Math.pow(9, 5) * 6; i++) {
			for (int j = 0; j < Integer.toString(i).length(); j++) {
				digitSum += (int) Math.pow((int) (i / Math.pow(10, j)) % 10, n);
			}
			if (digitSum == i)
				System.out.println(i);
			digitSum = 0;
		}

	}

}
