#include <iostream>
#include <vector>
#include <algorithm>

const int LIMIT = 1000000;
std::vector<int> chain(LIMIT+1, 0);
std::vector<int> d(LIMIT+1);

int dd(int n){
	int sum=1;
	int i = 2;
	for(;i*i < n; i++)
		if( n%i == 0){
			sum += i;
			sum += n/i;
		}
	if( n%i == 0 ) // here i*i == n => i = n/i
		sum += i;

	return sum;
}

void produce_chain(const int n){
	if( chain[n] != 0 || n > LIMIT)
		return;

	int a = n;
	a = d[a];

	std::vector<int> local;
	local.push_back(n);
	if( a < LIMIT )
	local.push_back(a);

	bool million = false;

	if( n >= LIMIT || a >= LIMIT)
		million = true;

	int length = 1;
	if(million == false)
	while( a != n && a != 1){
		length++;
		a = d[a];

		if( std::find(local.begin(), local.end(), a) != local.end() )
			break;

		if( a < LIMIT ){
			local.push_back(a);
			if( chain[a] != 0 ){
				length = chain[a];
				break;
			}
			local.push_back(a);
		}
		else{ 	million = true;
				break;
		}
	}

	if(million == true) length = -2;

	if( a == n )
	for(unsigned int i = 0; i < local.size(); i++)
		chain[local[i]] = length;
	else{ // a != n

		unsigned int i = 0;
		while( local[i] != a && i < local.size() )
			if( chain[local[i]] == 0)
			chain[local[i++]] = -3;
			else break;

		if( length != -2 )
		if( chain[a] == 0)
		length -= i;

		while(i < local.size())
			if( chain[local[i]] == 0)
			chain[local[i++]] = length;
			else break;
	}

}


int main(){

	for(unsigned int i = 0; i < d.size(); i++)
		d[i] = dd(i);

	for(unsigned int n = 1; n < chain.size(); n++)
		produce_chain(n);

	int maxLength = 0;
	int smallestMember = 0;
	for(unsigned int i = 1; i < chain.size(); i++)
		if( chain[i] > maxLength ){
			smallestMember = i;
			maxLength = chain[i];
		}

	std::cout << smallestMember << std::endl;
}

