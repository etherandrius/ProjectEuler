#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>

std::vector<int> power[3]; // 0 - square, 1 - cube; 2 - forth power
const int limit = 50000000;
const int limit2 = sqrt(limit)+1;
const int limit3 = pow(limit, 1.0/3.0)+1;
const int limit4 = pow(limit, 1.0/4.0)+1;


void init(){
//	int limit = 4;
	bool sieve[limit2];

	for(int i = 0; i < limit2; i++)
		sieve[i] = 1;

	for(int i = 2; i < limit2; i++)
		if(sieve[i] == 1)
			for(int j = i+i; j < limit2; j+=i)
				sieve[j] = 0;

	for(int i = 2; i < limit2; i++)
		if(sieve[i] > 0)
			power[0].push_back(i*i);

	for(int i = 2; i < limit3; i++)
		if(sieve[i])
			power[1].push_back(i*i*i);

	for(int i = 2; i < limit4; i++)
		if(sieve[i])
			power[2].push_back(i*i*i*i);

}


int main(){
	init();

	std::vector<int> sum;


//	for(unsigned int a = 0; a < power[0].size(); a++)
//		std::cout << power[0][a] << ' ';
//	std::cout << '\n';
//
//	for(unsigned int a = 0; a < power[1].size(); a++)
//		std::cout << power[1][a] << ' ';
//	std::cout << '\n';
//
//	for(unsigned int a = 0; a < power[2].size(); a++)
//		std::cout << power[2][a] << ' ';
//	std::cout << '\n';

	for(unsigned int a = 0; a < power[0].size(); a++)
		for(unsigned int b= 0; b < power[1].size(); b++)
			for(unsigned int c = 0; c < power[2].size(); c++){
				int tempSUM = power[0][a]+power[1][b]+power[2][c];
				if(tempSUM <= limit)
				sum.push_back(tempSUM);
			}
	std::sort(sum.begin(), sum.end());

//	for(unsigned int i = 0; i < sum.size(); i++)
//		std::cout << sum[i]  << ' ';
//	std::cout << '\n';


	int result = 1;
	int nonUnique = 0;
	for(unsigned int i = 1; i < sum.size(); i++)
		if(sum[i-1] != sum[i])
			++result;
		else ++nonUnique;

	std::cout << result << std::endl;
	std::cout << nonUnique << std::endl;

}











