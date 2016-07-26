package problem36;

public class Problem36 {

	public static void main(String[] arg) {
		long sum = 0;

		for (int i = 1; i < 1000000; i += 2)
			if (isPalindrome(i) && isPalindrome(Integer.toBinaryString(i)))
				sum += i;
		System.out.println(sum);

	}

	public static boolean isPalindrome(int n) {
		int length = (int) (Math.log10(n) + 1);
		for (int i = 1; i < Math.floor(length / 2) + 1; i++)
			if (((int) ((n / Math.pow(10, length - i)) % 10)) != ((int) ((n / Math.pow(10, i - 1)) % 10)))
				return false;
		return true;
	}

	public static boolean isPalindrome(String n) {
		for (int i = 0; i < n.length() / 2; i++)
			if (n.charAt(i) != n.charAt(n.length() - 1 - i))
				return false;
		return true;
	}

}
