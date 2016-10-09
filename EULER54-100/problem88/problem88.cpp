#include <iostream>
#include <vector>
#include <algorithm>

const int klimit = 12000; 		// k limit;
const int nlimit = 2 * klimit;	// n limit

std::vector<int> primes; // storing primes for global access
std::vector<int> kValue(klimit + 1); // stores minimum n value that satisfies requirements;

void init() {
	for (int i = 2, value = 4; i <= klimit; i++, value += 2)
		kValue[i] = value; // kValue[i] = 2*i; // worst case

	bool sieve[nlimit];
	for (int i = 2; i < nlimit; i++)
		sieve[i] = 0;

	for (int i = 2; i < nlimit; i++)
		if (sieve[i] == 0) {
			primes.push_back(i);
			for (int j = i + i; j < nlimit; j += i)
				sieve[i] = 1;
		}
}

void factor(std::vector<int>& factors, int n) {
	for (int i = 0; primes[i] <= n; i++)
		if (n % primes[i] == 0) {
			factors.push_back(primes[i]);
			n /= primes[i];
			while (n % primes[i] == 0) {
				n /= primes[i];
				factors.push_back(primes[i]);
			}
		}
}

void update(std::vector<int> factors, int& n, std::vector<int> groups,
		unsigned int maxGroup = 1, unsigned int index = 1) {
	if (index == factors.size()) {

		if (maxGroup != 1) {
			std::vector<int> A(maxGroup, 1);

			for (unsigned int i = 0; i < groups.size(); i++)
				A[groups[i]] *= factors[i];

			int sum = 0;
			for (unsigned int i = 0; i < A.size(); i++)
				sum += A[i];

			int kI = n - sum + maxGroup;
			if (kI < (int) kValue.size())
				kValue[kI] = std::min(kValue[kI], n);

		}

		return;

	} else {
		int L = 0;

		if (factors[index] == factors[index - 1]) // eliminates trivial duplicates
			L = groups[index - 1];

		for (unsigned int i = L; i < maxGroup; i++) {
			groups[index] = i;
			update(factors, n, groups, maxGroup, index + 1);
		}
		groups[index] = maxGroup;
		update(factors, n, groups, maxGroup + 1, index + 1);

		return;
	}
}

void factorAndUpdate(int& n) {
	std::vector<int> factors;
	factor(factors, n);

	if (factors.size() == 1)
		return;

	std::vector<int> group(factors.size(), 0);
	update(factors, n, group);
}

int main() {
	init();

	for (int n = 2; n < nlimit; n++)
		factorAndUpdate(n);

	std::sort(kValue.begin(), kValue.end());
	int result = kValue[0];

	for (unsigned int i = 1; i < kValue.size(); i++)
		if (kValue[i] != kValue[i - 1])
			result += kValue[i];

	std::cout << result;
}

