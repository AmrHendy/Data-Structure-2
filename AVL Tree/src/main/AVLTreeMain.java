package main;

import tree.avl.AVLTreeImpl;
import eg.edu.alexu.csd.filestructure.avl.IAVLTree;;

public class AVLTreeMain {

	public static void main(String[] args) {
		IAVLTree<Integer> avlTree = new AVLTreeImpl<Integer>();
		/*
		avlTree.printTree();
		avlTree.insert(12);
		avlTree.insert(10);
		avlTree.printTree();
		avlTree.insert(5);
		avlTree.printTree();
		avlTree.insert(8);
		avlTree.printTree();
		avlTree.insert(9);
		avlTree.printTree();
		System.out.println(avlTree.search(9));
		System.out.println(avlTree.search(12));
		System.out.println(avlTree.search(15));
		System.out.println(avlTree.search(0));
		avlTree.delete(8);
		avlTree.printTree();
		System.out.println(String.valueOf(avlTree.getTree().getValue()));
		avlTree.delete(10);
		avlTree.printTree();
		System.out.println(String.valueOf(avlTree.getTree().getValue()));
		System.out.println(String.valueOf(avlTree.height()));
		*/
		
		// test duplicate elements
		/*
		avlTree.insert(5);
		avlTree.insert(10);
		avlTree.insert(5);
		avlTree.printTree();

		avlTree.insert(5);
		avlTree.printTree();

		avlTree.insert(5);
		avlTree.printTree();


		avlTree.delete(5);
		avlTree.printTree();
		*/
		
		//corner test case insert 15 , 20 , 10 , 7 , 11  then delete 15 
		// it will be like
		//			20
		//		10
		//	7 		11
		avlTree.insert(15);
		avlTree.insert(20);
		avlTree.insert(10);
		avlTree.insert(7);
		avlTree.insert(11);
		avlTree.printTree();
		avlTree.delete(15);
		avlTree.printTree();
	}
}