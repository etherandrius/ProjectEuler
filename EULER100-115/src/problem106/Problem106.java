package problem106;

public class Problem106 {

	
	//Ugly code was to lazy to fix it
	public static boolean pairing(int A, int B){
		// assumes A and B are intertwined 1100:0011 is not intertwined 1010::11 is;
		// 1001:11 is;   A > B
		int counter = 0;
		while(A > 0){
			
			if( (A & 1) == 0){
				if( (B & 1) == 1) counter++;
				B >>=1;
			}
			else counter--;
			A>>=1;
			if(counter < 0) return false;
		}		
		return true;
	}

	
	public static void main(String[] args){
		
		int result = 0;
		for(int setA = 1; setA < Math.pow(2, 12); setA++)
		{
			int _setA = setA;
			int bitsA = 0;
			int LIMIT = 1;
			while(_setA > 0){
				if( (_setA & 1 ) == 0 ) LIMIT <<= 1;
				else bitsA++;
				_setA >>= 1;
			}
			
			if( bitsA != 1)
			for(int setB = 1; setB < LIMIT; setB++){
				int _setB = setB, bitsB = 0;
				while( _setB > 0 ){
					if( (_setB & 1 ) == 1) bitsB++;
					_setB>>=1;
				}
				if( bitsB != bitsA ) continue;
				_setB = setB; _setA = setA;
				while( (_setA & 1 ) == 0  && _setA > 0){
					_setA >>=1;
					_setB >>=1;
				}
				if( _setB > 0 && !pairing(setA, setB)) {
					result++;
				}
			}
		}
		System.out.println(result);
		
		
		
		
	}
		
		
		
		
	
}
