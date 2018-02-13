/**
* Huffman Coding Algorithm
*
* @author Kalina Majewska
*/

import java.io.*;
import java.util.*;

class listBinTreeNode{
	protected String chStr;
	protected int prob;
	protected listBinTreeNode next;
	protected listBinTreeNode left;
	protected listBinTreeNode right;
	
	public listBinTreeNode(){
		this(null, 0);
	} //constructor #1

	public listBinTreeNode(String s, int p){
		this.chStr = s;
		this.prob = p;
	}//constructor #2
	
	public String printNode(){
		String output = "[";
			output += this.chStr;
			output += ", ";
			output += Integer.toString(this.prob);
			output += ", ";
		if(this.next == null){
			output += "null, ";
		}
		else{
				output += this.next.chStr;
				output += ", ";
		}
		if(this.left == null){
			output += "null, ";
		}
		else{
			output += this.left.chStr;
			output += ", ";
		}
		if(this.right == null){
			output += "null]";
		}
		else{
			output += this.right.chStr;
			output += "]";
		}
		return output;
	}//printNode
}//class listBinTreeNode

class HuffmanLinkedList{
	protected listBinTreeNode listHead, oldListHead;
	
	public HuffmanLinkedList(){
		listHead = new listBinTreeNode("dummy", 0);
	}//constructor with listHead pointing to first dummy node.
	
	public void constructHuffmanLList(Scanner f, PrintWriter ff){
		listBinTreeNode spot = listHead;
		listBinTreeNode newNode;
		String charr;
		int p;
		while(f.hasNext()){
			charr = f.next();
			p = f.nextInt();
			spot = findSpot(p);
			newNode = new listBinTreeNode(charr, p);
			listInsert(spot, newNode);
			ff.print(this.printList());
		}//while
	}//constructHuffmanLList
	
	public listBinTreeNode findSpot(int nprob){
		listBinTreeNode walker = listHead;
		
		
			while(walker.next != null && nprob > walker.next.prob){
				walker = walker.next;
			}
		
		return walker;
	}//findSpot
	
	public void  listInsert(listBinTreeNode s, listBinTreeNode n){
		n.next = s.next;
		s.next = n;
	}//listInsert
	
	public boolean isEmpty(){
		return listHead == null;
	}//isEmpty
	
	public String printList(){
		listBinTreeNode curr = listHead;
		String Llist = "listHead";
		while(curr != null){
			Llist += " --> (";
			Llist += curr.chStr;
			Llist += ", ";
			Llist += Integer.toString(curr.prob);
			Llist += ", ";
			if(curr.next != null){
				Llist += curr.next.chStr;
				Llist += ")";
			}
			else{
				Llist += "NULL";
				Llist += ")";
			}
			curr = curr.next;
		}//while
		Llist += System.lineSeparator();
		return Llist;
	}//printList
}//class HuffmanLinkedList

class HuffmanBinaryTree{
	protected listBinTreeNode root = new listBinTreeNode();
	
	public HuffmanBinaryTree(){
		root = null;
	}//constructor
	
	public void constructHuffmanBinTree(HuffmanLinkedList list, PrintWriter ff){
		listBinTreeNode nnode = null;
		list.oldListHead = list.listHead;
		listBinTreeNode dummy = list.oldListHead;
		dummy.next = list.listHead.next;
		
		while(list.listHead.next != null && list.listHead.next.next != null){
			nnode = new listBinTreeNode();
			listBinTreeNode spot = new listBinTreeNode();
			int sum = list.listHead.next.prob + list.listHead.next.next.prob;
			String str = list.listHead.next.chStr;
			str += list.listHead.next.next.chStr;
			
			nnode.chStr = str;
			nnode.prob = sum;
			nnode.left = list.listHead.next;
			nnode.right = list.listHead.next.next;
			list.listHead = list.listHead.next.next;
			ff.print(nnode.printNode());
			spot = list.findSpot(sum);
			list.listInsert(spot, nnode);
			ff.print(System.lineSeparator() + list.printList());
		}
		root = nnode;
	}//constructHuffmanBinTree
	
	public boolean isLeaf(listBinTreeNode T){
		if(T.left == null && T.right == null){
			return true;
		}
		return false;
	}//isLeaf
	
	public void constructCharCode(listBinTreeNode T, String code, PrintWriter fo){
		if(T == null){
			System.out.println("This is an empty tree!");
			System.exit(0);
		}
		
		else if(isLeaf(T)){
			String leaf = "<";
			leaf +=	T.chStr;
			leaf += ", ";
			leaf += code;
			leaf += ">";
			leaf += System.lineSeparator();
			fo.print(leaf);
		}
		
		else{
			constructCharCode(T.left, code + "0", fo);
			constructCharCode(T.right, code + "1", fo);
		}
	}//constructCharCode
	
	void preOrder(listBinTreeNode T, PrintWriter ft){
		if(T == null){
			//do nothing
		}
		else{
			ft.print(T.printNode() + System.lineSeparator());
			preOrder(T.left, ft);
			preOrder(T.right, ft);
		}
	}//preOrder

	void inOrder(listBinTreeNode T, PrintWriter fh){
		if(T == null){
			//do nothing
		}
		else{
			inOrder(T.left, fh);
			fh.print(T.printNode() + System.lineSeparator());
			inOrder(T.right, fh);
		}
	}//inOrder

	void postOrder(listBinTreeNode T, PrintWriter fr){
		if(T == null){
			//do nothing
		}
		else{
			postOrder(T.left, fr);
			postOrder(T.right, fr);
			fr.print(T.printNode() + System.lineSeparator());
		}
	}//postOrder
	
}//HuffamnBinaryTree class


public class Project3Main {
	
	public static void main(String[] args)throws FileNotFoundException{
		Scanner inFile = new Scanner(new FileReader(args[0]));
		PrintWriter outFile1 = new PrintWriter(args[1]);
		PrintWriter outFile2 = new PrintWriter(args[2]);
		PrintWriter outFile3 = new PrintWriter(args[3]);
		PrintWriter outFile4 = new PrintWriter(args[4]);
		PrintWriter outFile5 = new PrintWriter(args[5]);
		
		HuffmanLinkedList list = new HuffmanLinkedList();
		HuffmanBinaryTree tree = new HuffmanBinaryTree();
		
		list.constructHuffmanLList(inFile, outFile5);
		tree.constructHuffmanBinTree(list, outFile5);
		tree.constructCharCode(tree.root,"", outFile1);
		tree.preOrder(tree.root, outFile2);
		tree.inOrder(tree.root, outFile3);
		tree.postOrder(tree.root, outFile4);
		
		inFile.close();
		outFile1.close();
		outFile2.close();
		outFile3.close();
		outFile4.close();
		outFile5.close();
	}
}