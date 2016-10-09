#include <iostream>
#include <gmp.h>
#include <cmath>

int main(){

	  mpf_t root;
	  mpf_set_default_prec(100000);
	  mpf_init(root);
	  int result = 0;
	  for(int k = 2; k <= 100; k++){
		  if( sqrt(k) == floor(sqrt(k)))
			  continue;

		mpf_set_ui(root, k);
		mpf_sqrt(root, root);
//		std::cout << k << " : ";
//		gmp_printf("%.100Ff\n\n", root);
		mp_exp_t exponent;
		char *h = mpf_get_str(NULL, &exponent, 10, 110, root);
		for (int i = 0; i < 100; i++)
			result += (int) h[i] - 48;
	}

	std::cout << result << std::endl;

	for (int i = 0; i < 100; i++)
		std::cout << "-";
	std::cout << std::endl;

}

















