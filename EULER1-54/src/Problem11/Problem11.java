package Problem11;

import java.io.File;
import java.util.Scanner;


	public class Problem11 
{
	/*
	 *     NW N NE
	 * 		W	E
	 * 	   SW S SE // 000-NW, 001-N, 010-NE, 011-W, 100-E, 101-SW, 110-S, 111-SE.
	 */
	public static void main(String[] arg) throws Exception
	{
		
	Scanner input = new Scanner(new File("Problem11file.txt"));
	int[][] grid = new int[20][20];
	for(int i=0; i<20; i++)
		for(int j=0; j<20; j++)
			grid[i][j] = input.nextInt();
	input.close();
	System.out.println(product( grid));
	
	
		
	}
	
	public static int product(int[][] grid)
	{
		int[] score= new int[8];
		for(int i=0; i<8; i++)
			score[i]=1;
		int max=0;
		for(int i=0; i<20; i++)
			for(int j=0; j<20; j++)
			{
				for(int l=0; l<8; l++)
					score[l]=1;
				if(i>2 && j>2)
					for(int k=0; k<4; k++)
						score[0]*=grid[i-k][j-k];
				if(i>2)
				{
					for(int k=0; k<4; k++)
						score[1]*=grid[i-k][j];
					if(j<17)
						for(int k=0; k<4; k++)
							score[2]*=grid[i-k][j+k];
				}
				if(j>2)
				{
					for(int k=0; k<4; k++)
						score[3]*=grid[i][j-k];
					if(i<17)
						for(int k=0; k<4; k++)
							score[4]*=grid[i+k][j-k];
				}
				if(j<17 && i<17)
					for(int k=0; k<4; k++)
						score[5]*=grid[i+k][j+k];
				if(j<17)
					for(int k=0; k<4; k++)
						score[6]*=grid[i][j+k];
				if(i<17)
					for(int k=0; k<4; k++)
						score[7]*=grid[i+k][j];
				max=Integer.max(score[0], Integer.max(score[1], Integer.max(score[2], Integer.max(score[3], Integer.max(score[4], Integer.max(score[5], Integer.max(score[6], Integer.max(score[7], max))))))));
			}
		
		return max;
	}
	
}

	
	
	
	
	
	
	
	
//	
//	FileReader file = new FileReader("Problem10file.txt");
//	BufferedReader input = new BufferedReader(file);
//	
//	int[][] grid= new int[20][20];
//	int number = input.();
//	for(int i=0; i<20; i++)
//	{
//		grid[i] = ;
//		line = input.readLine();
//	}
//	
//	System.out.println(text[0]);
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	