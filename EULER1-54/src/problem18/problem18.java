package problem18;

import java.io.File;
import java.util.Scanner;

public class problem18 {

	public static void main(String[] arg) throws Exception {
		Scanner scan = new Scanner(new File("Problem18file"));
		int x = scan.nextInt();
		int[][] p = new int[x][x];
		for (int i = 0; i < x; i++)
			for (int j = 0; j <= i; j++)
				p[i][j] = scan.nextInt();
		scan.close();

		for (int i = x - 2; i >= 0; i--)
			for (int k = 0; k <= i; k++)
				p[i][k] += Integer.max(p[i + 1][k], p[i + 1][k + 1]);
		System.out.print(p[0][0]);

	}

}
