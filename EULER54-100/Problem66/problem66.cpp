/*


////////// NOTE IF YOU WANT TO RUN THIS INCLDUE GMP IN LIBRARIE's


#include <iostream>
#include <gmp.h>
#include <cmath>

using namespace std;

int main() {

	//
	// First Try, works but gets very slow when x is more that 10-digits
	//


	mpz_t y, k, maxY;
	mpz_init(y);
	mpz_init(k);
	mpz_init(maxY);
	mpz_set_ui(maxY, 1);

	int maxD;

	for(int D = 2; D <= 1000; D++)
		if(D != (int)sqrt(D) * (int)sqrt(D)){
			mpz_set_ui(y, 0);
			mpz_set_ui(k, D);

			int temp = 2*D;

			while(true){
				mpz_add(y, y, k);
				mpz_add_ui(k, k, temp);

				mpz_add_ui(y, y, 1);

				if(mpz_perfect_square_p(y) != 0){
					if(mpz_cmp(y, maxY) > 0){

						maxD = D;

						mpz_set(maxY, y);
						break;

					}
					else break;
				}

				mpz_sub_ui(y, y, 1);


			}

			//TODO DEL
			mpz_sqrt(y, y);
			gmp_printf("X : %Zd, D : %d", y, D);
			mpz_mul(y, y, y);
			//TODO DEL
			//TODO DEL
			mpz_sub_ui(y, y, 1);
			mpz_div_ui(y, y, D);
			mpz_sqrt(y, y);
			gmp_printf(",  Y : %Zd \n", y);
			mpz_mul(y, y, y);
			mpz_mul_ui(y, y, D);
			mpz_add_ui(y, y, 1);
			//TODO DEL


		}

	mpz_sqrt(maxY, maxY);
	gmp_printf("%Zd,  D : %d,   \n", maxY, maxD);


	double diff = (double) clock() / (double) CLOCKS_PER_SEC;
	cout << diff << endl;
}

*/

