#include <iostream>

const int N = 50;
int result = 3*N*N; // all triangles that have a vertex on the edge of the square
//int result = 0;

int GCD(int a, int b){
 if(b == 0)
	 return a;
 else return GCD(b, a%b);
}

void updatePoint(int& x, int& y){ // cordinate x, y is always the point with the right angle
// assumes x > y, adds results for y < x , because symmetry
	int gcd = GCD(x, y);

	if(x+y > N && gcd == 1)
		return;
	int dy = x/gcd;
	int dx = y/gcd;

	int X = x + dx;
	int Y = y - dy;

	while( Y >= 0 && X <= N){
		result +=2;
		X += dx;
		Y -= dy;
	}

	X = x - dx;
	Y = y + dy;
	while(Y <= N && X >= 0){
		result +=2;
		X -= dx;
		Y += dy;
	}

}

int main(){

	for(int y = 1; y <= N; y++)
		for(int x = y+1; x <=N; x++){
		std::cout << x << " " << y << '\n';
				updatePoint(x, y);
		}

	if(N%2 == 0)
		result += N*N/2;
	else result += (N-1)*(N-1)/2;

	std::cout<< result;

	double diff = (double) clock() / (double) CLOCKS_PER_SEC;
	std::cout << std::endl << diff << std::endl;
}










