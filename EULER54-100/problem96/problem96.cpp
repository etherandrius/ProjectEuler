#include <iostream>
#include <vector>
#include <fstream>
#include <string>
#include <algorithm>
#include <list>

struct gridCell {
	int val; // current value
	bool state[10]; // if state[i] is false i cannot be held there
};

bool isCorrectSet(const std::vector<int*> set){
	std::vector<int> values(10, 0);
	for(int i = 0; i < 9; i++)
		++values[*set[i]];
	if(values[0] != 0)
		return false;
	for(int i = 1; i < 10; i++)
		if( values[i] != 1)
			return false;
	return true;
}

bool isCorrectGrid(std::vector< std::vector<gridCell> >& grid){

	for(int x = 0; x < 9; x++){
		std::vector<int*> set(9);
		for(int y = 0, i = 0; y < 9; y++, i++)
			set[i] = &grid[x][y].val;
		if( !isCorrectSet(set) )
			return false;
	}

	for(int y = 0; y < 9; y++){
		std::vector<int*> set(9);
		for(int x = 0, i = 0; x < 9; x++, i++)
			set[i] = &grid[x][y].val;
		if( !isCorrectSet(set) )
			return false;
	}


	for(int Lx = 0; Lx < 9; Lx+=3)
		for(int Ly = 0; Ly < 9; Ly +=3){
			std::vector<int*> set(9);
			for(int x = Lx, i = 0; x < Lx+3; x++)
				for(int y = Ly; y < Ly+3; y++, i++)
					set[i] = &grid[x][y].val;
			if( !isCorrectSet(set) )
				return false;
		}



	return true;
}

void printGrid(std::vector< std::vector<gridCell> >& grid) {

	for (int y = 0; y < 9; y++) {
		if (y % 3 == 0)
			std::cout << "_-_-_-_-_-_-_-_-_-_-_-_-_" << std::endl;

		for (int x = 0; x < 9; x++) {
			if (x % 3 == 0)
				std::cout << "| ";

			std::cout << grid[x][y].val << ' ';
		}
		std::cout << "|\n";
	}
	std::cout << "_-_-_-_-_-_-_-_-_-_-_-_-_" << std::endl;
}

void updateGrid(std::vector< std::vector<gridCell> >& grid, int& x, int& y) {
	// updates lines
	for (int k = 0; k < 9; k++) {
		grid[x][k].state[grid[x][y].val] = false;
		grid[k][y].state[grid[x][y].val] = false;
	}

	// updates squares
	int Lx = x - (x % 3);
	int Ly = y - (y % 3);

	for(int X = Lx; X < Lx+3; X++)
		for(int Y = Ly; Y < Ly+3; Y++)
			if( grid[X][Y].val == 0)
				grid[X][Y].state[grid[x][y].val] = false;
}

bool isSubset_ordered(const gridCell * a,const gridCell * b){
	// returns true if b is a subset of a
	for(int i = 1; i < 10; i++)
		if(b->state[i] == true)
			if( a->state[i] ==false)
				return false;
	return true;
}

void updateAdvancedSet(std::vector<gridCell*>& set, bool& state){
	//// update advanced
	/// 1:: example of a naked pair: in a set of 1-9. if cell #1 an only have (7, 9) as options
	/// 2:: and cell #2 can also only have (7, 9) as options then,
	/// 3:: cells 3-9 can't have (7, 9) as options
	// a set := a line or a square

	// converts set[n].status[i] to a list cells[n];
	int setSize = set.size();
	std::list<int> cells[setSize];
	for (int n = 0; n < setSize; n++)
		if (set[n]->val == 0)
			for (int i = 1; i < 10; i++)
				if (set[n]->state[i] == true)
					cells[n].push_back(i);

	// Iterates thru the cell list and finds naked pairs;
	std::vector<bool> okList(setSize, 1); // only allows a single check
	unsigned int cellSize; // required to update status;
	int n = 0;
	while (n < setSize) {
		if( okList[n] == true )
		if (cells[n].size() > 0) {
			okList[n] = false;
			std::list<int> ix; // list of indexes of cells who's .status[i] is a subset of  cells[n].status[i];
			ix.push_back(n);
			for (int i = n + 1; i < setSize; i++)
				if (set[i]->val == 0)
					if(isSubset_ordered( set[n], set[i] ))
						ix.push_back(i);

			if (ix.size() == cells[n].size()) {// if number of lists that are a subset of cells[n], is equal to the number of elements in cells[n]
				for (int i = 0; i < setSize; i++)
					if (std::find(ix.begin(), ix.end(), i) == ix.end()) {

						if (state == false)
							cellSize = cells[i].size();

						std::list<int>::iterator it = cells[n].begin();
						while (it != cells[n].end()) {
							cells[i].remove(*it);
							set[i]->state[*it] = false;
							it++;
						}

						if (state == false)
							if (cellSize > cells[i].size())
								state = true;
					}

				n = 0;
				continue;
			}
		}
		n++;
	}
}

