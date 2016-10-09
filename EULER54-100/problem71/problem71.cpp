#include <iostream>

using namespace std;

int gcd(int a, int b){

	if(b == 0)
		return a;
	else return gcd(b, a%b);

}

int main(){

	// Calculates smallest fraction before N/D , where d <= limit

	// TODO DEL
	cout << gcd(8, 2) << endl;
	cout << gcd(6000, 12006) << endl;
	cout << 6000 / gcd(6000, 12006) << "  " << 12006 / gcd(6000, 12006) << endl;

	// TODO DEL

	int N = 5998, D = 12000, limit = 12000;

	int n = 1, d = 3;

	int k = (limit - d)/D;
	d += k * D;
	n += k * N;

	int tN = n;
	while(7*n<3*(d)){
		tN = n;
	while(7*tN<3*d) ++tN;
		--d;
	}
	cout << --tN << "/" << ++d << endl;

}















