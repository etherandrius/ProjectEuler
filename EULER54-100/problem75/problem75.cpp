#include <iostream>
#include <cmath>

using namespace std;

bool isSquare(long n);
int GCD(int a, int b){
	if(b==0)
		return a;
	else return GCD(b, a%b);
}

int result = 0;

int main(){

	int LM = 1500000;
	int L[LM];
	for(int i = 0; i < LM; i++)
		L[i] = 0;

	for(int m = 2;; m++){
		if( 2*m*(m+m%2+1) > LM)
			break;
		for(int n = m%2+1; n < m; n+=2){
			if(GCD(n, m) != 1) continue;
			int sum = 2*m*(m+n);
			if(sum > LM){
				break;
			}
			else{
				++L[sum];
				int csum = sum+sum;
				while(csum <= LM){
					++L[csum];
					csum +=sum;
				}
			}
		}
	}


	int result = 0;
	for(int i = 2; i < LM; i+=2)
		if(L[i] == 1) result++;

	cout << result << endl;

	double diff = (double) clock() / (double) CLOCKS_PER_SEC;
	cout << endl << diff << endl;
}

bool isSquare(long n){


	if( n<0 || (n&2)!= 0 || (n&7)==5 || (n&11)==8)
		return false;
	else{
		double temp = sqrt(n);
		return temp == (int)temp;
	}


}













