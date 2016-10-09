#include <algorithm>
#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <cmath>

const int max = 14; // largest word;
std::vector<bool> bool_anagram(max+1, false); // for which lengths exists an anagram word
std::vector< std::vector< std::vector<std::string> > > anagramWord(max+1); // anagramWord[i][j][k]; i - denotes #of char; j - denotes an anagram set; k - denotes specific anagram
std::vector< std::vector< std::vector<int> > > anagramSquare(max+1); // anagramSquares[i][j][k]; i - denotes #of digits; j - denotes an anagram set ; k - denotes specific anagram

struct square{
	long n; // number
	std::vector<short> d; // digits
};

void getAnagrams(std::vector<std::string>& wordlist){
	if( wordlist.size() == 0)
		return;

	int length = wordlist[0].length();
	std::vector< std::vector<char> > word_classification;

	for(unsigned int i = 0; i < wordlist.size(); i++){
		std::vector<char> temp(wordlist[i].length());
		for(unsigned int j = 0; j < wordlist[i].length(); j++)
			temp.push_back(wordlist[i][j]);
		std::sort(temp.begin(), temp.end());
		word_classification.push_back(temp);
	}

	for(unsigned int i = 0; i < wordlist.size(); i++)
		if( wordlist[i] != ""){

			std::vector<std::string> anagrams;
			anagrams.push_back(wordlist[i]);
			wordlist[i] = "";

			for(unsigned int j = i+1; j < wordlist.size(); j++)
				if( wordlist[j] != "" )
				if( word_classification[i] == word_classification[j] ){
					anagrams.push_back(wordlist[j]);
					wordlist[j] = "";
				}

			if( anagrams.size() > 1 )
				anagramWord[length].push_back(anagrams);
		}

	if(anagramWord[length].size() > 0){
		bool_anagram[length] = true;
	}

}

void input(){

	std::vector<std::string> wordlist[max+1]; // sorted

	std::ifstream input("p098_words.txt");
	if( input.is_open() == false){
		std::cout << "File is missing\n";
		return;
	}
	char buff;
	std::string word;
	input >> buff;
	while( input.eof() == false ){
		getline(input, word, '"');
		input >> buff >> buff;

		wordlist[word.length()].push_back(word);
	}

	for(unsigned int i = 0; i <= max; i++)
		getAnagrams(wordlist[i]);

}

void getAnagrams(std::vector<square>& all_squares, int size){
	int exp = log10(size);

	for(unsigned int i = 0; i < all_squares.size(); i++)
		if (all_squares[i].n != 0) {

			std::vector<int> anagrams;
			anagrams.push_back(all_squares[i].n);
			all_squares[i].n = 0;

			for(unsigned int j = i+1; j < all_squares.size(); j++)
				if( all_squares[j].n != 0)
				if(all_squares[i].d == all_squares[j].d){
					anagrams.push_back(all_squares[j].n);
					all_squares[j].n = 0;
				}

			if( anagrams.size() > 1)
				anagramSquare[exp].push_back(anagrams);

		}
}

void genSquares(){

	long limit = std::pow(10, max+1);
	int exp = 2;
	for(long size = 100; size < limit; size*=10, exp++)
		if( bool_anagram[exp] == true ){
			std::vector<square> all_squares;

			for(long i = (long)sqrt(size/10)+1; i <= (long)sqrt(size); i++){
				square n;
				n.n= i*i;
				while( n.n > 0){
					n.d.push_back(n.n%10);
					n.n/=10;
				}
				n.n = i*i;
				std::sort(n.d.begin(), n.d.end());
				all_squares.push_back(n);
			}
			getAnagrams(all_squares, size);
	}

}

// prints words, and squares that satisfy problem98;
void Curious98(const int len, const unsigned int i, const unsigned int j){
	// fixed digit length
	// for every anagram number set
	for(unsigned int _set = 0; _set < anagramSquare[len].size(); _set++){
		// for every anagram number in set
		for(unsigned int _num = 0; _num < anagramSquare[len][_set].size(); _num++){
			// assign a value to anagramWord[len][i][j] and shift it according to next anagram WORD
			int d[len];
			for(int id = len-1, n = anagramSquare[len][_set][_num]; n > 0; n/=10, id--)
				d[id] = n%10;

			for(int k = 0; k < len; k++)
				for(int l = k + 1; l < len; l++)
					if( (d[k] == d[l]) xor (anagramWord[len][i][j][k] == anagramWord[len][i][j][l]) )
						goto DIGIT_MISSMATCH;

			//for every anagram WORD in set anagramWord[len][i]
			for(unsigned int j2 = j+1; j2 < anagramWord[len][i].size(); j2++){

				//shifts d -> d2 how  word1 -> word2
				std::vector<int> d2(len, -1);

				//the shift
				for(int k = 0; k < len; k++)
					for(int l = 0; l < len; l++)
						if( anagramWord[len][i][j][k] == anagramWord[len][i][j2][l]){
							d2[l] = d[k];
							break;
						}

				int D2 = 0;
				for(int id = len - 1, size = 1; id >= 0; id--, size*=10)
					D2 += d2[id]*size;

				if(std::binary_search(anagramSquare[len][_set].begin(), anagramSquare[len][_set].end(), D2)){
					std::cout << anagramWord[len][i][j]  << " : " << anagramSquare[len][_set][_num] << " -> ";
					std::cout << anagramWord[len][i][j2] << " : " << D2 << '\n';
				}
			}
		}
		DIGIT_MISSMATCH:; // is is call in cases such as AbA 123, but would pass AbA 121;
	}
}

void find_anagramic_squares(){
	//for every word length
	for(unsigned int len = 1; len < anagramWord.size(); len++){
		std::cout << " Length : " << len << '\n';
		//for every anagram set
		for(unsigned int set = 0; set < anagramWord[len].size(); set++)
			// for every anagram in anagram word set
			for(unsigned int word = 0; word < anagramWord[len][set].size() - 1; word++)
				 Curious98(len, set, word);
	}
}

int main(){
	std::cout << "Input...\n";
	input();
	std::cout << " Done.\n\n";
	std::cout << "genSquares...\n";
	genSquares();
	std::cout << " Done.\n\n";
	std::cout << "find... \n\n";
	find_anagramic_squares();
	std::cout << "\n Done.\n";

	double diff = (double) clock() / (double) CLOCKS_PER_SEC;
	std::cout << diff << std::endl;
}
