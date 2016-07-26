package problem29;

import java.util.Vector;

public class Problem29 {

	public static int sum = 0;

	public static void main(String[] arg) {
		// 99*99 a^b combinations
		// return 99*99-(#ofEqualElements)
		for(int i=0; i<5; i++)
		System.out.println("NOT WORKING");
		calculate(100, 100);
		System.out.println("k " + sum);

	}

	public static void calculate(int a, int b) {
		Vector<Integer> primes = new Vector<Integer>(0, (int) Math.log(a));
		for (int i = 2; i <= a; i++)
			if (isPrime(i))
				primes.add(i);
		//System.out.println(primes);
		int[] powers = new int[primes.size()];
		int limit;
		for (int i = 0;; i++)
			if (Math.pow(2, i + 1) > a) {
				limit = i;
				break;
			}

		calculate1(a, b, powers, 0, limit, primes);
	}

	public static void calculate1(int a, int b, int[] Array, int index, int lim, Vector<Integer> primes) {

		//System.out.print(rPrime(Array) + ", ");
		//printArray(Array);

		if (lim == 0 || index == Array.length - 1) {
			if (rPrime(Array)) {
				if (calculate2(a, 1, Array, primes))
					sum += b;
				for (int i = 2;; i++)
					if (calculate2(a, i, Array, primes))
						sum++;
					else
						return;
			} else
				return;
		}

		for (int i = 0; i <= lim; i++) {
			// System.out.println(index);
			Array[index] = i;
			calculate1(a, b, Array, index + 1, lim - i, primes);
		}

	}

	public static boolean calculate2(int a, int n, int[] Array, Vector<Integer> primes) {
		int number = 1;
		for (int i = 0; i < Array.length; i++)
			number *= Math.pow(primes.elementAt(i), Array[i] * n);
		if (number > a)
			return false;
		return true;
	}

	public static boolean isPrime(int n) {
		if (n == 2)
			return true;
		if (n % 2 == 0)
			return false;
		for (int i = 4; i * i < n; i++)
			if (n % (i - 1) == 0 || n % (i + 1) == 0)
				return false;
		return true;
	}

	public static boolean rPrime(int[] Array)// returns true if greatest common
												// divisor of all elements in
												// Array is 1
	{ // ignores 0's if there are any;
		for (int i = 0; i < Array.length - 1; i++)
			for (int k = i + 1; k < Array.length; k++) {
				if (Array[i] > 1 && Array[k] > 1)
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

	public static void printArray(int[] Array) {
		System.out.print("[");
		System.out.print(Array[0]);
		for (int i = 1; i < Array.length; i++)
			System.out.print(", " + Array[i]);
		System.out.println("]");
	}

}
