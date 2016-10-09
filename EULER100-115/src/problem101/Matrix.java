package problem101;

import java.util.ArrayList;

public class Matrix {
	
	public int n;
	public long[][] m;
	public int colS;
	public int rowS;
	
	public Matrix(int n){
		this.n = n;
		this.m = new long[n][n+1];
		this.colS = n+1;
		this.rowS = n;
	}
	
	public Matrix(int n, ArrayList<Long> un){ // n < un.size()
		this.n = n;
		this.colS = n+1;
		this.rowS = n;
		this.m = new long[n][n+1];
		fillMatrix(un);
	}
	
	public void fillMatrix(ArrayList<Long> un){
		for(int row = 0; row < this.rowS; row++){
			this.m[row][0] = 1;
			this.m[row][n] = un.get(row+1);
			for(int col = 1; col < this.colS-1; col++)
				this.m[row][col] = this.m[row][col-1]*(row+1);
		}
	}
	
	public void print(){
		
		for(int row = 0; row < n; row++){
		for(int col = 0; col < n; col++){
			System.out.print(this.m[row][col] + " ");
		}
		System.out.println( " | " + this.m[row][n]);
	}	
	}
}
