/**
* Compute the frequencies of characters in a given input file - used for Huffamn Coding.
*
* @author Kalina Majewska
*/

import java.io.*;
import java.util.*;

public class Project2Main {

	public static void main(String[] args) throws FileNotFoundException{
		int[] charCounts = new int[256];
		char charIn = ' ';
		
		String word;
		int index = 0;
		
		Scanner inFile = new Scanner(new FileReader(args[0])); //open input file
		PrintWriter outFile = new PrintWriter(args[1]); //open output file
		while(inFile.hasNext()){
			word = inFile.next();
			for(int i=0;i < word.length(); i++){
				charIn = word.charAt(i);
				index = (int)charIn;
				charCounts[index]++;
			}
		}
		outFile.print(printAry(charCounts));
		inFile.close();
		outFile.close(); //close files
	}//end main
	
	
	/*printAry function*/
	public static String printAry(int[] charAry){
		int index = 0;
		String output = "";
		
		while(index < 256){
			if(charAry[index] > 0){
				char symbol = (char)index;
				output += symbol;
				output += " count #: ";
				output += Integer.toString(charAry[index]);
				output += System.lineSeparator();
			}
			else{ 
				//skip character 
			}
			index++;
		}
		return output;
	}//end printAry
}
