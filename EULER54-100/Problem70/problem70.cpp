#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;

bool isPerm(int a, int b);
void Esieve(int down, int up, vector<int>& primes);


int main(){

	int square = sqrt(10000000);
	int delta = 1500;
	vector<int> primes;
	Esieve(square-delta, square+delta, primes);

	cout << "STOP"<<endl;


	for(unsigned int i  = 0; i < primes.size(); i++)
		for(unsigned int j = i + 1; j < primes.size(); j++)
		{
			int n = primes[i] * primes[j];
			if (n < 10000000) {
				int phi = (primes[i] - 1) * (primes[j] - 1);
				if (isPerm(n, phi))
					cout << " n : " << n << ", phi(n) : " << phi
							<< ", difference : " << n - phi << ", ratio : "
							<< (double) n / (double) phi << endl;
			}
		}





}

void Esieve(int down, int up, vector<int>& primes){
	int index = 2;
	bool sieve[up];
	for(int i = 0; i < up; i++)
		sieve[i] = 1;

	for( ;index < down; index++)
		if(sieve[index] == 1)
			for(int j = index+index; j < up; j+=index)
				sieve[j] = 0;

	for( ;index < up; index++)
		if(sieve[index] == 1){
			primes.push_back(index);
			for(int j = index+index; j < up; j+=index)
				sieve[j] = 0;
	}
}

bool isPerm(int a, int b) {
	int aD[10], bD[10];
	for (int i = 0; i < 10; i++) {
		aD[i] = 0;
		bD[i] = 0;
	}
	while (a > 0) {
		++aD[a % 10], a /= 10;
	}
	while (b > 0) {
		++bD[b % 10], b /= 10;
	}

	for (int i = 0; i < 10; i++)
		if (aD[i] != bD[i])
			return false;
	return true;
}
