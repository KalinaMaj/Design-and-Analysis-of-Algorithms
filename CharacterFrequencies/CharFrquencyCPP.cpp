//Compute the frequencies of characters in a given input file - used for Huffamn Coding.

#include <fstream>
#include <sstream>
using namespace std;

string printAry(int charAry[]){
	int index = 0;	
	stringstream txt;
	string output;
	
	while(index < 256){
		if(charAry[index] > 0){
			char symbol = (char)index;
			txt << symbol; 
			txt << " count #: "; 
			txt << charAry[index];
			txt << endl;
			
			output = txt.str();
		}
		index++;
	}
	return output;
}

int main(int argc, char** argv){
	
	ifstream inFile;
	inFile.open(argv[1]);
	ofstream outFile;
	outFile.open(argv[2]);
	
	char charIn;
	int index = 0;
	string printAry(int charAry[]);
	int charCounts[256] = { 0 };
	
	if(outFile.is_open()){
		while(!inFile.eof()){
			inFile >> charIn;
			index = (int)charIn;
			charCounts[index]++;
		}
		outFile << printAry(charCounts);
	}
	
	inFile.close();
	outFile.close();
return 0;	
}
