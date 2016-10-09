#include <iostream>
#include <vector>
#include <fstream>
#include <string>
#include <cmath>

struct RomanNumerals{
	std::string input;
	int Z; // Z for Integers
	std::string RN; // RN for Roman numerals
};

void input(std::vector<RomanNumerals>& text){
	std::fstream input("p089_roman.txt");
	if(input.is_open()){
		RomanNumerals temp;
		while(!input.eof()){
			std::getline(input, temp.input);
			text.push_back(temp);
		}
	}
	else std::cout << "File was not opened";
}

void read(std::string& in, int& out){ // assumes valid form.

	out = 0;
	if(in.length() > 0)
	for(unsigned int i = 0; i < in.length(); i++){

		if(i + 1 == in.length()){
			switch(in[i]){
			case 'I':
				out += 1;
				break;
			case 'V':
				out += 5;
				break;
			case 'X':
				out += 10;
				break;
			case 'L':
				out += 50;
				break;
			case 'C':
				out += 100;
				break;
			case 'D':
				out += 500;
				break;
			case 'M':
				out += 1000;
				break;
			default:
				std::cout << "Invalid String" << std::endl;
				return;

			}
			break;
		}

		switch (in[i]) {
			case 'I':
				switch (in[i + 1]) {
				case 'V':
					out += 4; // 5 - 1
					i++;
					break;
				case 'X':
					out += 9; // 10 - 1
					i++;
					break;
				default:
					out++;
					break;
				}
				break;
			case 'V':
				out += 5;
				break;
			case 'X':
				switch(in[i+1]){
				case 'L':
					out += 40; // 50 - 10
					i++;
					break;
				case 'C':
					out += 90; // 100 - 10
					i++;
					break;
				default:
					out += 10;
					break;
				}
				break;
			case 'L':
					out +=50;
					break;
			case 'C':
				switch(in[i+1]){
				case 'D':
					out += 400; // 500 - 100
					i++;
					break;
				case 'M':
					out += 900; // 1000 - 100
					i++;
					break;
				default:
					out += 100;
					break;
				}
				break;
			case 'D':
				out += 500;
				break;
			case 'M':
				out += 1000;
				break;
			default:
				std::cout << "Invalid String" << std::endl;
				return;
		}


	}
}

void write(int in, std::string& out){
	out = "";
	const char romanV[] = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
	const int values[] =  { 1 ,  5 ,  10,  50, 100, 500, 1000};// Can be extended indefinetly
	// assumes last value is 10^n, for some n;
	int ix = 6; // index of last value;

	int lim = (in - in%values[ix])/values[ix];
	in -= lim * values[ix];
	for(int i = 0; i < lim; i++)
		out += romanV[ix];

	ix -= 2;
	while(ix >= 0){
		lim = (in - in%values[ix])/values[ix];
		in -= lim * values[ix];

		if(lim == 9){
			out += romanV[ix];
			out += romanV[ix+2];
		}
		else if(lim == 4){
			out += romanV[ix];
			out += romanV[ix+1];
		}
		else if( lim > 4){
			out += romanV[ix+1];
			lim -= 5;
			for(int i = 0; i < lim; i++)
				out += romanV[ix];
		}
		else{
			for(int i = 0; i < lim; i++)
				out += romanV[ix];
		}

		ix-=2;
	}
}

int main(){
	std::vector<RomanNumerals> roman;
	input(roman);

	for(unsigned int i = 0; i < roman.size(); i++){
		read(roman[i].input, roman[i].Z);
		write(roman[i].Z, roman[i].RN);
	}

	int result = 0;
	for(unsigned int i = 0; i < roman.size(); i++)
		result += roman[i].input.size() - roman[i].RN.size();

	std::cout << result << std::endl;
}
