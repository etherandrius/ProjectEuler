#include <iostream>

int sum85(int a, int b){
	int result = 0;
	for(int x = 0; x < a; x++)
		for(int y = 0; y < b; y++)
			result += (a-x)*(b-y);
	return result;
}

int main(){

	int limit = 2000000;

	int minD = 1000000000;
	int area = 0;
	for (int a = 1;; a++) {
		int current = sum85(a, a);
		if (current >= limit) {
			int localD = current - limit;
			if (localD < minD) {
				minD = localD;
				area = a * a;
			}
			break;
		}
		for (int b = a + 1;; b++) {
			current = sum85(a, b);
			if (current >= limit) {
				int localD = current - limit;
				if (localD < minD) {
					minD = localD;
					area = a * b;
				}
				current = sum85(a, b - 1);
				localD = limit - current;
				if (localD < minD) {
					minD = localD;
					area = a * (b - 1);
				}
				break;
			}
		}
	}

	std::cout << area << std::endl;
	std::cout << minD << std::endl;

}

















