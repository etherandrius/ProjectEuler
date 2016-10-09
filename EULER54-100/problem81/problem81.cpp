#include <iostream>
#include <fstream>

void input(int input[80][80]){
	std::ifstream in("p081_matrix.txt");
	char a;
	if(in.is_open()){
	for(int i = 0; i < 80; i++){
		for(int j = 0; j < 79; j++){
			in >> input[j][i];
			in >> a;
			//std::cout << a << " ";
		}
		in >> input[79][i];
	}

	}
	else std::cout << "did not open file" << std::endl;
}

void sum81(int data[80][80]){
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

int main(){
	int data[80][80];
	input(data);

	int down = 0;
	int up = 12;


	for(int i = down; i < up; i++){
			for(int j = down; j < up; j++)
				std::cout << data[j][i] << " ";
			std::cout << std::endl;
		}

	std::cout << "-\t-\t-\t-\t" << std::endl;

	sum81(data);
	std::cout << data[0][0] << std::endl;

	std::cout << "-\t-\t-\t-\t" << std::endl;


	for(int i = down; i < up; i++){
		for(int j = down; j < up; j++)
			std::cout << data[j][i] << " ";
		std::cout << std::endl;
	}


}













