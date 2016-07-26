package problems;

import java.io.File;
import java.util.Scanner;
import general.methods;

public class Problem54 {

	/*
	 * States;
	 * 0 - High Card: Highest value card. 
	 * 1 - One Pair: Two cards of the same value. Two
	 * 2 - Pairs: Two different pairs. 
	 * 3 - Three of a Kind: Three cards of the same value. 
	 * 4 - Straight: All cards are consecutive values. 
	 * 5 - Flush: All cards of the same suit. 
	 * 6 - Full House: Three of a kind and a pair. 
	 * 7 - Four of a Kind:Four cards of the same value. 
	 * 8 - Straight Flush: All cards are consecutive values of same suit. 
	 * 9 - Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
	 */

	public static void main(String[] arg) {

		String[][] hands = input();
		
//		methods.printArray(hands[999]);
//		Problem54hand player1 = new Problem54hand(1, hands[999]);
//		player1.printHand();
//		System.out.println(player1.getState());
//		player1.classify();
//		methods.printArray(player1.getClassification());
//		System.out.println("s--s-s-s-s-s-s-s--s-s-s-s-s-s-s-s");
//		Problem54hand player2 = new Problem54hand(2, hands[999]);
//		player2.printHand();
//		System.out.println(player2.getState());
//		player2.classify();
//		methods.printArray(player2.getClassification());
		
		int player1W = 0;
		int player2W = 0;
		for (int i = 0; i < 1000; i++) {
			Problem54hand player1 = new Problem54hand(1, hands[i]);
			Problem54hand player2 = new Problem54hand(2, hands[i]);
			if (player1.getState() > player2.getState()) {
				++player1W;
				continue;
			}
			equals:{
			if (player1.getState() == player2.getState()) {
				player1.classify();
				player2.classify();

				for (int j = 0; j < player1.getClassification().length; j++) {
					if (player1.getClassification()[j] == player2.getClassification()[j])
						continue;

					if (player1.getClassification()[j] > player2
							.getClassification()[j]) {
						++player1W;
						break equals;
					} else {
						++player2W;
						break equals;
					}
				}
				System.out.print("i : " + i + ",  ");
				methods.printArray(hands[i]);
				continue;
			}
			
			
			++player2W;}
		}
		
		System.out.println(player1W);
		System.out.println(player2W);
		
		
	}
	
	
	private static String[][] input() {

		try {
			Scanner scan = new Scanner(new File("p054_poker.txt"));

			String[][] input = new String[1000][10];
			for (int i = 0; i < 1000; i++) {
				String line = scan.nextLine();
				input[i] = line.split(" ");
			}
			scan.close();
			return input;

		} catch (Exception e) {
			return null;

		}
	}
}
