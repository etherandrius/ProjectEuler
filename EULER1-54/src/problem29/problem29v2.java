package problem29;

import java.util.Vector;

public class problem29v2 {

	public static void main(String[] arg) {
		int sum = 0, a = 100, b = 100;
		Vector<Integer> primes = new Vector<Integer>((int) (a / (Math.log(a))), 5);
		for (int i = 2; i <= a; i++)
			if (isPrime(i))
				primes.add(i);
		int[] powers = new int[primes.size()];
		// ------------------------------
		for (int i = 2; i <= a; i++) {
			getPowers(i, powers, primes);
			if (rPrime(powers)) {
				sum += b - 1;
				int number = getNumber(powers, primes);
				if (number <= 10)
					for (int k = 2;; k++)
						if (Math.pow(number, k) <= a)
							sum += getSum(k, b);
						else
							break;
			}
		}
		System.out.print(sum);

	}

	public static int getSum(int k, int b) {
		int sum = b;
		for (int j = 1; j <= b; j++)
			search: {
				for (int i = 1; i <= b; i++)
					if ((j * k) % i == 0)
						if (j < i)// (j*k)/i<k => j<k
						{
							sum--;
							break search;
						}
			}
		if (sum == b)
			sum--;
		return sum;
	}

	public static int getNumber(int[] powers, Vector<Integer> primes) {
		int num = 1;
		for (int i = 0; i < powers.length; i++)
			num *= Math.pow(primes.elementAt(i), powers[i]);
		return num;
	}

	public static void getPowers(int n, int[] powers, Vector<Integer> primes) {
		for (int i = 0; i < powers.length; i++)
			powers[i] = 0;

		for (int i = 0; i < powers.length;)
			if (n % primes.elementAt(i) == 0) {
				n /= primes.elementAt(i);
				powers[i]++;
			} else
				i++;
	}

	public static boolean rPrime(int[] Array)
	// returns true if greatest common
	// divisor of all elements in
	// Array is 1
	// ignores 0's if there are any;
	{
		int n = 0;
		for (int i = 0; i < Array.length; i++)
			n += Array[i];
		if (n == 1)
			return true;

		for (int i = 0; i < Array.length - 1; i++)
			for (int k = i + 1; k < Array.length; k++) {
				if (Array[i] != 0 && Array[k] != 0)
					if (gcd(Array[i], Array[k]) == 1)
						return true;
			}
		return false;
	}

	public static int gcd(int a, int b) {
		if (b == 0)
			return a;
		else
			return gcd(b, a % b);
	}

	public static boolean isPrime(int n) {
		if (n == 2 || n == 3)
			return true;
		if (n % 2 == 0 || n % 3 == 0)
			return false;
		for (int i = 6; i * i < n; i += 6)
			if (n % (i - 1) == 0 || n % (i + 1) == 0)
				return false;
		return true;
	}

}
