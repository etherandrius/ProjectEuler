#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int phi(int n, vector<int>& primes);
void Esieve(vector<int>& primes, int up, int down = 2);

int main(){

	int limit = 1000000;
	vector<int> primes;
	Esieve(primes, limit + 1);

	long long result = 0;

	for(int i = 2; i <= limit; i++){
		if(binary_search(primes.begin(), primes.end(), i)){
			result += i - 1;
		}
		else{
			result += phi(i , primes);
		}

	}
	cout << result << endl;

	double diff = (double) clock() / (double) CLOCKS_PER_SEC;
	cout << endl << diff << endl;

}

int phi(int n, vector<int>& primes){ // assumes n is not prime
	int N = n;

	for(int i = 0; primes[i] <= n; i++){
		if(n%primes[i] == 0){
			N /= primes[i];
			N *= primes[i] - 1;
			n/=primes[i];
			while(n%primes[i] == 0){
				n/=primes[i];
			}
		}
	}
	return N;
}


void Esieve(vector<int>& primes, int up, int down){
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














