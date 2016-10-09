// needs -std=c++11
#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

void analyse(vector<int> keys);

bool rulesL[10][10]; //
bool rulesR[10][10];

int main() {

	for(int i = 0; i < 10; i++)
		for(int j = 0; j < 10; j++){
			rulesL[i][j] = 0;
			rulesR[i][j] = 0;
		}

	vector<int> keys = { 319, 680, 180, 690, 129, 620, 762, 689, 762, 318, 368,
			710, 720, 629, 168, 160, 716, 731, 736, 729, 316, 769, 290, 719,
			318, 389, 162, 289, 162, 718, 790, 890, 362, 319, 760, 380, 728 };

	analyse(keys);

	for(int i = 0; i < 10; i++){

		cout << endl << "9 8 7 6 5 4 3 2 1 0  : i :  0 1 2 3 4 5 6 7 8 9" << endl;
		for(int j = 9; j >=0; j--)
		cout << rulesL[i][j] << " ";
		cout << " : " << i << " :  ";
		for(int j = 0; j < 10; j++)
		cout << rulesR[i][j] << " ";
		cout << endl;

		// NOT FINISHED
		//finished the problem by hand

	}

}

void analyse(vector<int> keys){

	for(unsigned int i = 0; i < keys.size(); i++){
		int dig3 = keys[i]%10;
		keys[i] /= 10;
		int dig2 = keys[i]%10;
		keys[i] /= 10;
		int dig1 = keys[i]%10;

		rulesR[dig1][dig2] = 1;
		rulesR[dig1][dig3] = 1;

		rulesR[dig2][dig3] = 1;
		rulesL[dig2][dig1] = 1;

		rulesL[dig3][dig1] = 1;
		rulesL[dig3][dig2] = 1;
	}

}






//int keys[] = { 319, 680, 180, 690, 129, 620, 762, 689, 762, 318, 368, 710,
//		720, 710, 629, 168, 160, 689, 716, 731, 736, 729, 316, 729, 729,
//		710, 769, 290, 719, 680, 318, 389, 162, 289, 162, 718, 729, 319,
//		790, 680, 890, 362, 319, 760, 316, 729, 380, 319, 728, 716 };
