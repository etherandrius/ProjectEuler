// problem only concerns 4 digit numbers;

#include <iostream>
#include <cmath>

using namespace std;

int front(int a);
int back(int a);
int P(int P, int n);
void problem61(int order[6],int values[],const int min[],const int max[], int depth = 1,int limit = 6);

int main() {
//	cout  << "3 \t"  << P(3, 45) << "\t" << P(3, 140) <<endl;
//	cout  << "4 \t"  << P(4, 32) << "\t" << P(4,99) <<endl;
//	cout  << "5 \t"  << P(5, 26) << "\t" << P(5,81) <<endl;
//	cout  << "6 \t"  << P(6, 23) << "\t" << P(6,70) <<endl;
//	cout  << "7 \t"  << P(7, 21) << "\t" << P(7,63) <<endl;
//	cout  << "8 \t"  << P(8, 19) << "\t" << P(8,58) <<endl;


//	cout << P(3, 127) <<endl;
//	cout << P(4, 91)  << endl;
//	cout << P(5, 44)  << endl;


//	for (int P3 = 45; P3 < 141; P3++)
//	if (back(P(3, P3)) >= 10)
//		for (int P4 = 32; P4 < 100; P4++)
//		if (back(P(4, P4)) >= 10){
//		if(front(P(4, P4)) > back(P(3,P3))){
//				break;
//		}
//		else
//			if(front(P(4, P4)) == back(P(3,P3))){//del
//			for (int P5 = 26; P5 < 82; P5++)
//			if (back(P(5, P5)) >= 10){
//			if(front(P(5, P5)) > back(P(4, P4))){
//				break;
//			}
//			else
//				if(front(P(5, P5)) == back(P(4, P4)))
//				for (int P6 = 23; P6 < 71; P6++)
//				if (back(P(6, P6)) >= 10){
//				if(front(P(6, P6)) > back(P(5, P5))){
//					break;
//				}
//				else
//					if(front(P(6, P6)) == back(P(5, P5)))
//					for (int P7 = 21; P7 < 64; P7++)
//					if (back(P(7, P7)) >= 10){
//					if(front(P(7, P7)) > back(P(6, P6))){
//						break;
//					}
//					else
//						if(front(P(7, P7)) == back(P(6, P6)))
//						for (int P8 = 19; P8 < 59; P8++)
//						{//del
//				//			cout << P(3, P3) << "\t" << P(4, P4) << "\t" << P(5, P5) << "\t" << P(6, P6) << "\t" << P(7, P7) << "\t" << P(8, P8) << "\t" << endl;
//
//							if( back(P(8, P8)) >= 10)
//							if (back(P(8, P8)) == front(P(3, P3)))
//							if(front(P(8, P8)) == back(P(7, P7))){
//
//								cout << "JACK : "<<P3 << "\t" << P4 << "\t" << P5 << "\t" << P6 << "\t" << P7 << "\t" << P8 << "\t" << endl;
//								cout << "JACK : "<<P(3, P3) << "\t" << P(4, P4) << "\t" << P(5, P5) << "\t" << P(6, P6) << "\t" << P(7, P7) << "\t" << P(8, P8) << "\t" << endl;
//
//
//
//								}}}}}
	const int min[9] = {0,0,0,45,32,26,23,21,19};	 // inclusive
	const int max[9] = {0,0,0,141,100,82,71,64,58}; // not inclusive
	int order[]  = {3,5,4,6,7,8};
	int values[] = {0,0,0,0,0,0};

	for(int a3 = 3; a3 < 9; a3++)
		for(int a4 = 3; a4 < 9; a4++)
			if(a4 != a3)
			for(int a5 = 3; a5 < 9; a5++)
				if(a5 != a4 && a5 != a3)
				for(int a6 = 3; a6 < 9; a6++)
					if(a6 != a5 && a6 != a4 && a6 != a3)
					for(int a7 = 3; a7 < 9; a7++)
						if(a7 != a6 && a7 != a5 && a7 != a4 && a7 != a3)
						for(int a8 = 3; a8 < 9; a8++)
						if(a8 != a7 && a8 != a6 && a8 != a5 && a8 != a4 && a8 != a3){
							order[0] = a3; order[1] = a4; order[2] = a5;
							order[3] = a6; order[4] = a7; order[5] = a8;
//	for(int i = 0; i<6; i++)
//		cout << order[i] << "\t";
//	cout << endl;
//


	for(values[0] = min[order[0]]; values[0] < max[order[0]]; values[0]++)
		if(back(P(order[0], values[0])) >= 10)
			problem61(order, values, min, max);
	}


	double diff = (double) clock() / (double) CLOCKS_PER_SEC;
	cout << diff << endl;

}



void problem61(int order[], int values[],const int min[],const int max[], int depth,int limit){

	if(depth == limit){

		if (back(P(order[depth-1], values[depth-1])) == front(P(order[0], values[0]))){
			int sum = 0;
		for(int i = 0; i < limit; i++){
			cout<< P(order[i], values[i]) << "\t";
			sum +=P(order[i], values[i]);
		}
		cout << "sum : " << sum;
		cout << endl;
		double diff = (double) clock() / (double) CLOCKS_PER_SEC;
		cout << diff << endl;
		}
		return;
	}
	else
	{
		int pback = back(P(order[depth-1], values[depth-1])); // back of previous number

		for( values[depth] = min[order[depth]]; values[depth] < max[order[depth]]; values[depth]++)
			if(back(P(order[depth], values[depth])) >= 10){
				int cfront = front(P(order[depth], values[depth])); // front of current number
				if( pback < cfront ){
					break;
				}
				else if ( pback == cfront){
					problem61(order, values, min, max, depth+1, limit);
					continue;
				}
			}

		return;
	}
}


int P(int P, int n){
	if(P == 3)
		return  n*(n+1)/2;
	if(P == 4)
		return n*n;
	if(P == 5)
		return n*(3*n-1)/2;
	if(P == 6)
		return n*(2*n-1);
	if(P == 7)
		return n*(5*n-3)/2;
	if(P == 8)
		return n*(3*n-2);
	return 0;
}


int back(int a) {
	return a % 100;
}
int front(int a) {
	return (a / 100);
}

