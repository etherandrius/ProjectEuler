#include <iostream>
#include <cmath>

int main(){
//	// https://www.alpertron.com.ar/QUAD.HTM
//	// solved Diophantine equation 3b*b -/+ 2b - 1 = k*k
//
	unsigned long sum = 0;
	long limit = 1000000000;
	long b = 5;
	long k = 8;
	while( b <= (limit-1)/3 ){ // 3*b*b - 2*b - 1 = k*k
		long b_temp = (-2)*b - k + 1;
		long k_temp = (-3)*b + (-2)*k + 1;

		sum += 3*b + 1;

		std::cout << " b : "<< b << std::endl;
		std::cout << " k : " << k << std::endl << std::endl;

		b = (-2)*b_temp - k_temp + 1;
		k = (-3)*b_temp + (-2)*k_temp + 1;
	}

	b = 17;
	k = 30;

	while( b <= (limit+1)/3 ){ // 3*b*b + 2*b - 1 = k*k
		long b_temp = (-2)*b - k - 1;
		long k_temp = (-3)*b - 2*k - 1;

		sum += 3*b - 1;

		std::cout << " b : "<< b << std::endl;
		std::cout << " k : " << k << std::endl << std::endl;


		b = (-2)*b_temp - k_temp - 1;
		k = (-3)*b_temp - 2*k_temp - 1;
	}

	std::cout << sum << std::endl;
}
