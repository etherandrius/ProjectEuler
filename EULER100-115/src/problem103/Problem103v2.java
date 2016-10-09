package problem103;

import java.util.ArrayList;
import java.util.Arrays;

// tries solutions that deviate from sub optimum solution by at most ERROR
// O(ERROR^n)
public class Problem103v2 {

	public static int ERROR = 10;
	
	public static boolean check(ArrayList<Integer> set){
		// checks if set is optimum
		
		//rule 2 - cheap
		// checks smallest n set with largest n-1 set
		{
			int setA = set.get(0) + set.get(1);
			int setB = set.get(set.size()-1);
			for(int i = 2; i <= set.size()/2; i++){
				setA+=set.get(i);
				setB+=set.get(set.size()-i);
				if(setA <= setB) return false;
			}
		}
		
		//rule 1 - expensive
		// setA, setB : represents two sets as binary digits
		// setA & setB = 0 a must for sets to be disjoint
		// setA > setB to avoid duplicate entries
			
		int LIMIT = (int)Math.pow(2, set.size());
		for(int setA = 1; setA < LIMIT; setA++){
			int _setA = setA;
			int LIMIT2 = 1;
			while( _setA > 1 ){
				if( (_setA & 1 ) == 0 ) LIMIT2 <<= 1;
				_setA >>= 1;
			}
			for(int setB = 1; setB < LIMIT2; setB++){
				_setA = setA;
				int _setB = setB;
				int sumA = 0, sumB = 0;
				int index = 0;
				while( _setA > 0 || _setB > 0 ){
					if( (_setA & 1) == 1)
						sumA += set.get(index);
					else{
						if( (_setB & 1 ) == 1 )
							sumB += set.get(index);
						_setB >>= 1;
					}	
					index++;
					_setA >>= 1;
				}
				if( sumA == sumB ) return false;
			}
		}
		return true;
	}
	
	public static void optimize(ArrayList<Integer> dev, ArrayList<Integer> set, int error, int csum, int depth){	
		if(depth >= set.size()){			
			if(csum > 0) return;
			for(int i = 0; i < set.size() ; i++)
				set.set(i, set.get(i)+dev.get(i));

			if( check(set) ) System.out.println(set + " " + (csum));
			
			for(int i = 0; i < set.size() ; i++)
				set.set(i, set.get(i)-dev.get(i));			
		}
		else{
			
			if(depth == 1) System.out.println(dev.get(0));//TODO DEL			

			int down;
			if(depth > 0) down = set.get(depth-1)+dev.get(depth-1) - set.get(depth) + 1;
			else down = -set.get(0) + 2;
			down = Math.max(down, -error);
						
			for(int i = down; i < error; i++){
				dev.set(depth, i);
				optimize(dev, set, error, csum+i, depth+1);
			}
		}
	}
	
	public static void optimize(ArrayList<Integer> set, int error){
		ArrayList<Integer> deviation = new ArrayList<Integer>(set.size());
		for(int i = 0; i < set.size(); i++) deviation.add(0);
		optimize(deviation, set, error, 0, 0);
	}
	
	public static void advance(ArrayList<Integer> set){
		ArrayList<Integer> semi_set = new ArrayList<Integer>(set.size()+1);
		int b = set.get(set.size()/2);
		semi_set.add(b);
		for(int i : set) semi_set.add(i+b);
		optimize( semi_set, ERROR);
	}
	
	
	public static void main(String[] args){				
		final long startTime = System.currentTimeMillis();		
		
//		Integer[] B = {20, 31, 38, 39, 40, 42, 45};
		Integer[] B = {11, 18, 19, 20, 22, 25};
//		Integer[] B = {6, 9, 11, 12, 13};
		ArrayList<Integer> A = new ArrayList<Integer>(Arrays.asList(B));
		advance(A);	
				
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime) );		
		
	}
}

