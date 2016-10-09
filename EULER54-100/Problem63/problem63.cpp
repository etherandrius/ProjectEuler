#include <iostream>
#include <cmath>

using namespace std;

int numLen(int base, int power);

int main(){

	int result = 1;

	for ( int i = 2; i < 10; i++)
		for(int j = 1;; j++)
		{
			int ln = numLen(i, j);
			if(ln < j)
				break;
			if(ln == j)
				result++;
		}

	cout << result << endl;

	double diff = (double) clock() / (double) CLOCKS_PER_SEC;
	cout << diff << endl;

}

int numLen(int base, int power){

	int result = 1;
	double bass = (double)base;

	while(power > 0)
		if(bass >= 10)
		{
		++result;
		bass /= 10;
		}
		else
		{
				bass*=base;
				--power;
		}

	return result;
}











