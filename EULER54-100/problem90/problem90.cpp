#include <iostream>
#include <vector>

int result = 0;

// checks if a given number n can be made from cube1 and cube2
bool contains(int n, std::vector<bool> cube1, std::vector<bool> cube2){
	int d1 = n/10;
	int d2 = n%10;
	return ( cube1[d1] && cube2[d2] ) || ( cube2[d1] && cube1[d2] );
}

// checks if sets cube1 and cube2 comply with problem90
bool isCurious90(std::vector<bool> cube1, std::vector<bool> cube2) {
	int unique[] = { 1, 4, 25, 81 };
	int pairs[] = { 6, 9, 16, 19, 36, 39, 49, 46, 64, 94 };

	if (cube1[6] == false && cube1[9] == false && cube2[6] == false
			&& cube2[9] == false)
		return false;

	for (int i = 0; i < 4; i++)
		if (contains(unique[i], cube1, cube2) == false)
			return false;

	for (int i = 0; i < 10; i += 2)
		if (contains(pairs[i], cube1, cube2) == false
				&& contains(pairs[i + 1], cube1, cube2) == false)
			return false;

	return true;
}

// solves problem90
void problem90(std::vector<bool> cube1, std::vector<bool> cube2, int CS1 = 0, int CS2 = 0,bool dS = 0, int depth = 0) // CS = cube size
{
	if( (CS1 == 6 && CS2 == 6 ) || depth == 10){
		if((CS1 == 6 && CS2 == 6 )){

		if(isCurious90(cube1, cube2))
			++result;

		return;
		}
	}
	else{

		if(depth > 0){
			if(dS == 0){ // dS = duplicate State
			for(int i = 0; i < depth; i++){ // removes duplicates
				if(cube1[i] < cube2[i])
					return;
				else if(cube1[i] != cube2[i]){
					dS = 1;
						break;
				}
			}
			}


			if (depth < 7 || depth == 9) // removes invalid sets
				if (cube1[depth - 1] == false && cube2[depth - 1] == false)
					return;

			if(depth > 4) // removes sets that would not reach CS = 6
			{
				int constrain = 4 - depth;
				if( constrain + CS1 < 0  || constrain + CS2 < 0 )
					return;
			}

		}

		problem90(cube1, cube2, CS1, CS2, dS, depth+1);			// 0 0

		if(CS2 < 6 && CS1 < 6){
			cube1[depth] = 1;
			cube2[depth] = 1;
			problem90(cube1, cube2, CS1+1, CS2+1, dS, depth+1); 	// 1 1
		}

		if(CS1 < 6){
			cube1[depth] = 1;
			cube2[depth] = 0;
			problem90(cube1, cube2, CS1+1, CS2, dS, depth+1);		// 1 0
		}

		if(CS2 < 6){
			cube1[depth] = 0;
			cube2[depth] = 1;
			problem90(cube1, cube2, CS1, CS2+1, dS, depth+1);		// 0 1
		}

		return;
	}

}

int main(){
	std::vector<bool> cube1(10, 0);
	std::vector<bool> cube2(10, 0);

		problem90(cube1, cube2);
		std::cout << result;

		double diff = (double) clock() / (double) CLOCKS_PER_SEC;
		std::cout << std::endl << diff << std::endl;
}
