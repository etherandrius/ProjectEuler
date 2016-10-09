package problem103;

import java.util.ArrayList;
import java.util.Arrays;

public class problem103 {
	
	private static int limit = 0;
	
	public static boolean check(ArrayList<Integer> set){		
		for(int i = 1; i < Math.pow(3, set.size()); i++){
			int base3 = i;
			while(base3%3==0) base3/=3;
			if(base3%3 == 2) continue; // base3%3 == 2 or 1			
			
			int sumA=0, sumB=0;
			int lenA=0, lenB=0;
			int index = 0;
			base3 = i;
			
			while( base3 > 0){
				switch(base3%3){
				case 0: break;
				case 1: sumA += set.get(index);
						lenA++;
						break;
				case 2: sumB += set.get(index);
						lenB++;
						break;}
				++index;
				base3/=3;
			}
			
			if(lenB == 0) continue; // lenA can't be 0			
			if( sumA == sumB ) return false;
			if( lenB > lenA && sumB < sumA ) return false; 
			if( lenA > lenB && sumA < sumB ) return false;
		}
		return true;
	}
	
	public static void solve(ArrayList<Integer> set){
		for(int i = set.size()-1; i <= limit / set.size(); i++){
			set.set(0, i);
			solve(set, i, 1);
		}		
	}
	
	private static void solve(ArrayList<Integer> set, int sum, int depth){
		if( depth >= set.size() ){
			if( check(set)){
				System.out.println(set + " " + sum);	
				limit = Math.min(sum, limit);
			}
		}
		else{			
			int LIMIT = (limit- sum - (((set.size()-depth-1)*(set.size()-depth))/2))/(set.size()-depth);
			for(int i = set.get(depth-1)+1; i <= LIMIT; i++ ){
				set.set(depth, i);
				solve(set, sum+i, depth+1);
			}
		}
	}
	
	public static void advance(ArrayList<Integer> set){ // advances to optimum set from n to n+1
		limit = set.get(set.size()/2) * (set.size()+1);
		for(int i : set) limit += i;
		
		ArrayList<Integer> new_set = new ArrayList<Integer>(set.size()+1);
		for(int i = 0; i < set.size()+1; i++) new_set.add(0);
		
		solve(new_set);
		
		set = new ArrayList<Integer>(new_set);
	}
	
	public static void main(String[] args){				
//		Integer[] B = {6, 9, 11, 12, 13};
//		ArrayList<Integer> A = new ArrayList<Integer>(Arrays.asList(B));
//		advance(A);	
	}

}