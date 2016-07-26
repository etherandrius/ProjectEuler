package problem31;

public class Problem31 {

	public static long sum = 0;

	public static void main(String[] arg) {
		int[] coins = { 1, 2, 5, 10, 20, 50, 100, 200 };

		int[] result = new int[8];
		permute(coins, result, 200, 0);
		System.out.println(sum);
	}

	public static void permute(int[] coins, int[] result, int limit, int index) {
		if (limit == 0) {
			sum++;
			return;
		}

		for (int i = index; i < coins.length; i++)
			if (coins[i] <= limit) {
				result[i]++;
				permute(coins, result, limit - coins[i], i);
				result[i]--;
			}

	}
}
