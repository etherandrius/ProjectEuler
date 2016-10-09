#include <iostream>
#include <fstream>
#include <sstream>
#include <algorithm>

using namespace std;

bool checkLegal(string words[], short list[], int listSize);
bool applyCodeChar(short cod, int index, int period, short cipher[],
		short text[], int& length); // TODO
void applyProperCode(short code, int index, int period, short cipher[],
		short text[], int& length);
short XOR(short x, short y);
void decToBin(short & dec, bool bin[]);
short binToDec(bool bin[]);
int size();
void input(short A[], int& length);
int stoi(string& line);
void printArray(int A[], int& size);
void printArray(short A[], int& size);
void printArray(bool A[], int& size);
void printText(short A[], int& size);
void getComWords(string words[]);
bool wordInList(string word, short list[], int listSize);

short afd(short & a) {
	return a + 1;
}

// (int)'a' == 97;
// (int)'z' == 122;
int main() {

	int length = size();
	short cipher[length];
	short text[length];
	input(cipher, length);
	string words[10000];
	getComWords(words);

	for (short i = 97; i < 123; i++) {
		if (applyCodeChar(i, 0, 3, cipher, text, length))
			for (short j = 97; j < 123; j++)
				if (applyCodeChar(j, 1, 3, cipher, text, length))
					for (short k = 97; k < 123; k++)
						if (applyCodeChar(k, 2, 3, cipher, text, length)) {
							if (checkLegal(words, text, length)) {
								printText(text, length);
								cout << i << " " << j << " " << k << endl;
								cout << (char) i << (char) j << (char) k
										<< endl;

								short properText[length];
								applyProperCode(i, 0, 3, cipher, properText,
										length);
								applyProperCode(j, 1, 3, cipher, properText,
										length);
								applyProperCode(k, 2, 3, cipher, properText,
										length);
								printText(properText, length);
								int sum = 0;
								for (int i = 0; i < length; i++)
									sum += properText[i];
								cout << sum << endl;
							}
						}
	}

	double diff = (double) clock() / (double) CLOCKS_PER_SEC;
	cout << diff << endl;

}

bool checkLegal(string words[], short list[], int listSize) {
	int legal = 0;
	string current = "";

	for (int i = 0; i < listSize; i++) {
		if(legal > 30){
			return true;
		}
		if (list[i] == 0) {
			if (current != "") {
				if (binary_search(words, words + 10000, current)) {
					++legal;
				}
				current = "";
			}

		} else
			current += (char) list[i];
	}
	return legal > 30;
}

bool applyCodeChar(short code, int index, int period, short cipher[],
		short text[], int& length) {

	short temp;
	int zeros = 0;

	for (; index < length; index += period) {
		temp = XOR(cipher[index], code);
		if (temp < 123 && temp > 96) {
			text[index] = temp;
			continue;
		}
		if (temp < 91 && temp > 64) {
			text[index] = temp + 32;
			continue;
		}
		if (temp < 32 || temp > 126)
			zeros += 16;
		text[index] = 0;
		zeros++;
	}

	return (double) zeros / (double) length < 0.1;
}

bool wordInList(string word, short list[], int listSize) {

	if (word == "")
		return false;

	for (int j = 0; j < 200; j++) {
		bool state = true;
		for (unsigned int i = 0; i < word.length(); i++) {
			if ((int) word[i] != list[j + i]) {
				state = false;
				break;
			}
		}
		if (state == true) {
			cout << word << "  " << j << endl;
			return true;

		}
	}

	return false;
}

void applyProperCode(short code, int index, int period, short cipher[],
		short text[], int& length) {

	short temp;

	for (; index < length; index += period) {
		temp = XOR(cipher[index], code);
		text[index] = temp;
	}

}

short XOR(short x, short y) {
	bool xbin[7];
	decToBin(x, xbin);
	bool ybin[7];
	decToBin(y, ybin);
	bool zbin[7];
	for (int i = 0; i < 7; i++)
		zbin[i] = (!xbin[i] != !ybin[i]);

	return binToDec(zbin);
}

void decToBin(short & dec, bool bin[]) {
	// suitable only for dec < 128, bin < 1000000
	for (int i = 6, power = 64; i >= 0; i--, power /= 2)
		if (dec >= power) {
			dec -= power;
			bin[i] = 1;
		} else
			bin[i] = 0;
}

short binToDec(bool bin[]) {
	// suitable only for dec < 128, bin < 1000000
	short dec = 0;
	for (int i = 6, power = 64; i >= 0; i--, power /= 2)
		if (bin[i] == 1) {
			dec += power;
		}

	return dec;
}

int size() {
	ifstream scan("p059_cipher.txt");
	string line;
	scan >> line;
	int length = 1;
	for (unsigned int i = 0; i < line.length(); i++)
		if (line[i] == ',')
			++length;
	scan.close();
	return length;
}

void input(short A[], int& length) {
	ifstream scan("p059_cipher.txt");
	string line;
	for (int i = 0; i < length; i++) {
		getline(scan, line, ',');
		A[i] = stoi(line);
	}
	scan.close();
}

void getComWords(string words[]) {
	ifstream scan;
	scan.open("commonWords.txt");
	for (int i = 0; i < 10000; i++) {
		scan >> words[i];
		if (words[i].length() < 3)
			words[i] = "";
	}
	sort(words, words + 10000);
}

int stoi(string& line) {
	stringstream ss(line);
	int result;
	return ss >> result ? result : 0;
}

void printArray(int A[], int& size) {
	cout << "[ " << A[0];
	for (int i = 1; i < size; i++)
		cout << "," << A[i];
	cout << " ]" << endl;
}

void printArray(short A[], int& size) {
	cout << "[ " << A[0];
	for (int i = 1; i < size; i++)
		cout << "," << A[i];
	cout << " ]" << endl;
}

void printArray(bool A[], int& size) {
	for (int i = size - 1; i >= 0; i--)
		cout << A[i];
	cout << "\n";

}

void printText(short A[], int& size) {
	cout << "[ ";
	if (A[0] == 0)
		cout << '.';
	else
		cout << A[0];
	for (int i = 1; i < size; i++)
		if (A[i] == 0)
			cout << '.';
		else
			cout << (char) A[i];
	cout << " ]" << endl;
}
