package problem35;

import java.util.Vector;

public class Problem35 {

	public static void main(String[] arg) {
		System.out.println("Increadibly Slow");
		System.out.println("Increadibly Slow");
		System.out.println("NOT WORKING");
		System.out.println("NOT WORKING");

		int limit = 1000000;
		Vector<Integer> primes = sievePrimes(limit);
		// System.out.println(primes);

		Vector<Integer> results = new Vector<Integer>((int) Math.sqrt(limit), (int) Math.sqrt(limit));

		int digits = 1;
		int Digits = 10;
		for (int i = 0; i < primes.size(); i++) {
			if (primes.elementAt(i) >= Digits) {
				digits++;
				Digits *= 10;
			}

			if (isCircularyPrime(primes.elementAt(i), digits, primes))
				results.add(primes.elementAt(i));
			if (i % 10000 == 0)
				System.out.println(i);

		}
		// System.out.println(results);
		System.out.println(results.size());

	}

	public static boolean isCircularyPrime(int prime, int numDigits, Vector<Integer> sieve) {
		// int numDigits = Integer.toString(prime).length();
		for (int i = 0; i < numDigits - 1; i++) {
			prime = rotate(prime, numDigits);
			if (!isInVector(prime, sieve))
				return false;
		}
		return true;
	}

	public static int rotate(int prime, int numDigits) {
		int lastDigit = prime % 10;
		prime /= 10;
		prime += lastDigit * Math.pow(10, numDigits - 1);

		return prime;
	}

	public static Vector<Integer> sievePrimes(int limit) {
		boolean[] primes = new boolean[limit + 1];
		Vector<Integer> primesVect = new Vector<Integer>((int) (limit / (Math.log(limit - 1))) + 10,
				(int) Math.log(limit) * (int) Math.log(limit));
		for (int i = 2; i < limit + 1; i++)
			if (!primes[i]) {
				primesVect.add(i);
				for (int j = i * 2; j < limit + 1; j += i)
					primes[j] = true;
			}

		return primesVect;
	}

	public static boolean isInVector(int prime, Vector<Integer> vector) {
		for (int i = 0; i < vector.size(); i++) {
			if (prime < vector.elementAt(i))
				return false;
			if (prime == vector.elementAt(i))
				return true;
		}
		return false;
	}

}
