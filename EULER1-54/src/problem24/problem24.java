package problem24;

public class problem24 {

	public static long current = 0;

	public static void main(String[] arg) {

		int n = 10;
		int[] perm = new int[n];
		permutate(perm);

	}

	public static void permutate(int[] permutations) {
		permutate1(permutations, 0);
	}

	public static void permutate1(int[] array, int index) {
		if (array.length > index) {
			for (int value = 0; value < array.length; value++) {
				permutate: {
					for (int _index = 0; _index < index; _index++)
						if (value == array[_index])
							break permutate;

					array[index] = value;
					permutate1(array, index + 1);
				}
			}
			return;
		}
		current++;
		if (current == 1000000)
			printArray(array);

	}

	public static void printArray(int[] array) {
		for (int i = 0; i < array.length - 1; i++)
			System.out.print(array[i]);
		System.out.println(array[array.length - 1]);
	}

}
