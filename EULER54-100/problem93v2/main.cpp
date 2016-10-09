#include <iostream>
#include <list>
#include <vector>

//// generate a number set;
//// iterate on the number set
/// save numbers and keys in std::list<algebra>
//	every list elements of list<algebra> holds a key for operation behind them;
//// create keys for every set
// first key is always null
//// simulate every possible parenthesis;
//// calculate every possible arrangement

// Not finished;


const char keys[] = {'+', '-', '*', '/'};

struct algebra{
	int n;
	char key;
};

void insertSortedUnique(std::list<int>& state, int n){

	std::list<int>::iterator it = state.begin();
	while( *it < n && it != state.end()) ++it;
	if( *it != n )
		state.insert(it, n);
}

void calculate(std::list<int>& state, std::list<algebra> finalSet, int order[4], int depth = 1) // simulates parenthesis
{
	if(depth == 4){
		order[0] = 0;





		return;
	}





}



void solve(){



}

void printListInt(std::list<int> A){
	std::list<int>::iterator it = A.begin();
	while(it != A.end()){
		std::cout << *it << " ";
		++it;
	}
	std::cout << '\n';
}
int main(){
	std::list<int> A;
	std::list<int>::iterator it = A.begin();
	A.push_back(1);
	A.push_back(2);
	A.push_back(3);
	A.push_back(4);
	A.push_back(5);
	A.push_back(6);

	printListInt(A);
	it++;
	it++;

	std::cout << *it << '\n';
	A.erase(it);
	it++;
	A.erase(it);

	printListInt(A);

}












