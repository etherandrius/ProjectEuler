#include <iostream>
#include <string>
#include <stdlib.h>
#include <limits.h>
#include <time.h>

const int dice = 6;
int CC = 0;
int CH = 0;
int doubles = 0;
const std::string board[40] = { "GO", "A1", "CC1", "A2", "T1", "R1", "B1",
		"CH1", "B2", "B3", "JAIL", "C1", "U1", "C2", "C3", "R2", "D1", "CC2",
		"D2", "D3", "FP", "E1", "CH2", "E2", "E3", "R3", "F1", "F2", "U2", "F3",
		"G2J", "G1", "G2", "CC3", "G3", "R4", "CH3", "H1", "T2", "H2" };

int roll(int n){
	return rand()%n;
}

void advance(int &pos){

	int die1 = roll(dice)+1;
	int die2 = roll(dice)+1;

	if(die1 == die2)
		doubles++;
	else
		doubles = 0;

	if(doubles > 2){
		pos = 10;
		doubles = 0;
		return;
	}

	pos += die1 + die2;


	if (pos >= 40)
		pos -= 40;

	if(pos == 30){
		pos = 10;
		return;
	}


	if( pos == 7 || pos == 22 || pos == 36){
		if( CH >= 16)
			CH-=16;
		CH++;
		switch (CH) {
		case 0:
			pos = 0;
			return;
		case 1:
			pos = 10;
			return;
		case 2:
			pos = 11;
			return;
		case 3:
			pos = 24;
			return;
		case 4:
			pos = 39;
			return;
		case 5:
			pos = 5;
			return;
		case 6:
			pos -= 3;
			break;
		case 7:
			if (pos == 22) {
				pos = 28;
			} else
				pos = 12;
			return;
		case 8:
		case 9:
			switch (pos) {
			case 7:
				pos = 15;
				return;
			case 22:
				pos = 25;
				return;
			case 36:
				pos = 5;
				return;
			default: break;
			}
			return;
		default: break;
		}
	}

	if( pos == 2 || pos == 17 || pos == 33 ){
		if( CC >= 16)
			CC-=16;
		CC++;
		switch (CC){
		case 0:
			pos = 0;
			return;
		case 1:
			pos = 10;
			return;
		default:
			return;
		}
	}

}

int main(){
	srand(time(NULL));
	int position = 0;
	int freq[40];
	for(int i = 0; i < 40; i++)
		freq[i] = 0;

	for(int  i = 0; i < 100000000; i++){
		++freq[position];
		advance(position);
	}

	int first = 0, firstI=0;
	int second = 0, secondI=0;
	int third = 0, thirdI=0;

	for(int i = 0; i < 40; i++){

		if( freq[i] > first){
		third = second;
		thirdI = secondI;
		second = first;
		secondI = firstI;
		first = freq[i];
		firstI = i;
		continue;
		}
		if( freq[i] > second){
		third = second;
		thirdI = secondI;
		second = freq[i];
		secondI = i;
		continue;
		}
		if( freq[i] > third){
		third = freq[i];
		thirdI = i;
		continue;
		}
	}




	for(int i = 0; i < 40; i++){
		std::cout << freq[i] << '\t' << board[i] << '\t' << i << std::endl;
	}

	std::cout << "|\t|\t|\t|\t|" << std::endl;
	std::cout << "|\t|\t|\t|\t|" << std::endl << std::endl;
	std::cout << freq[firstI] << '\t' << board[firstI] << '\t' << firstI << std::endl;
	std::cout << freq[secondI] << '\t' << board[secondI] << '\t' << secondI << std::endl;
	std::cout << freq[thirdI] << '\t' << board[thirdI] << '\t' << thirdI << std::endl;

}

























