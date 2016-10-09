#include <iostream>

using namespace std;

long long result = 0;

void populate(int d1, int d2, int limit); // for when numerators are both 1;
void populate(int n1, int d1, int n2, int d2, int limit);
int gcd(int a, int b){
	if(b==0)
		return a;
	else return gcd(b, a%b);
}

void printFarreyS(int limit);

int main(){

	// finds number of fractions between 1/d1 and 1/d2,,, when denominator <= limit
	populate(3, 2, 12000);
	cout << "result : "<< --result << endl;

	printFarreyS(10);
	double diff = (double) clock() / (double) CLOCKS_PER_SEC;
	cout << endl << diff << endl;

}

void printFarreyS(int limit ){
	populate(0, 1, 1, 1, limit);
	cout << 1 << "/" << 1 << endl;
}

void populate(int n1, int d1, int n2, int d2, int limit){
	if(d1+d2<=limit){
		int GCD = gcd(n1+n2, d1+d2);
		populate(n1, d1, (n1+n2)/GCD, (d1+d2)/GCD, limit);
		populate((n1+n2)/GCD, (d1+d2)/GCD, n2, d2, limit);
	}
	else cout << n1 <<"/" << d1 << /*" "<< n2 << "/" << d2 <<*/ ", ";
}


void populate(int d1, int d2, int limit){

	if(d1+d2<=limit){
		populate(d1, d1+d2, limit);
		populate(d1+d2, d2, limit);
	}
	else result++;
}
