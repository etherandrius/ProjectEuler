package problem105;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Problem105 {
	
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
				
				while( _setA > 0){
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
	
	public static void main(String[] args) throws IOException{
		
		int result = 0;
		
		Scanner scan = new Scanner(new File("p105_sets.txt"));
		while(scan.hasNextLine()){
			String line = scan.nextLine();
			String[] lineSplit = line.split(",");
			ArrayList<Integer> set = new ArrayList<Integer>(12);
			for(String i : lineSplit) set.add(Integer.parseInt(i));
			Collections.sort(set);
			if(check(set))
				for(int i : set) result += i;				
		}		
		System.out.println(result);		
		scan.close();
	}
}
