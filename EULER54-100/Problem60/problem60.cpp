#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

const int rot = 5;

bool isPrime(long long n);
long long concade(int a, int b); // concade
bool isCurious60(int A[rot], vector<int> primes , int index);
void rotate(vector<int>& sums, vector<int>& primes, int A[], int start,int &limit, int depth = 1);

int main(){

	const int primeLimit = 9000;


	bool sieve[primeLimit];
	for(int i = 0; i < primeLimit; i++)
		sieve[i] = 1;


	vector<int> primes;
	for(int i = 2; i < primeLimit; i++)
		if(sieve[i] == 1){
			primes.push_back(i);

			for(int j = i+i; j < primeLimit; j+=i)
				sieve[j] = 0;
		}

	int ln = primes.size();
	vector<int> sums;
	int A[rot];
	for( A[0] = 1; A[0] < ln; A[0]++)
		rotate(sums, primes, A , A[0]+1, ln, 1);

	sort(sums.begin(), sums.end());

	cout << endl;
	cout << endl <<  "sums : ";
	for(unsigned int i = 0; i < sums.size(); i++)
		cout << sums[i] << ", ";
	cout << endl;


	double diff = (double) clock() / (double) CLOCKS_PER_SEC;
	cout << diff << endl;


}


void rotate(vector<int>& sums, vector<int>& primes, int A[], int start, int &limit, int depth){
	if(depth  == rot)
	{
		int tamp = 0;
		for(int i = 0; i<rot; i++)
			tamp+=primes[A[i]];
		sums.push_back(tamp);
		for(int i = 0; i < rot ; i++)
			cout << primes[A[i]] << " ";
		cout << endl;
		return;
	}
	else{
		for( A[depth] = start; A[depth] < limit; A[depth]++)
			if(isCurious60(A, primes, depth)){
				rotate(sums, primes, A, A[depth]+1, limit, depth+1);
			}
		return;
	}
}

bool isCurious60(int A[rot], vector<int> primes, int index){



	for(int i = 0; i < index; i++)
		if(isPrime(concade(primes[A[i]], primes[A[index]])) == false)
			return false;
		else if(isPrime(concade(primes[A[index]], primes[A[i]])) == false)
			return false;

	return true;
}

bool isPrime(long long n){
	if(n == 2)
		return true;
	else
		if(n%2==0)
			return false;
	for(long long i = 3; i*i<=n; i+=2)
		if(n%i ==  0)
			return false;
	return true;
}

long long concade(int a, int b){
	for(long long i = 10;; i*=10)
		if(b < i)
			return a*i + b;
	return -1;
}














