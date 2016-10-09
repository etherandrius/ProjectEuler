#include <iostream>
#include <vector>

using namespace std;

int result = 0;

void countSUB(int sum, int depth, vector<int> primes);
void count(int sum, vector<int> primes){
	int index = 0;
	for(;; index++)
		if(primes[index] > sum)
			break;
	--index;
	if(sum == primes[index])
		result--;

	countSUB(sum, index, primes);
}
void Esieve(int limit, vector<int>& primes);


int main(){

	vector<int> primes;
	Esieve(145, primes);

	for(int i = 10;; i++){
		result = 0;
		count(i, primes);
		cout << i  << " :  " << result << endl;
		if(result > 5000){
		break;
		}
	}
//	count(10, primes);

	cout << result << endl;


	double diff = (double) clock() / (double) CLOCKS_PER_SEC;
	cout << endl << diff << endl;
}

void countSUB(int sum, int depth, vector<int> primes){
	if(depth == 0)
	{
		if( sum%2 == 0)
			++result;
		return;
	}
	else{
		for(int i = sum; i >= 0; i-=primes[depth])
			countSUB(i, depth-1, primes);
	}
}

void Esieve(int limit, vector<int>& primes){
	bool sieve[limit];
	for(int i = 2; i < limit; i++)
		sieve[i] = 1;

	for(int i = 2; i < limit; i++)
		if(sieve[i]){
			primes.push_back(i);
			for(int j =i+i; j < limit; j+=i)
				sieve[j] = 0;
		}
}

















