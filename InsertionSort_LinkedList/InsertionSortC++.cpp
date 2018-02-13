/* Insertion Sort. 
* Given a file that contains a list of English words, the program constructs a linked 
* list (sorted in ascending order) for the words. The linked list must have a dummy node 
* that is pointed by the listHead.
*/

#include <fstream>
#include <string>

using namespace std;

class listNode{
public:
	string data;
	listNode* next;
	
	listNode(){
		data;
		next;
	};	//constructor #1
	listNode(string s){
		data = s;
	} //constructor #2
};

class LList{
public:
	listNode* listHead;
	listNode* walker;
	void listInsert(string ndata);
	bool isEmpty();
	string printList();
	void listDelete(string data);
	
	LList(){
		string s = "dummy";
		listHead = new listNode(s);
		listHead->next = NULL;
	} //constructor
};

void LList::listInsert(string ndata){
	listNode* nn;
	walker = listHead;
	if(walker->next != NULL && ndata > walker->next->data){
		walker = walker->next;
	}
	nn = new listNode();
	nn->data = ndata;
	nn->next = walker->next;
	walker->next = nn;
} //listInsert

bool LList::isEmpty(){
	return listHead = NULL;
} //isEmpty

string LList::printList(){
	listNode* curr;
	curr = listHead;
	std::string nodes = "listHead";
	while(curr != NULL && curr->next != NULL){
		nodes += " --> (";
		nodes += curr->data; 
		nodes += ", ";
		nodes += curr->next->data;
		nodes += ")";
		nodes += '\n';
					
		curr = curr->next;
	}
	return nodes;
}

int main(int argc, char**argv){
	
	LList *list = new LList();
	std::string word;
	
	ifstream inFile;
	inFile.open(argv[1]);
	ofstream outFile;
	outFile.open(argv[2]);
	
	if(outFile.is_open()){
		while(!inFile.eof()){
			inFile >> word;
			list->listInsert(word);
			outFile << list->printList();
		}
	}
	
	inFile.close();
	outFile.close();
	
return 0;
}
