#include <iostream>
#include <fstream>
#include <cmath>

class exponent{
public:
	exponent(int base, int pow, int line = 0){
		this->base = base;
		this->pow = pow;
		this->log = (double)pow * std::log(base);
		this->line = line;
	};
	int base;
	int pow;
	double log;
	int line;
};

exponent max(1, 1, 0);

bool operator>(const exponent& a, const exponent& b){
	return a.log > b.log;
}

void findMax(){

	std::fstream input("p099_base_exp.txt");
	if(input.is_open()){
		int base;
		int pow;
		char comma;
		int line = 1;

	while(input >> base >> comma >> pow){
		exponent T(base, pow, line++);
		if( T > max )
			max = T;
	}
	}
}

int main(){
	findMax();
	std::cout << max.base << '^';
	std::cout << max.pow << '\n';
	std::cout << max.log << '\n';
	std::cout << max.line << '\n';
}














