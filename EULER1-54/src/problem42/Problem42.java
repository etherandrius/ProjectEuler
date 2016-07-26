package problem42;

import java.io.File;
import java.util.Scanner;

public class Problem42 {

	public static void main(String[] arg) throws Exception {
		// ---------
		System.out.println(getNumOfTriangleWords());
		// ---------
	}

	public static int getNumOfTriangleWords() throws Exception {
		int n = 0;
		String[] words = input();
		int[] tringles = triangleNumbers(30);

		for (int i = 0; i < words.length; i++) {
			int score = getScore(words[i]);
			for (int j = 0; j < 30; j++) {
				if (score == tringles[j]) {
					n++;
					break;
				}
				if (tringles[j] > score)
					break;

			}
		}

		return n;
	}

	public static int getScore(String word) // assumes all letters are
											// capitalized
	{
		int score = 0;
		for (int i = 0; i < word.length(); i++)
			score += (int) word.charAt(i) - 64;
		return score;
	}

	public static int[] triangleNumbers(int limit) {

		int[] tringles = new int[limit];

		tringles[0] = 1;
		for (int i = 2, index = 1; index < limit; i++, index++)
			tringles[index] += tringles[index - 1] + i;

		return tringles;
	}

	public static String[] input() throws Exception {
		Scanner scan = new Scanner(new File("p042_words.txt"));
		String line = scan.nextLine();
		scan = new Scanner(new File("problem22file"));
		scan.close();
		line = line.replace("\"", "");

		String[] input = line.split(",");

		return input;
	}

	public static void printArray(int[] Array) {
		System.out.print("[");
		System.out.print(Array[0]);
		for (int i = 1; i < Array.length; i++)
			System.out.print(", " + Array[i]);
		System.out.println("]");
	}

}
