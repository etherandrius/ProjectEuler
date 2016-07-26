package problem43;

public class Problem43 {

	public static long sum = 0;

	public static void main(String[] arg) {
		permutate();
		System.out.println(sum);
	}

	public static void permutate() {
		int[] A = new int[10];
		A[0] = 1;
		permutate1(A, 0);

	}

	public static void permutate1(int[] array, int index) {
		if (array[0] == 0)
			return;

		if (index < array.length)
			for (int i = 0; i < array.length; i++) {
				search: {
					for (int j = 0; j < index; j++)
						if (array[j] == i)
							break search;

					array[index] = i;
					permutate1(array, index + 1);
				}
			}
		else {
			if (check(array)) {
				printArray(array);
				sum += arrayToLong(array);
			}
		}

	}

	public static long arrayToLong(int[] A) {
		long result = 0;
		for (int i = 0; i < A.length; i++) {
			result += (long) Math.pow(10, i) * (long) A[A.length - 1 - i];

		}

		return result;
	}

	public static boolean check(int[] A) {
		if (!c1(A[1], A[2], A[3], 2) || !c1(A[2], A[3], A[4], 3) || !c1(A[3], A[4], A[5], 5) || !c1(A[4], A[5], A[6], 7)
				|| !c1(A[5], A[6], A[7], 11) || !c1(A[6], A[7], A[8], 13) || !c1(A[7], A[8], A[9], 17))
			return false;
		return true;
	}

	public static boolean c1(int a, int b, int c, int d) {
		return (100 * a + 10 * b + c) % d == 0;
	}

	public static int[] splitDigits(long n) {
		int[] digits = new int[10];
		for (int i = 0; i < 10; i++)
			digits[10 - 1 - i] = (int) (n / Math.pow(10, i)) % 10;

		return digits;
	}

	// public static boolean isPandigital(long a)
	// {
	// boolean[] digits = new boolean[10];
	//
	// for(int i=0; i<10; i++)
	// {
	// int m = (int)(a/Math.pow(10, i))%10;
	// if(m>0)
	// digits[m]=true;
	// else
	// break;
	// }
	//
	// for(int i=0; i<10; i++)
	// if(!digits[i])
	// return false;
	// return true;
	// }

	public static void printArray(int[] Array) {
		// System.out.print();
		System.out.print(Array[0]);
		for (int i = 1; i < Array.length; i++)
			System.out.print(Array[i]);
		System.out.println();
	}

}
