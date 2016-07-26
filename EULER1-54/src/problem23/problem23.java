package problem23;

public class problem23 {

	public static void main(String[] arg) {
		int[] abunNum = getAbundantNum();
		long sum = 0;
		for (int i = 1; i <= 28123; i++)
			if (!isSumOfAbun(abunNum, i))
				sum += i;
		System.out.println(sum);

	}

	public static boolean isSumOfAbun(int[] abunNum, int num) {
		for (int i = 0; i < 6965; i++)
			for (int j = 0; j < 6965; j++) {
				if (abunNum[i] > num)
					return false;
				if (abunNum[j] + abunNum[i] > num)
					break;
				// System.out.println("i : " + i +", j : " + j);
				if (abunNum[i] + abunNum[j] == num)
					return true;
			}
		return false;
	}

	public static int[] getAbundantNum() {
		int[] abunNum = new int[6965];
		int abunIndex = 0;
		for (int i = 1; i < 28123; i++)
			if (isAbundant(i)) {
				abunNum[abunIndex] = i;
				abunIndex++;
			}
		return abunNum;
	}

	public static boolean isAbundant(int n) {
		int sum = 1;
		for (int i = 2; i < n; i++)
			if (n % i == 0)
				sum += i;

		return sum > n;
	}

}
