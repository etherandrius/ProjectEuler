package problem109;

public class Problem109 {
	
	public static void main(String[] args){	
		int[] val = {0, 1, 2, 3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 25, 	// 21 -> 25
						2, 4, 6,  8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 50,	// 42 -> 50
						3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36, 39, 42, 45, 48, 51, 54, 57, 60};		// 62 -> 60
		int[] dub = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 50};		// 20 -> 50		
		int counter = 0;

		for(int a = 0; a < 63; a++)
			for(int b = a; b < 63; b++)
				for(int c = 0; c < 21; c++){
					if( val[a] + val[b] + dub[c] < 100 ){					
						System.out.print((a == 0) ? ""
								: ((a < 22) ? ("S" + val[a]) : ((a < 43) ? ("D" + val[a] / 2) : ("T" + val[a] / 3))) + " ");
						System.out.print((b == 0) ? ""
								: ((b < 22) ? ("S" + val[b]) : ((b < 43) ? ("D" + val[b] / 2) : ("T" + val[b] / 3))) + " ");
						System.out.println( "D" + dub[c]/2);
						counter++;
					}
				}
		System.out.println(counter);
	}

}








