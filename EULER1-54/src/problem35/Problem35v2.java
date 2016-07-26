package problem35;

public class Problem35v2 {

	public static void main(String[] arg) {
		int limit = 1000000;
		int sum = 0;

		int length = 1;
		int lengthNum = 10;
		for (int i = 3; i < limit; i += 2) {
			if (i > lengthNum) {
				length++;
				lengthNum *= 10;
			}
			if (isPrime(i))
				if (isCircular(i, length))
					sum++;
		}
		System.out.println(sum);

	}

	public static boolean isCircular(int n, int length) {
		for (int i = 0; i < length - 1; i++) {
			n = rotate(n, length);
			if (!isPrime(n))
				return false;
		}

		return true;
	}

	public static int rotate(int num, int length) {
		int lastD = num % 10;
		num /= 10;
		num += (int) Math.pow(10, length - 1) * lastD;
		return num;
	}

	public static boolean isPrime(int n) {
		if (n == 2)
			return true;
		if (n % 2 == 0)
			return false;
		int limit = (int) Math.sqrt(n);
		for (int i = 4; i < limit; i++)
			if (n % (i - 1) == 0 || n % (i + 1) == 0)
				return false;
		return true;
	}

}
