#include <iostream>
#include <cmath>
#include <vector>

#include "InfInt.h"

using namespace std;

struct Cubes{

	InfInt n; // the cube;
	int pre; // pre cube
	int dig[10]; // digits;
	bool check;
};

bool operator==(const Cubes &a, const Cubes &b){
	for(int i =0; i<10; i++)
		if(a.dig[i] != b.dig[i])
			return false;
	return true;
}


void countDigits(Cubes& cube);


int main(){
	const int limit = 10000;
	Cubes cubes[limit];

	for(int i = 1; i < limit; i++){
		cubes[i].pre = i;
		cubes[i].n = i;
		cubes[i].n *= i*i;
		countDigits(cubes[i]);
		cubes[i].check = false;
	}

	double diff = (double) clock() / (double) CLOCKS_PER_SEC;
	cout << diff << endl;


	for(int i = 0; i < limit; i++){
		int p = 1;
		vector<Cubes> array;
		for(int j = i+1; j < limit; j++){
			if(cubes[j].check == false)
			if(cubes[i] == cubes[j]){
				++p;
				cubes[j].check=true;
				array.push_back(cubes[j]);
			}
		}

		if(p>4){
			cout<< cubes[i].pre << " "  <<  cubes[i].n << "\t" << p << " : ";
			for(int j = 0; j< 10; j++)
				cout << cubes[i].dig[j] << " ";
			cout << endl;
			for(unsigned int j=0; j < array.size(); j++)
				cout << array[j].pre << "\t";
			cout << endl;
			break;
		}
		array.clear();
	}

	diff = (double) clock() / (double) CLOCKS_PER_SEC;
	cout << diff << endl;

}

void countDigits(Cubes& cube){
	for(int i = 0; i < 10; i++)
		cube.dig[i]=0;
	for(unsigned int i = 0; i < cube.n.numberOfDigits(); i++)
		++cube.dig[(int)cube.n.digitAt(i)];
}

















