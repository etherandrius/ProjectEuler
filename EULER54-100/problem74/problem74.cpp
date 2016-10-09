#include <iostream>
#include <vector>

using namespace std;

int advance(int n, int fact[]);

int main(){
	// 169 => 3
	// 871 => 2
	// 872 => 2
	int fact[] = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
	bool curious[1000000];
	for(int i = 0;  i < 1000000; i++)
		curious[i] = 1;
	curious[145] = 0;
	curious[169] = 0, curious[363601] = 0, curious[1454] = 0;
	curious[871] = 0, curious[45361]  = 0;
	curious[872] = 0, curious[45362]  = 0;


	int result = 0;

//	169 → 363601 → 1454 → 169
//	871 → 45361 → 871
//	872 → 45362 → 872

//	int h = 70;
//	int p = 14558;
//	while(h > 0){
//	cout << p << endl;
//	p = advance(p);
//	h--;
//	}

	for(int i = 69; i < 1000000; i++){
		int length = 1;
		if(curious[i] == 1){
			int temp = i;
			while(true){
				temp = advance(temp, fact);
				if(temp < 1000000)
				curious[temp] = 0;
				if(temp == 871 || temp == 872 || temp == 45361 || temp == 45362){
					length += 2;
					break;
				}
				if(temp == 169 || temp == 363601 || temp == 1454){
					length += 3;
					break;
				}
				if(temp == 145 || temp == 2 || temp == 40585){
					++length;
					break;
				}
				++length;
			}
		}
		else continue;

		//cout << i << ", " << length << endl;

		if(length == 60)
			++result;
	}// 1.93543

	cout << result << endl;

	double diff = (double) clock() / (double) CLOCKS_PER_SEC;
	cout << endl << diff << endl;

}

int advance(int n, int fact[]){
	int result = 0;
	while(n>0){
		result += fact[n%10];
		n /= 10;
	}
	return result;
}