bool updateAdvancedSquare(std::vector< std::vector<gridCell> >& grid){
	// prepares a set for updateAdvancedSet
	bool state = false; // tracks if a change was made
	for(int Lx = 0; Lx < 9; Lx+=3)
		for(int Ly = 0; Ly < 9; Ly+=3){
			std::vector<gridCell*> set(9);
			int index = 0;
			for(int x = Lx; x < Lx + 3; x++)
				for(int y = Ly; y < Ly + 3; y++){
					set[index++] = &grid[x][y];
				}
			updateAdvancedSet(set, state);
		}
	return state;
}

bool updateAdvancedLines(std::vector< std::vector<gridCell> >& grid){
	// Prepares a set for updateAdvancedSet
	bool state = false; // tracks if a change was made
	//Horizontal
	for(int i = 0; i < 9; i++){
		std::vector<gridCell*> set(9);
		for(int a = 0; a < 9; a++)
			set[a] = &grid[a][i];
		updateAdvancedSet(set, state);
	}

	//Vertical
	for(int i = 0; i < 9; i++){
		std::vector<gridCell*> set(9);
		for(int a = 0; a < 9; a++)
			set[a] = &grid[i][a];
		updateAdvancedSet(set, state);
	}

	return state;
}

void initGrid(std::vector< std::vector<gridCell> >& grid) {

	for (int x = 0; x < 9; x++)
		for (int y = 0; y < 9; y++) {
			grid[x][y].state[0] = false;
			for (int i = 1; i < 10; i++)
				grid[x][y].state[i] = true;
		}

	for (int x = 0; x < 9; x++)
		for (int y = 0; y < 9; y++)
			if (grid[x][y].val != 0)
				updateGrid(grid, x, y);
}

bool checkBasic(std::vector< std::vector<gridCell> >& grid) {
	bool state = false; // tracks if a change was made
	for (int x = 0; x < 9; x++)
		for (int y = 0; y < 9; y++)
			if (grid[x][y].val == 0) {

				int cc = 0;
				int val;
				for (int i = 1; i < 10; i++)
					if (grid[x][y].state[i] == true) {
						cc++;
						val = i;
					}

				if (cc == 1) {
					state = true;
					grid[x][y].val = val;
					updateGrid(grid, x, y);
				}
			}
	return state;
}

bool checkLine(std::vector< std::vector<gridCell> >& grid) {
	bool state = false; // tracks if a change was made
	// vertical lines
	for (int x = 0; x < 9; x++) {
		std::vector<int> sum(10, 0);
		for(int y = 0; y < 9; y++)
			if(grid[x][y].val == 0){
				for(int i = 1; i < 10; i++)
					++sum[grid[x][y].state[i]];
			}
			else sum[grid[x][y].val] = 2;

		// find if sum contains value 1
		for(int i = 1; i < 10; i++)
			if(sum[i] == 1){
				state = true;
				// finds the single element that can take value of i in the line
				for(int y = 0; y < 9; y++)
					if(grid[x][y].state[i] == true){
						grid[x][y].val = i;
						updateGrid(grid, x, y);
						break;
					}
			}

	}

	// horizontal lines
	for (int y = 0; y < 9; y++) {
		std::vector<int> sum(10, 0);
		for(int x = 0; x < 9; x++)
			if(grid[x][y].val == 0){
				for(int i = 1; i < 10; i++)
					++sum[grid[x][y].state[i]];
			}
			else sum[grid[x][y].val] = 2;

		// find if sum contains value 1
		for(int i = 1; i < 10; i++)
			if(sum[i] == 1){
				state = true;
				// finds the single element that can take value of i in the line
				for(int x = 0; x < 9; x++)
					if(grid[x][y].val == 0)
					if(grid[x][y].state[i] == true){
						grid[x][y].val = i;
						updateGrid(grid, x, y);
						break;
					}
			}
	}

return state;
}

