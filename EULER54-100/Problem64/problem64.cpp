#include <iostream>
#include <cmath>
#include <vector>

using namespace std;

struct frac{ // a_k+1 = (int)(1/b_k), b_k+1 = (1/b_k) - a_k+1; b_k = (sqrt(n)-e)/f;
	int	n;
	double	root;
	vector<int> a, f, e;
};

void advance(frac& a, int& i);
int findPeriod(int n);

int main(){

	int limit = 10000;
	int result = 0;

	for(int i = 2; i <= limit-2; i++)
	{
		if( i == (int)sqrt(i)*(int)sqrt(i)){
			continue;
		}
			if (findPeriod(i) % 2 == 1) {
				++result;
			}
	}

	cout << result  << endl;

	double diff = (double) clock() / (double) CLOCKS_PER_SEC;
	cout << diff << endl;
}

void advance(frac& a, int & i){ // a_k+1 = (int)(1/b_k), b_k+1 = (1/b_k) - a_k+1; b_k = (sqrt(n)-e)/f;
	/*
	 * advances or "unwraps" a fraction 1 level deeper.
	 *
	 * let a_k be represent an integer k levels deep.
	 * let b_k represent the non integer part k levels deep.
	 *
	 * a_k+1 and b_k+1 only depend on b_k.
	 *
	 * a_k+1 is integer part of 1/b_k
	 * b_k+1 is non-integer part of 1/b_k
	 *
	 * since b_k < 1,  1/b_k > 1.
	 * which implies a_k+1 exists;
	 *
	 * 	f_K+1 -> (n - e_k^2) / f_k
	 * 	a_K+1 -> ( sqrt(n) + e_k) * f / (n - e_k^2)
  	 * 	e_K+1 -> |( e_k - (a_K+1 * f_K+1) )|
	 *
	 *
	 *	NOTE : function assumes that n-e*e|f.
	 *	I was not able to prove this claim
	 *	however it is true for at least n <= 10000.
	 */
		a.f.push_back((a.n-a.e[i]*a.e[i])/a.f[i]);
		a.a.push_back((a.root+a.e[i])/a.f[i+1]);
		a.e.push_back(a.e[i]-a.a[i+1]*a.f[i+1]);
		a.e[i+1]  = abs(a.e[i+1]);
}

int findPeriod(int n){ // assumes n is		for(int j = 1; j < i; j++){ square-less;
	frac A;
	A.n = n;
	A.root = sqrt(n);
	A.a.push_back(A.root);
	A.e.push_back(A.a[0]);
	A.f.push_back(1);
	for(int i = 0;; i++){
		advance(A, i);

		for(int j = 1; j < i; j++){
			if(A.a[i] == A.a[j])
				if(A.f[i] == A.f[j])
					if(A.e[i] == A.e[j])
						return i-j;
		}
	}
	return -1;
}







