package problems;

public class Problem54hand {

	/*
	 * 0 - spades; 1 - hearts; 2 - diamonds; 3 - clubs;
	 */

	/*
	 * States;
	 * 0 - High Card: Highest value card. 								  -	{State, High - low ...}
	 * 1 - One Pair: Two cards of the same value. Two					  -	{State, pairValue, what's left...}
	 * 2 - Pairs: Two different pairs. 									  -	{State, pairValue, , lone card}
	 * 3 - Three of a Kind: Three cards of the same value. 				  -	{State, V3, V2, 1, 0}
	 * 4 - Straight: All cards are consecutive values. 					  -	{State, highestCard, 0, 0, 0} 
	 * 5 - Flush: All cards of the same suit. 							  -	{State, 1st highest card, 2nd , 3rd, 4th, 5th}
	 * 6 - Full House: Three of a kind and a pair. 						  - {State, highest3, highest2, 0, 0, 0}
	 * 7 - Four of a Kind:Four cards of the same value. 				  -	{State, highestFour, highest left,}
	 * 8 - Straight Flush: All cards are consecutive values of same suit. - {State, highest card, ..};
	 * 9 - Royal Flush: Ten, Jack, Queen, King, Ace, in same suit. 		  - 
	 * 
	 * 
	 * 
	 */
	
	private int[] values = new int[5];
	private int[] suits = new int[5];
	private int state;
	private int[] list;
	private int[] classification;

	public Problem54hand(int player, String[] hand) {
		// player either 2 or 1

		values = generateValues(player, hand);
		suits = generateSuits(player, hand);
		list = valueList();
		state = generateState();
	}

	public int[] getClassification() {
		return classification;
	}

	public void classify() {
		classification = classify1();
	}

	private int[] classify1() {

		if (state == 0) {
			int[] classification = new int[5];
			for (int i = 14, index = 0; index < 5; i--)
				if (list[i] == 1) {
					classification[index] = i;
					++index;
				}
			return classification;
		}

		if (state == 1) {
			int[] classification = new int[4];
			for (int i = 14; i > 0; i--)
				if (list[i] == 2) {
					classification[0] = i;
					break;
				}
			for (int i = 14, index = 1; index < 4; i--)
				if (list[i] == 1) {
					classification[index] = i;
					++index;
				}

			return classification;

		}

		if (state == 2) {
			int[] classification = new int[3];
			for (int i = 14, index = 0; index < 2; i--)
				if (list[i] == 2) {
					classification[index] = i;
					++index;
				}
			for (int i = 14; i > 0; i--)
				if (list[i] == 1) {
					classification[2] = i;
					break;
				}
			return classification;

		}

		if (state == 3) {

			int[] classification = new int[3];
			for (int i = 14; i > 0; i--)
				if (list[i] == 3) {
					classification[0] = i;
					break;
				}
			for (int i = 14, index = 1; index < 3; i--)
				if (list[i] == 1) {
					classification[index] = i;
					++index;
				}
			return classification;

		}

		if (state == 4) {

			int[] classification = new int[1];
			for (int i = 14; i > 0; i--)
				if (list[i] == 1) {
					classification[0] = i;
				}
			return classification;

		}

		if (state == 5) {
			int[] classification = new int[5];
			for (int i = 14, index = 0; index < 5; i--)
				if (list[i] != 0)
					for (int j = 0; j < list[i]; j++) {
						classification[index] = i;
						++index;
					}

			return classification;

		}

		if (state == 6) {

			int[] classification = new int[2];
			for (int i = 14; i > 0; i--)
				if (list[i] == 3) {
					classification[0] = i;
					break;
				}
			for (int i = 14; i > 0; i--)
				if (list[i] == 2) {
					classification[1] = i;
					break;
				}
			return classification;

		}

		if (state == 7) {

			int[] classification = new int[2];
			for (int i = 14; i > 0; i--)
				if (list[i] == 4) {
					classification[0] = i;
					break;
				}
			for (int i = 14; i > 0; i--)
				if (list[i] == 1) {
					classification[1] = i;
					break;
				}
			return classification;

		}

		if (state == 8) {

			int[] classification = new int[1];
			for (int i = 13; i > 0; i--)
				if (list[i] == 1) {
					classification[0] = i;
				}
			return classification;

		}

		int[] classification = new int[0];// don't need state for 9 since all
											// royal flushes are equal;
		return classification;

	}

