#include <iostream>

using namespace std;

void permute60(int A[], int limit = 10, int depth = 0);
bool isMagic(int A[]);
void printMagic(int A[]);

int main(){


	int A[10];
	permute60(A);


}

void printMagic(int A[]){

	cout << A[0] + A[5] + A[6] << " :: ";
	cout << A[0] << "," << A[5] << "," << A[6] <<"; ";
	cout << A[1] << "," << A[6] << "," << A[7] <<"; ";
	cout << A[2] << "," << A[7] << "," << A[8] <<"; ";
	cout << A[3] << "," << A[8] << "," << A[9] <<"; ";
	cout << A[4] << "," << A[9] << "," << A[5] <<";"<< endl;
	cout << "\t" << A[0];
	for(int i = 1; i <10; i++)
		cout << "," << A[i];
	cout << endl << endl;

}


bool isMagic(int A[]){

	int sum = A[4] + A[5] + A[9];

	for(int i = 0; i < 4; i++){
		int sumT = A[i] + A[i+5] + A[i+6];
		if (sumT != sum)
			return false;

	}



	return true;
}

void permute60(int A[], int limit, int depth){

	if (limit == depth) {

		if(isMagic(A)){
			printMagic(A);
		}

		return;
	} else {
		if(depth > 4)
		{
			for(int i = 0; i < 4; i++)
				if(A[0] >= A[i+1])
					return;

			if(!(A[0] == 10 || A[1] == 10 || A[2] == 10 || A[3] == 10 || A[4] == 10))
				return;
		}

		for(int i = 1; i <= 10; i++)
		{
			for(int j = 0; j < depth; j++)
				if(i == A[j])
					goto CONTINUE;

			A[depth]=i;
			permute60(A, limit, depth+1);

			CONTINUE:;
		}
		return;
	}
}