bool checkSquare(std::vector< std::vector<gridCell> >& grid){
	bool state = false; // tracks if a change was made

	for(int Ly = 0; Ly < 7; Ly+=3)
		for(int Lx = 0; Lx < 7; Lx+=3){

			std::vector<int> sum(10, 0);
			for(int x = Lx; x < Lx+3; x++)
			for(int y = Ly; y < Ly+3; y++)
					if( grid[x][y].val == 0){
						for(int i = 1; i < 10; i++)
							if(grid[x][y].state[i] == true)
							++sum[i];
					}
					else sum[grid[x][y].val] = 2;

			for(int i = 1; i < 10; i++){
				if( sum[i] == 1){
					state = true;
				for(int x = Lx; x < Lx+3; x++)
				for(int y = Ly; y < Ly+3; y++){
					if(grid[x][y].val == 0)
					if( grid[x][y].state[i] == true){
						grid[x][y].val = i;
						updateGrid(grid, x, y);
						goto SKIP;
					}
				}
				SKIP:;
				}
			}
		}
	return state;
}

bool deduce(std::vector< std::vector<gridCell> >& grid) {
	if(checkBasic(grid)) 	return true;
	if(checkLine(grid))  	return true;
	if(checkSquare(grid))	return true;
	if(updateAdvancedSquare(grid)) return true;
	if(updateAdvancedLines(grid))  return true;
	return false;
}

void getGrid(std::fstream& input, std::vector< std::vector<gridCell> >& grid){
	std::string line;
	getline(input, line);
	for(int y = 0; y < 9; y++){
		getline(input, line);
		for(int x = 0; x < 9; x++){
			grid[x][y].val = line[x] - '0';
		}
	}
	initGrid(grid);
}

bool guessGrid(std::vector< std::vector<gridCell> > grid_copy, int value, int x, int y){

	grid_copy[x][y].val = value;
	updateGrid(grid_copy, x, y);
	while( deduce(grid_copy) );
	return isCorrectGrid(grid_copy);
}

void guessAndSolve(std::vector< std::vector<gridCell> >& grid,
				   std::vector< std::vector<gridCell> >  grid_copy,
				   bool state = false, int x = 0, int y = 0){

	if( state == true)
		return;

	if( grid_copy[x][y].val == 0){

		for(int i = 1; i < 10; i++)
			if( grid_copy[x][y].state[i] == true )
				if ( guessGrid( grid_copy, i, x, y)){
					state = false;

					grid[x][y].val = i;
					updateGrid(grid, x, y);
					while( deduce(grid) );

					return;
				}


	}

	if( x < 8 )
	guessAndSolve(grid, grid_copy, state, x + 1, y);
	if( y < 8 )
	guessAndSolve(grid, grid_copy, state, x, y + 1);

}

int main() {

	int RESULT = 0;

	std::fstream input("p096_sudoku.txt");
	if(!input.is_open()){
		std::cout << " File was not opened \n";
		return 0;
	}

	int grid_number = -1;
	while(input.eof() == false){ 	//MASTER LOOP FOR ALL GRIDS
		++grid_number;

	std::vector< std::vector<gridCell> > grid(9, std::vector<gridCell>(9)); // grid is stored here
	getGrid(input, grid);

	while (deduce(grid)); // deduce() returns boolean value;

	if( ! isCorrectGrid(grid) ) 	//CHECK IF FINISHED
	guessAndSolve(grid, grid);

//	std::cout << "\t -GRID " << grid_number << "-";
	if( ! isCorrectGrid(grid) ){} 	//CHECK IF FINISHED
//		std::cout << " - WRONG \n";
	else{
//		std::cout << '\n';
		RESULT += 100 * grid[0][0].val;
		RESULT += 10 * grid[1][0].val;
		RESULT += grid[2][0].val;
	}
//	printGrid(grid);


	// GUESS OR FIND FINAL ANSWER RECURSIVELY

	}

	std::cout << RESULT << '\n';

	double diff = (double) clock() / (double) CLOCKS_PER_SEC;
	std::cout << std::endl << diff << std::endl;

}

