#include <cmath>
#include <iostream>
#include <gmp.h>

using namespace std;

int main() {

	int sum = 0;

	mpz_t n, d;
	mpz_init(n);
	mpz_init(d);
	mpz_set_ui(n, 3);
	mpz_set_ui(d, 2);

	mpz_t denom;
	mpz_init(denom);

	mpz_t gcd;
	mpz_init(gcd);

	for (int i = 2; i <= 1000; i++) {

		mpz_set(denom, d);
		mpz_add(d, d, n);
		mpz_add(n, d, denom);

		mpz_gcd(gcd, n, d);
		mpz_div(n, n, gcd);
		mpz_div(d, d, gcd);

		char oneChar;
		int sizeN = gmp_snprintf(&oneChar, 1, "%Zd", n);
		int sizeD = gmp_snprintf(&oneChar, 1, "%Zd", d);

		if (sizeN > sizeD) {
			++sum;
//			cout << "Iteration : " << i << "\t";
//			gmp_printf ("%Zd", n);
//			cout << "/";
//			gmp_printf ("%Zd\n", d);
//			cout << sizeN << "\t" << sizeD <<endl;
		}
	}

	cout << sum << endl;

}

