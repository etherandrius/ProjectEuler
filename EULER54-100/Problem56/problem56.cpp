#include "InfInt.h"

using namespace std;

int digitSum(InfInt number) {
	int sum = 0;
	for (unsigned int i = 0; i < number.numberOfDigits(); i++)
		sum += (int) number.digitAt(i);
	return sum;
}

int main() {

	int max = 0;
	int Base;
	int Power;

	for (int i = 2; i < 100; i++)
		for (int j = 2; j < 100; j++) {
			if (i % 10 == 0)
				break;

			InfInt number = 1;
			for (int k = 0; k < j; k++)
				number *= i;

			int maxT = digitSum(number);
			if (maxT > max) {
				max = maxT;
				Base = i;
				Power = j;
			}
		}

// Output
	cout << max << endl;
	cout << Base << endl;
	cout << Power << endl;

// 	Running time
//	double diff = (double)clock() / (double)CLOCKS_PER_SEC;
//	cout<< diff << endl;

}