	public void printHand() {
		System.out.print("[ " + values[0] + "-" + suits[0]);
		for (int i = 1; i < 5; i++)
			System.out.print(", " + values[i] + "-" + suits[i]);
		System.out.println(" ]");
	}

	public int getState() {
		return state;
	}

	public int getSuit(int card) {
		/*
		 * card 0-4
		 */
		if (card < 5 && card > -1)
			return suits[card];
		return -1;
	}

	public int getValue(int card) {
		/*
		 * card 0-4
		 */
		if (card < 5 && card > -1)
			return values[card];
		return -1;
	}

	private int generateState() {

		boolean flush = isFlush();
		int straigth = isStraigth();

		if (flush && straigth != -1)
			if (straigth == 14)
				return 9;// Royal Flush
			else
				return 8;// Straight Flush

		int max = 0;
		for (int i = 1; i < 15; i++)
			max = Integer.max(max, list[i]);

		if (max == 4)
			return 7;// Four Of a Kind

		if (max == 3)
			for (int i = 1; i < 15; i++)
				if (list[i] == 2)
					return 6;// Full House

		if (flush)
			return 5;// Flush

		if (straigth != -1)
			return 4;// Straight

		if (max == 3)
			return 3;// Three of a Kind

		if (max == 2) {
			int count = 0;
			for (int i = 1; i < 15; i++)
				if (list[i] == 2)
					++count;

			if (count == 2)
				return 2;// Pairs

			return 1;// One Pair
		}

		return 0;// High
	}

	private boolean isFlush() {
		for (int i = 1; i < 5; i++)
			if (suits[0] != suits[i])
				return false;
		return true;
	}

	private int isStraigth() {

		int valueM = 0;
		for (int i = 0; i < 5; i++)
			valueM = Integer.max(valueM, values[i]);

		if (valueM == 14 || valueM < 5)
			return -1;

		for (int i = valueM; i > valueM - 5; i--)
			search: {
				for (int j = 0; j < 5; j++)
					if (values[j] == i)
						break search;
				return -1;
			}

		return valueM;
	}

	private int[] valueList() {
		int[] list = new int[15];
		for (int i = 0; i < 5; i++)
			list[values[i]]++;

		return list;
	}

	private static int[] generateSuits(int player, String[] hand) {
		int correction;
		if (player == 1)
			correction = 0;
		else
			correction = 5;

		int[] suits = new int[5];
		for (int index = 0; index < 5; index++)
			switch (hand[index + correction].charAt(1)) {
			case 'S':
				suits[index] = 0;
				break;
			case 'H':
				suits[index] = 1;
				break;
			case 'D':
				suits[index] = 2;
				break;
			case 'C':
				suits[index] = 3;
				break;
			}

		return suits;
	}

	private static int[] generateValues(int player, String[] hand) {

		int correction;
		if (player == 1)
			correction = 0;
		else
			correction = 5;

		int[] values = new int[5];
		for (int index = 0; index < 5; index++)
			switch (hand[index + correction].charAt(0)) {
			case 'T':
				values[index] = 10;
				break;
			case 'J':
				values[index] = 11;
				break;
			case 'Q':
				values[index] = 12;
				break;
			case 'K':
				values[index] = 13;
				break;
			case 'A':
				values[index] = 14;
				break;
			default:
				values[index] = Character.getNumericValue(hand[index
						+ correction].charAt(0));
			}

		return values;
	}

}
