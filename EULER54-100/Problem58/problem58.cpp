#include <iostream>
#include <cmath>

using namespace std;

bool isPrime(int n);

int main() {

	int primes = 0;
	int total = 1;
	for (int i = 3, square = 1;; i += square + 1) {

		if (i % 2 == 1)
			if ((int) sqrt(i) * (int) sqrt(i) == i)
				square = sqrt(i);

		if (isPrime(i))
			++primes;
		++total;

		if (((double) primes / (double) total) < 0.1) {
			cout << "answer : " << sqrt(i) << "// only exact if i is square"
					<< endl;

			break;
		}

	}

//	 	Running time
		double diff = (double)clock() / (double)CLOCKS_PER_SEC;
		cout<< diff << endl;

}

bool isPrime(int n) {
	if (n == 2)
		return true;
	if (n % 2 == 0)
		return false;
	for (int i = 3; i * i <= n; i += 2)
		if (n % i == 0)
			return false;
	return true;
}

