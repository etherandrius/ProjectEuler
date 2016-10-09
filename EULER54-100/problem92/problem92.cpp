#include <iostream>
#include <vector>
#include <cmath>

// calculates number of 89 chains below limit
const int limit = 10000000;
const int digitsN = log10(limit);
const int Vlimit = digitsN * 81 + 1;
std::vector<int> chain(Vlimit, 0);
int result = 0;
const int square[] = { 0, 1, 4, 9, 16, 25, 36, 49, 64, 81 };

int WPAAA = 0;

void advance(int& m) {
	int z = 0;
	while (m > 0) {
		z += square[m % 10];
		m /= 10;
	}
	m = z;
}

void completeChain(int& n) {
	int m = n;
	while (chain[m] == 0) {
		advance(m);
	}
	chain[n] = chain[m];
}

void permutation(std::vector<int> digits, int depth = 0) {

	if (depth <= digitsN) {
		if (depth > 0) {
			const int fact[] = { 1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880 };

			int m = 0;
			for (unsigned int i = 0; i < digits.size(); i++)
				m += square[digits[i]];
			if (chain[m] == 89) {
				std::vector<int> weigth(1, 1);
				for (unsigned int i = 1, ix = 0; i < digits.size(); i++) {
					if (digits[i] == digits[i - 1])
						++weigth[ix];
					else {
						++ix;
						weigth.push_back(1);
					}
				}

				int add;

				if (digits[0] != 0) {
					add = fact[digits.size()];
					for (unsigned int i = 0; i < weigth.size(); i++) {
						add /= fact[weigth[i]];
					}
				} else {
					// special case when zero is in detected, since zero cannot be a lead number
					add = 1;
					int size = digits.size() - 1;
					for (int i = 0; i < weigth[0]; i++) {
						add *= size;
						--size;
					}
					add /= fact[weigth[0]];
					++size;
					for (unsigned int i = 1; i < weigth.size(); i++) {
						for (int j = 0; j < weigth[i]; j++) {
							add *= size;
							--size;
						}
						add /= fact[weigth[i]];
					}
				}
				result += add;
			}
		}

		//recursion here;
		int L = 0;
		if (depth > 0)
			L = digits[depth - 1];

		digits.push_back(0);
		for (int i = L; i < 10; i++) {
			digits[depth] = i;
			permutation(digits, depth + 1);
		}

	}
}

int main() {

	chain[0] = -1;
	chain[1] = 1;
	chain[89] = 89;

	for (int i = 1; i < Vlimit; i++)
		completeChain(i);

	std::vector<int> digits;
	permutation(digits);

	std::cout << '\n' << result;

	double diff = (double) clock() / (double) CLOCKS_PER_SEC;
	std::cout << std::endl << diff << std::endl;
}

