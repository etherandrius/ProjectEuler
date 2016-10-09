#include <iostream>
#include <fstream>
#include <vector>
#include <limits.h>

const int size = 80;

void input(std::vector<int> input[size]) {
	std::ifstream in("p082_matrix.txt");
	char a;
	int b;
	if (in.is_open()) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size-1; j++) {
				in >> b;
				input[i].push_back(b);
				in >> a;
			}
			in >> b;
			input[i].push_back(b);

		}

	} else
		std::cout << "did not open file" << std::endl;
	in.close();
}

int main() {

	std::vector<int> data[size];
	input(data);

	for (int y = size - 2; y > 0; y--)
		for (int x = 0; x < size; x++) {
			int sum;
			if (x > 0)
				sum = data[x - 1][y] + data[x][y];
			else
				sum = INT_MAX;

			int sum2 = 0;
			for (int d = x; d < size && sum2 < sum; d++) {
				sum2 += data[d][y];
				sum = std::min(sum, sum2 + data[d][y + 1]);
			}
			data[x][y] = sum;
		}

	int result = INT_MAX;
	for (int x = 0; x < size; x++)
		result = std::min(data[x][0] + data[x][1], result);

	std::cout << result << std::endl;

	double diff = (double) clock() / (double) CLOCKS_PER_SEC;
	std::cout << std::endl << diff << std::endl;

}

