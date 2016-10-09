#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>
#include <list>

// REDO PROPERLY
// works but by luck
//needed special cases for (a*b) + (c*d)
// and a/(b+c)

// still does not compute (a+b) * (c+d)


const int size = 4; // set size * main.cpp * main.cpp




int maxN = 0;
std::vector<int> abcd(4, 0);

void insertOrderedUnique(std::list<int>& state, int n){

	std::list<int>::iterator it = state.begin();
	while( *it < n && it != state.end()) ++it;
	if( *it != n )
		state.insert(it, n);
}

void solveSingle(std::list<int>& state, std::vector<double> set, double sum = 0, int depth = 0){

	if(depth == size){
		if( sum == floor(sum) && sum > 0)
			insertOrderedUnique(state, sum);
		return;
	}
		solveSingle(state, set, sum + set[depth], depth+1);
		solveSingle(state, set, sum - set[depth], depth+1);
		solveSingle(state, set, sum * set[depth], depth+1);
		solveSingle(state, set, sum / set[depth], depth+1);
		if(sum != 0)
		solveSingle(state, set, set[depth] / sum, depth+1);

}

void solvePerm(std::list<int>& state, std::vector<double> set, std::vector<double> temp, int depth = 0){
	//temp the same size as set
	if(depth == size){
		solveSingle(state, temp);
		//edge case when parenthesis are not nested
		double suma = temp[0] * temp[1];
		double sumb = temp[2] * temp[3];
		double suma2 = temp[0] / temp[1];
		double sumb2 = temp[2] / temp[3];

		insertOrderedUnique(state, suma + sumb);
		if(suma2 == floor(suma2)){
			insertOrderedUnique(state, suma2 + sumb);
		}
		if(sumb2 == floor(sumb2)){
			insertOrderedUnique(state, suma + sumb2);
		}
		if(sumb2 == floor(sumb2) && suma2 == floor(suma2)){
			insertOrderedUnique(state, suma2 + sumb2);
		}
	}
	else{
		for(int i = 0; i < size; i++){
			for(int j = 0; j < depth; j++)
				if( temp[j] == set[i] ){
					goto SKIP;
				}
			temp[depth] = set[i];
			solvePerm(state, set, temp, depth+1);
			SKIP:;
		}

	}
}

void solve(){

	std::vector<double> set(size, 0);
	std::vector<double> temp(size, 0);
	std::list<int> state;

	for(set[0] = 1; set[0] < 7; set[0]++)
	for(set[1] = set[0]+1; set[1] < 8; set[1]++)
	for(set[2] = set[1]+1; set[2] < 9; set[2]++)
	for(set[3] = set[2]+1; set[3] < 10; set[3]++){
		solvePerm(state, set, temp);

		int prev = *state.begin();
		std::list<int>::iterator it = state.begin();
		while( it != state.end()){
			++it;
			if(prev + 1 != *it){
				if( prev > maxN ){
					maxN = prev;
					abcd[0] = set[0];
					abcd[1] = set[1];
					abcd[2] = set[2];
					abcd[3] = set[3];
				}
				break;
			}
			prev = *it;
		}
		state.clear();
	}
}


int main(){

	solve();

	for(int i = 0; i < 4; i++)
		std::cout << abcd[i] << ' ';
	std::cout << '\n' << maxN << '\n';


}

















