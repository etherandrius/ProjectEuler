#include <iostream>
#include <cmath>
#include <vector>
#include <gmp.h>


using namespace std;

int main(){

	int limit = 99;

	int start = 2;
	int cFrac[limit];
	cFrac[0] = 1;
	cFrac[1] = 2;
	for(int i = 2, mul = 4; i < limit; i++){
		if( i%3 == 1 ){
			cFrac[i] = mul;
			mul+=2;
		}
		else cFrac[i] = 1;
	}

	mpz_t n, d;
	mpz_init(n);
	mpz_init(d);
	mpz_set_ui(n, 1);
	mpz_set_ui(d, cFrac[limit-1]);

	for(int i = limit-2; i>=0; i--){

		mpz_t temp;
		mpz_init(temp);
		mpz_set(temp, n);
		mpz_set(n, d);
		mpz_mul_ui(d, d, cFrac[i]);
		mpz_add(d, d, temp);

	}

	for(int i = 0; i <start; i++)
		mpz_add(n, n, d);

	gmp_printf("%Zd", n);
	cout << endl;
	gmp_printf("%Zd", d);
}










