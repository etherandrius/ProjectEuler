package general;

import java.math.BigInteger;
import java.util.Arrays;

public class methods {

	public static BigInteger C(int n, int r) {
		if (r == 0)
			return BigInteger.valueOf(1);
		return (C(n, r - 1).multiply(BigInteger.valueOf(n - r + 1))
				.divide(BigInteger.valueOf(r)));
	}

	public static boolean arePermutations(int[] array)
	// returns true iff array[0] .. array[n] are permutations of each other;
	{
		int length = (int) Math.log10(array[0]) + 1;
		for (int i = array.length - 1; i > 1; i--)
			if (length != (int) Math.log10(array[i]) + 1)
				return false;

		int[] digits = getDigits(array[0], length);

		for (int i = 1; i < array.length; i++) {
			int mockDigits[] = getDigits(array[i], length);
			if (!Arrays.equals(digits, mockDigits))
				return false;
		}
		return true;
	}

	private static int[] getDigits(int n, int length) {
		int[] digits = new int[10];
		for (int i = 0; i < length; i++)
			++digits[(int) (n / Math.pow(10, i) % 10)];
		return digits;
	}

	public static boolean isPrime(int n) {
		if (n == 2)
			return true;
		if (n % 2 == 0)
			return false;
		for (int i = 3; i * i < n; i++)
			if (n % i == 0)
				return false;
		return true;
	}

	public static boolean isPrime(int n, int[] primes) {
		return Arrays.binarySearch(primes, n) >= 0;
	}

	public static boolean isInArraySorted(int n, int[] array) {
		return Arrays.binarySearch(array, n) >= 0;
	}

	public static boolean isInArrayNotSorted(int n, int[] array) {
		Arrays.sort(array);
		return Arrays.binarySearch(array, n) >= 0;
	}

	public static void printArray(int[] Array) {
		System.out.print("[ ");
		System.out.print(Array[0]);
		for (int i = 1; i < Array.length; i++)
			System.out.print(", " + Array[i]);
		System.out.println(" ]");

	}

	public static void printArray(int[][] Array) {
		for (int i = 0; i < Array.length; i++)
			printArray(Array[i]);

	}

	public static void printArray(boolean[] Array) {
		System.out.print("[ ");
		if (Array[0])
			System.out.print("1");
		else
			System.out.print("0");

		for (int i = 1; i < Array.length; i++)
			if (Array[i])
				System.out.print(", 1");
			else
				System.out.print(", 0");
		System.out.println(" ]");

	}

	public static void printArray(boolean[][] Array) {
		for (int i = 0; i < Array.length; i++)
			printArray(Array[i]);
	}

	public static void printArray(String[] Array) {
		System.out.print("[ ");
		System.out.print(Array[0]);
		for (int i = 1; i < Array.length; i++)
			System.out.print(", " + Array[i]);
		System.out.println(" ]");

	}

	public static void printArray(String[][] Array) {
		for (int i = 0; i < Array.length; i++)
			printArray(Array[i]);
	}

}
