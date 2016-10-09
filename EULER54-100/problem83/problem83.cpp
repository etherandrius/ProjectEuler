#include <iostream>
#include <fstream>
#include <vector>
#include <limits.h>
#include <iterator>
#include <cmath>


//WORKS BUT SHOULD TAKE WELL OVER 2 HOURS
bool state[size][size];

class cord{
public:
	cord(int x, int y){
		this->x = x;
		this->y = y;
	}
	void set(int x, int y){
		this->x = x;
		this->y = y;
	}

	int x;
	int y;
};

bool comp(const cord& a, const cord& b){
	return ( a.x == b.x && a.y == b.y);
}

bool contains(const std::vector<cord>& a, const cord& b){
	for(unsigned int i = 0; i < a.size(); i++)
		if(comp(a[i], b))
			return true;
	return false;
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

bool nearMiss(const std::vector<cord>& a, const cord& b){

	for(int i = 0; i < (int)a.size() -1; i++){
		int dx = std::abs(a[i].x - b.x);
		int dy = std::abs(a[i].y - b.y);
		if( (dx ==1 && dy == 0) || ( dx ==0 && dy ==1))
			return true;
	}
	return false;
}

void initt(){
	for(int x = 0; x < size; x++)
		for(int y = 0; y < size; y++)
			state[x][y] = 0;
	state[size-1][size-1] = 1;
}

void baseSum(int data[80][80]){
	// prepares so I don't have edge cases
	for(int i = 78; i >=0; i--){
		data[79][i] += data[79][i+1];
		data[i][79] += data[i+1][79];
	}
	//.
	for(int i = 77; i >=0; i--)
		for(int j = 1; j < 79-i; j++){
			int x = i+j, y = 79-j;
			data[x][y] += std::min(data[x+1][y], data[x][y+1]);
		}
	for(int i = 78; i >=0; i--){
		for(int j = 0; j <= i; j++){
			int x = j;
			int y = i-j;
			data[x][y] += std::min(data[x+1][y], data[x][y+1]);
		}
	}
}

void spread(int data[size][size],std::vector<cord> path, std::vector<cord>& finalPath, cord current, int sumLoc, int& sum, cord delta){

	if( current.x >= size || current.x < 0 ||  current.y >= size || current.y < 0)
		return;

	if( contains(path, current) || nearMiss(path, current))
		return;

	sumLoc += data[current.x][current.y];
	if(sumLoc > sum)
		return;

	path.push_back(current);

	if(path[0].x > path[0].y){
		if(delta.x > 5){
			return;
		}
	}else if(path[0].x > path[0].y){
		if(delta.y > 5){
			return;
		}
	}

	if(state[current.x][current.y] == 1){

		sum = std::min(sum, sumLoc);
		finalPath = path;

		return;
	}

	current.y++; 	delta.y++;
	spread(data, path, finalPath, current, sumLoc, sum, delta); // x, y+1
// ----------------------------------
	current.y--; 	delta.y--;
	current.x++;	delta.x++;
	spread(data, path, finalPath, current, sumLoc, sum, delta); // x+1, y
// ----------------------------------
	current.x -= 2; delta.x -= 2;
	spread(data, path, finalPath, current, sumLoc, sum, delta); // x-1, y
// ----------------------------------
	current.y--; 	delta.y--;
	current.x++; 	delta.x++;
	spread(data, path, finalPath, current, sumLoc, sum, delta); // x, y-1

}

void populate(const std::vector<cord> path, int data[size][size]){
	if(path.size() == 0)
		return;
	for(int i = path.size()-2; i >= 0; i--){
		data[path[i].x][path[i].y] += data[path[i+1].x][path[i+1].y];
		state[path[i].x][path[i].y] = 1;
	}
}

void sum83(int data[size][size]){

	int baseS[size][size];
	std::copy(&data[0][0], &data[0][0] + size*size, &baseS[0][0]);
	baseSum(baseS);


	std::vector<cord> path;
	std::vector<cord> finalPath;

//	int x = 77, y = 77;
	cord origin(0, 0);
//	spread(data, path, origin, 0, baseS[x][y]);
	cord delta(0, 0);
	for(int i = 2; i<size; i++){
		for( int j = 1; j < i; j++){
			int x = size-i;
			int y = x+j;
			origin.set(x, y);
			//std::cout << x << " " << y << std::endl;
			spread(data, path, finalPath, origin, 0, baseS[x][y], delta);
			populate(finalPath, data);
//			data[x][y] = baseS[x][y];
//			state[x][y] = 1;
			origin.set(y, x);
			//std::cout << y << " " << x << std::endl;
			spread(data, path, finalPath, origin, 0, baseS[y][x], delta);
			populate(finalPath, data);
//			data[y][x] = baseS[y][x];
//			state[y][x] = 1;

		}

		int x = size - i;
		origin.set(x, x);
		spread(data, path, finalPath, origin, 0, baseS[x][x], delta);
		populate(finalPath, data);

	}

	std::cout << baseS[0][0] << std::endl;
	origin.set(0, 0);
	spread(data, path, finalPath, origin, 0, baseS[0][0], delta);
	std::cout << baseS[0][0] << std::endl;

}

int main(){

	int data[size][size];
	input(data);
	initt();

    std::cout << std::endl;

	sum83(data);

    std::cout << std::endl;
    for(int i = 60; i < size; i++){
        for(int j = 60; j < size; j++)
        	std::cout << state[j][i] << " ";
        std::cout << std::endl;
        }



	return 0;
}


