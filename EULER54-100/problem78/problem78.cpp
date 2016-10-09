#include <iostream>
#include <vector>

using namespace std;

void p(int n);
void genPent();

const int divisor = 1000000;
const int LM = 100000;
int P[LM]; // saving  p(n)%divisor so some values of P[LM] will be negative
vector<int> pent;

int main(){
	genPent();
	P[0] = 1;
	for(int i = 1; i < LM; i++)
		P[i] = -1;

	for(int i = 4; i < LM; i+=5){
		p(i);
	//	cout << i << "\t" << P[i] << endl;
		if(P[i] == 0){
			cout << i << "\t" << P[i] << endl;
			break;
		}
	}

	double diff = (double) clock() / (double) CLOCKS_PER_SEC;
	cout << endl << diff << endl;
}

void p(int n){
	if(n < 0 || n > LM) return;
	if(P[n] != -1){
		return;
	}
	long PP = 0;
	for(unsigned int i = 0, sign = 0; i <= pent.size(); i++)
	if(pent[i] <= n){
		p(n - pent[i]);
		if(sign < 2) PP += P[n - pent[i]];
		else PP -= P[n - pent[i]];
		sign = (sign+1)%4;
	} else break;

	P[n] = PP%divisor;
	return;

}

void genPent(){
	for(int i = 1;; i++){
		pent.push_back((i*(3*i-1))/2);
		int temp = (i*(3*i+1))/2;
		pent.push_back(temp);
		if(temp > LM) break;
	}
}
