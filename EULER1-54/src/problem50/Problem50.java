package problem50;

public class Problem50 {

	/*
	 * Tries To solve problem50 from project euler
	 * But ignoring that only consecutive primes are needed
	 */
	// public static int _sum=0;
	// public static boolean _state = true;
	//
	// public static void main(String[] arg)
	// {
	// // primes before limit 78498
	//
	//
	// int limit = 1000000;
	// int[] primes = new int[78498];
	// boolean[] sieve = new boolean[limit];
	//
	// for(int i=2, index=0; i<limit; i++)
	// if(!sieve[i])
	// {
	// primes[index]=i;
	// index++;
	// for(int j=i+i; j<limit; j+=i)
	// sieve[j]=true;
	// }
	// boolean[] primesC = new boolean[22044];
	// System.out.println("//-----------------//");
	//
	//
	// for(int i=57; i>0; i--)
	// if(_sum==0)
	// {
	// int localSum =0;
	//
	// for(int j=0; j<i; j++)
	// {
	// primesC[j]=true;
	// localSum+=primes[j];
	// }
	// permutate50(primes, primesC, i, localSum);
	// System.out.println("i : " + i);
	// }
	//
	//
	//
	// System.out.println(_sum);
	//
	// }
	//
	// public static void permutate50(int[] primes, boolean[] primesC, int
	// limit, int localSum)
	// {
	//
	// System.out.println("localSum " + localSum);
	// if(_sum!=0)
	// return;
	//
	// if(localSum>1000000)
	// {
	// _state=false;
	// return;
	// }
	//
	// if(isPrime(localSum, primes))
	// {
	// _sum = localSum;
	// System.out.println(localSum);
	// System.out.println("limit : " + limit);
	// System.out.println("//-----------------//");
	// }
	//
	// for(int i=primesC.length-1; i>=0; i--)
	// for(int j=i+1; j<primesC.length; j++)
	// {
	// if(primesC[i])
	// if(!primesC[j])
	// {
	// primesC[i]=false;
	// primesC[j]=true;
	// permutate50(primes, primesC, limit, localSum-primes[i]+primes[j]);
	// primesC[i]=true;
	// primesC[j]=false;
	// }
	// if(_state==false)
	// break;
	// }
	//
	// _state=true;
	//
	//
	//
	// }
	//
	//
	//
	//
	//
	// // public static void permute50(int[] primes, boolean primesC[], int
	// limit)// assumes first limit primesC[] are true;
	// // {
	// // int localSum=0;
	// // for(int i=0; i<primesC.length; i++)
	// // if(primesC[i])
	// // localSum+=primes[i];
	// //
	// // System.out.println("localSum : " + localSum + "\t" + limit);
	// // if(localSum>1000000)
	// // {
	// // _state=false;
	// // return;
	// // }
	// //
	// // if(isPrime(localSum, primes))
	// // {
	// // _sum = localSum;
	// // System.out.println(localSum);
	// // System.out.println("limit : " + limit);
	// // System.out.println("//-----------------//");
	// // }
	// //
	// // for(int i=primesC.length-1; i>=0; i--)
	// // for(int j=i+1; j<primesC.length; j++)
	// // {
	// // if(primesC[i])
	// // if(!primesC[j])
	// // {
	// // primesC[i]=false;
	// // primesC[j]=true;
	// //
	// // _state=true;
	// // permute50(primes, primesC, limit);
	// // primesC[i]=true;
	// // primesC[j]=false;
	// // }
	// // if(_state==false)
	// // break;
	// // }
	// //
	// // _state=true;
	// // }
	//
	// public static boolean isPrime(int n, int[] primes)// true only if
	// primes.lastelement>n;
	// {
	// for(int i=0; i<primes.length; i++)
	// if(primes[i]>n)
	// return false;
	// else
	// if(primes[i]==n)
	// return true;
	// return false;
	// }
	//
	// public static void printArray(int[] Array)
	// {
	// System.out.print("[");
	// System.out.print(Array[0]);
	// for(int i=1; i<Array.length; i++)
	// System.out.print(", " + Array[i]);
	// System.out.println("]");
	// }
	//
}
