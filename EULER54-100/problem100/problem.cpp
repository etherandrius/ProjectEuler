#include <iostream>
#include <cmath>
#include <gmp.h>

int main(){
	// couldn't solve this one myself so
	// googl'd “quadratic Diophantine equation”
	// https://www.alpertron.com.ar/QUAD.HTM

	unsigned long limit = 1000000000000;
	unsigned long n = 21; // 4, 3, -3
	unsigned long b = 15; // 3, 2, -2
	while(n < limit){
		unsigned long b_temp = 3*b + 2*n - 2;
		unsigned long n_temp = 4*b + 3*n - 3;

		b = b_temp;
		n = n_temp;
	}

	std::cout << b << '\n';
	std::cout << n << '\n';
}

