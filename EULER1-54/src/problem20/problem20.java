package problem20;

import java.math.BigInteger;

public class problem20 {

	public static void main(String[] arg) {
		BigInteger fact = BigInteger.valueOf(1);
		for (int i = 2; i < 101; i++)
			fact = fact.multiply(BigInteger.valueOf(i));
		String factS = fact.toString();
		int sum = 0;
		for (int i = 0; i < factS.length(); i++)
			sum += Character.getNumericValue(factS.charAt(i));
		;

		System.out.println(fact);
		System.out.println(factS);
		System.out.println(sum);
	}

}
