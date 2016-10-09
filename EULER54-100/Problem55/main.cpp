#include <cmath>
#include <iostream>
#include <string>
#include <gmp.h>

#include "InfInt.h"

using namespace std;

bool isPalindrome(unsigned long long n);
unsigned long long reverse(unsigned long long n);

bool isPalindrome(InfInt n);
InfInt reverse(InfInt n);

int main() {

	int sum = 0;

	for (int i = 1; i < 10000; i++) {
		InfInt number = i;
		for (int j = 0; j < 50; j++) {
			number += reverse(number);
			if (isPalindrome(number)) {

				goto place;
			}
		}
		sum++;
		place:;
	}

	cout << sum << endl;

}

InfInt reverse(InfInt n) {
	string result = "";
	string nn = n.toString();
	for (unsigned int i = n.numberOfDigits(); i > 0; i--)
		result += nn[i - 1];

	return result;
}

bool isPalindrome(InfInt n) {
	int length = n.numberOfDigits();
	for (int i = 0; i < length / 2; i++)
		if (n.digitAt(i) != n.digitAt(length - i - 1))
			return false;
	return true;
}

unsigned long long reverse(unsigned long long n) {
	unsigned long long result = 0;
	int length = (int) log10(n) + 1;
	for (int i = 0; i < length; i++)
		result += ((n / (unsigned long long) pow(10, i)) % 10)
				* (unsigned long long) (pow(10, length - i - 1));
	return result;
}

bool isPalindrome(unsigned long long n) {
	int length = log10(n) + 1;
	for (int i = 0; i < length / 2; i++)
		if ((n / (int) pow(10, i)) % 10
				!= (n / (int) pow(10, length - i - 1) % 10)) {
			return false;
		}

	return true;
}

