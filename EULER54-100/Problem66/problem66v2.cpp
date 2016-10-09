/*
 * Second try after searching the web
 *
 * basic application of PQa algorithm for finding x2 - D * y2 = 1
 */

#include <iostream>
#include <cmath>
#include <gmp.h>

using namespace std;

class PQa { // consult http://www.jpr2718.org/pell.pdf. , G_-2 = -P_0, G_-1 = Q_0; // preset for N = 1;
public:
	//FUNCTIONS
	PQa(int D, int P = 0, int Q = 1) { // starts at index 0;

//		mpz_init(A1), 	   mpz_init(A2);
//		mpz_set_ui(A1, 1), mpz_set_ui(A2, 0);
//		mpz_init(B1), 	   mpz_init(B2);
//		mpz_set_ui(B1, 0), mpz_set_ui(B2, 1);
		mpz_init(G1), mpz_init(G2);
		mpz_set_ui(G1, Q), mpz_set_ui(G2, -P);

		P1 = 0, Q1 = 0, a1 = 0;
		index = 0;

		this->P = P;
		this->Q = Q;
		this->D = D;
		this->root = sqrt(D);

		a = (int) ((P + root) / Q);

//		mpz_init(A), mpz_mul_ui(A, A1, a); // A2 = 0 so i dont add it here
//		mpz_init(B), mpz_set(B, B2); 				   // B1 = 0 .....
		mpz_init(G), mpz_mul_ui(G, G1, a), mpz_add(G, G, G2);
	}

	void advance() {

		a1 = a, P1 = P, Q1 = Q;
//		mpz_set(A2, A1), mpz_set(A1, A);
//		mpz_set(B2, B1), mpz_set(B1, B);
		mpz_set(G2, G1), mpz_set(G1, G);

		P = a1 * Q1 - P1;
		Q = (D - P * P) / Q1;

		a = (int) ((P + root) / Q);

//		mpz_init(A), mpz_mul_ui(A, A1, a), mpz_add(A, A, A2);
//		mpz_init(B), mpz_mul_ui(B, B1, a), mpz_add(B, B, B2);
		mpz_init(G), mpz_mul_ui(G, G1, a), mpz_add(G, G, G2);

		++index;
	}

	void printf() {

		cout << "index : " << index << ", D : " << D << endl;
		cout << "a1 : " << a1 << ", a : " << a << endl;
		cout << "P1 : " << P1 << ", P : " << P << endl;
		cout << "Q1 : " << Q1 << ", Q : " << Q << endl;
//		gmp_printf("A2 : %Zd, A1 : %Zd, A : %Zd \n",A2,A1,A);
//		gmp_printf("B2 : %Zd, B1 : %Zd, B : %Zd \n",B2,B1,B);
		gmp_printf("G2 : %Zd, G1 : %Zd, G : %Zd \n", G2, G1, G);
		cout << "=\t=\t=\t=" << endl;
	}

	// VARIABLES
//	mpz_t A2, A1, A; // A = A_i, A1 = A_i-1, A2 = A_i-2
//	mpz_t B2, B1, B; // B = B_i, B1 = B_i-1, B2 = B_i-2
	mpz_t G2, G1, G; // G = G_i, G1 = G_i-1, G2 = A_i-2

	int P1, P; // P1 = P_i-1, P = P_i;
	int Q1, Q; // Q1 = Q_i-1, Q = Q_i;
	int a1, a;
	int D, index;
	double root; // sqrt(D)
};

void findX(int D, mpz_t& x) { // finds min X that solves x2 - D*y2 = 1;
	// D can't be a square
	PQa a(D);

	while (true) {
		a.advance();
		if (a.Q == 1)
			break;
	}

	if (a.index % 2 == 1) {
		int loop = a.index;
		for (int i = 1; i < loop; i++)
			a.advance();
		mpz_set(x, a.G);
	} else
		mpz_set(x, a.G1);
}

int main() {
	mpz_t maxX;
	mpz_init(maxX);
	mpz_set_ui(maxX, 0);

	int maxD;
	for (int d = 2; d <= 1000; d++)
		if (d != (int) sqrt(d) * (int) sqrt(d)) {
			mpz_t temp;
			mpz_init(temp);
			findX(d, temp);
			if (mpz_cmp(temp, maxX) > 0) {
				maxD = d;
				mpz_set(maxX, temp);
			}
		}

	gmp_printf("X : %Zd\n", maxX);
	cout << "D : " << maxD << endl;

	double diff = (double) clock() / (double) CLOCKS_PER_SEC;
	cout << endl << diff << endl;

}

