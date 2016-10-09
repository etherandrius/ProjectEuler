#include <iostream>
#include <vector>
#include <cmath>

int M = 0;
int belowM = 0;

void getX(double& min, double& a, double& b, double& c){
	double x = (a*b)/(b+c);
	if( x >= 0 && x < a)
		min = std::min(min, (sqrt(x*x+b*b) + sqrt( (a-x)*(a-x) + c*c)));
}

bool isCurious85(int & a, int & b){ // returns true if cube's AxBxC shortest path is an Integer
	double min = a+b+M;

	double A = a;
	double B = b;
	double C = M;
	getX(min, A, B, C);
	getX(min, C, A, B);
	getX(min, B, C, A);

	double epsilon = 0.00000001;
	return min - floor(min + epsilon) < epsilon;
}

void advance(){
	++M;
	for(int a = 1; a <= M; a++) // c = M;
		for(int b = a; b <= M; b++)
				if(isCurious85(a, b)){
					++belowM;
					std::cout << a << " " << b << " " << M << " " << belowM << std::endl;
				}
}

int main(){ // !! SOLUTION IS NOT OPTIMAL AND TAKES OVER A MINUTE
	int million = 1000000;

	while(belowM < million)
		advance();
	std::cout << "B : "<< belowM << std::endl;
	std::cout << "M : "<< M << std::endl;
}
