package problems;

import general.methods;

public class Problem52 {

	public static void main(String[] arg) {
		for (int i = 1;; i++) {
			int length = (int) Math.log10(i) + 1;
			int firstDigit = (int) (i / Math.pow(10, length - 1));

			if (firstDigit == 1) {
				int[] array = { i, 2 * i, 3 * i, 4 * i, 5 * i, 6 * i };
				if (methods.arePermutations(array)) {
					
					System.out.println(i);
					methods.printArray(array);
					break;
					
					
				}
			} else
				i = (int) Math.pow(10, length);

		}
	}

}
