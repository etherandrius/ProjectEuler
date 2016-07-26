package problem22;

import java.io.File;
import java.util.Scanner;

public class problem22 {

	public static void main(String[] arg) throws Exception {
		String[] names = input();
		names = mergeSort(names);

		long totalScore = 0;
		for (int i = 0; i < names.length; i++)
			totalScore += score(names[i]) * (i + 1);
		System.out.print(totalScore);

	}

	public static String[] input() throws Exception {
		Scanner scan = new Scanner(new File("problem22file"));
		String line = scan.nextLine();
		scan = new Scanner(new File("problem22file"));
		scan.close();
		line = line.replace("\"", "");

		String[] names = line.split(",");

		return names;
	}

	// [BAD MERGE SORT/]
	public static String[] mergeSort(String[] array) {
		if (array.length < 2)
			return array;
		String[] arrayL = new String[array.length / 2];
		String[] arrayR = new String[array.length - array.length / 2];

		for (int i = 0; i < array.length / 2; i++)
			arrayL[i] = array[i];
		for (int i = 0; i < array.length - array.length / 2; i++)
			arrayR[i] = array[i + array.length / 2];

		arrayL = mergeSort(arrayL);
		arrayR = mergeSort(arrayR);
		array = merge(arrayL, arrayR);

		return array;
	}

	public static String[] merge(String[] arrayL, String[] arrayR) {
		String[] array = new String[arrayL.length + arrayR.length];
		int arrayLindex = 0;
		int arrayRindex = 0;
		for (int i = 0; i < array.length; i++) {
			if (compareStrings(arrayL[arrayLindex], arrayR[arrayRindex]) == arrayL[arrayLindex]) {
				array[i] = arrayL[arrayLindex];
				if (arrayLindex < arrayL.length - 1)
					arrayLindex++;
				else
					arrayL[arrayLindex] = null;
			} else {
				array[i] = arrayR[arrayRindex];
				if (arrayRindex < arrayR.length - 1)
					arrayRindex++;
				else
					arrayR[arrayRindex] = null;
			}
		}

		return array;
	}

	// [/BAD MERGE SORT]

	public static String compareStrings(String string1, String string2) // returns
																		// alphabeticly
																		// first
																		// string
	{
		if (string1 == null)
			return string2;
		if (string2 == null)
			return string1;
		String string1C = string1.toLowerCase();
		String string2C = string2.toLowerCase();
		for (int i = 0; i < Integer.min(string1.length(), string2.length()); i++) {
			if (string1C.charAt(i) < string2C.charAt(i))
				return string1;
			if (string1C.charAt(i) > string2C.charAt(i))
				return string2;
		}
		if (string1C.length() > string2C.length())
			return string2;
		return string1;
	}

	public static int score(String string) {
		string = string.toUpperCase();
		int score = 0;
		for (int i = 0; i < string.length(); i++)
			score += (int) string.charAt(i) - 64;
		return score;
	}

}
