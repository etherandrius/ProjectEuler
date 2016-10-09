#include <iostream>

using namespace std;
void count(int sum, int depth); // depth = sum
long p(int n, int m);

int result = 0;
const int LM = 1000;
long P[LM][LM];

int main(){

	for(int i = 0; i < LM; i++){
		P[i][1] = 1;
		P[i][0] = 0;
		for(int j = 2; j < LM; j++)
			P[i][j] = -1;
	}

	cout << p(100, 100) << endl;
//	count(100, 100);
//	cout << result << endl;

	double diff = (double) clock() / (double) CLOCKS_PER_SEC;
	cout << endl << diff << endl;
}

long p(int n, int m){

	if(m < 0 || n < 0)
		return 0;
	if(n == 0)
		return 1;
	if(m > n)
		return p(n, n);

	if(P[n][m] != -1)
		return P[n][m];

	P[n][m] = 0;

	for(int i = 1; i <= m; i++)
		P[n][m] += p(n-i, i);

	return P[n][m];
}

void count(int sum, int depth){
	if(depth == 1)
	{
		result++;
		return;
	}
	else{
		for(int i = sum; i >= 0; i-=depth)
			count(i, depth-1);
	}
}









