package problem14;

public class problem14 {

	public static void main(String[] arg) {

		int longest = 0;
		int terms = 0;
		int i;
		boolean[] table = new boolean[1000001];
		long j;
		for (i = 500000; i <= 1000000; i++)

			if (!table[i]) {
				j = i;
				int this_terms = 1;
				while (j != 1) {
					if (j < table.length)
						table[(int) j] = true;
					this_terms++;
					if (this_terms > terms) {
						terms = this_terms;
						longest = i;
					}
					if (j % 2 == 0) {
						j = j / 2;
					} else {
						j = 3 * j + 1;
					}
				}
			}

		System.out.println(longest + ",   " + terms);

	}

}
	

