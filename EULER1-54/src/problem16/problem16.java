package problem16;

public class problem16 {

	public static void main(String[] arg) {
		// one two three four five six seven eight nine
		int[] units = { 3, 3, 5, 4, 4, 3, 5, 5, 4 };
		// -- twenty thirty forty sixty seventy eighty ninety
		int[] tens = { 3, 6, 6, 5, 5, 5, 7, 6, 6 };
		int[] teens = { 6, 6, 8, 8, 7, 7, 9, 8, 8 };
		// hundred 7
		/*
		 * eleven - 6 twelve 6 thirteen 8 fourteen - 8 fifteen 7 sixteen 7
		 * seventeen - 9 eighteen 8 nineteen 8
		 */
		// int sum = 3+3+5+4+4+3+5+5+4+6+6+8+8+7+7+9+8+8+3;//1-19
		// sum+=11;
		int sum = 11;
		System.out.println();
		for (int i = 0; i < 1000; i++) {
			if ((i / 10) % 10 == 1) {
				if (i % 10 == 0)
					sum += 3;
				else
					sum += teens[i % 10 - 1];

			} else {
				if (i % 10 != 0)
					sum += units[i % 10 - 1];
				if ((i / 10) % 10 != 0)
					sum += tens[(i / 10) % 10 - 1];
			}
			if ((i / 100) % 10 != 0)
				sum += units[(i / 100) % 10 - 1] + 7;
			if ((i / 100) % 10 != 0 && ((i / 10) % 10 != 0 || i % 10 != 0))
				sum += 3;
		}

		System.out.println(sum);
	}

}
