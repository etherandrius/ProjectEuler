#include <iostream>
#include <gmp.h>

template<typename T>
T powMOD(T base, T exp, T mod){ // (base^exp)%mod
	base %= mod;
	T result = 1;
	while (exp > 0) {
		if (exp & 1) result = (result * base) % mod;
		base = (base * base) % mod;
		exp >>= 1; // exp /= 2
	}
	return result;
}

void powMOD(mpz_t base, mpz_t exp, mpz_t& mod, mpz_t& result){
	mpz_mod(base, base, mod);
	mpz_set_ui(result, 1);
	while( mpz_cmp_ui(exp, 0) > 0){
		if( mpz_even_p(exp) != 0){
			mpz_mul(result, result, base);
			mpz_mod(result, result, mod);
		}
		mpz_mul(base, base, base);
		mpz_mod(base, base, mod);
		mpz_div_ui(exp, exp, 2);
	}
}



int main(){

	long result = 1;
	for(int i = 0; i < 7830457; i++){
		result <<= 1;
		result %= 10000000000;
	}
	result *= 28433;
	result += 1;
	result %= 10000000000;
	std::cout << result <<std::endl;

//	mpz_t mod; mpz_init(mod); mpz_set_str(mod, "10000000000", 10);
//	mpz_t base; mpz_init(base); mpz_set_ui(base, 2);
//	mpz_t exp; mpz_init(exp); mpz_set_ui(exp, 7830457);
//	mpz_t result; mpz_init(result);
//
//	powMOD(base, exp, mod, result);
//
//	mpz_mul_ui(result, result, 28433);
//	mpz_mod(result, result, mod);
//	mpz_add_ui(result, result, 1);
//	mpz_mod(result, result, mod);
//	gmp_printf("%Zd\n",result);

}













