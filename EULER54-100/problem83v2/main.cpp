// a variant of breath first search keyed for path sum
#include <iostream>
#include <fstream>
#include <list>

struct que{
	int x;
	int y;
	int sum;
};

const int size = 80;
int state[size][size];

void insert(std::list<que>& Q, que q){
	std::list<que>::iterator it = Q.begin();
	while(it->sum < q.sum && it != Q.end()) ++it;
	Q.insert(it, q);
}

void input(int input[size][size]){
	std::ifstream in("p083_matrix.txt");
	char a;
	if(in.is_open()){
	for(int i = 0; i < size; i++){
		for(int j = 0; j < size-1; j++){
			in >> input[j][i];
			in >> a;
		}
		in >> input[size-1][i];
	}
	}
	else std::cout << "did not open file" << std::endl;
}

void init(){
	for(int x = 0; x < size; x++)
		for(int y = 0; y < size; y++)
			state[x][y] = 0;
}

void advance(int data[size][size], std::list<que>& Q){
	int * x = &Q.begin()->x;
	int * y = &Q.begin()->y;

	if(state[*x][*y] != 0){
		Q.pop_front();
		return;
	}

	data[*x][*y] = Q.begin()->sum;
	state[*x][*y] = 1;
	que next;
	next.y = *y;
	next.x = *x + 1;
	if (next.x < size && state[next.x][next.y] == 0){
		next.sum = data[*x][*y] + data[next.x][next.y];
		insert(Q, next);
	}

	next.x = *x - 1;
	if (next.x >= 0 && state[next.x][next.y] == 0){
		next.sum = data[*x][*y] + data[next.x][next.y];
		insert(Q, next);
	}

	next.x = *x;
	next.y = *y + 1;
	if (next.y < size && state[next.x][next.y] == 0){
		next.sum = data[*x][*y] + data[next.x][next.y];
		insert(Q, next);
	}

	next.y = *y - 1;
	if (next.y >= 0 && state[next.x][next.y] == 0){
		next.sum = data[*x][*y] + data[next.x][next.y];
		insert(Q, next);
	}

	Q.pop_front();
}

void spread(int data[size][size]){

	std::list<que> Q;
	que index; index.x = 0; index.y = 0; index.sum = data[0][0];
	Q.push_back(index);

//	while(Q.size() != 0 )
	while( Q.begin()->x != size -1 || Q.begin()->y != size -1)
		advance(data, Q);
	if(Q.size() != 0)
		advance(data, Q);
    std::cout << data[size -1][size -1]<< std::endl;
}

int main(){
	int data[size][size];
	input(data);
	init();
	spread(data);
}
