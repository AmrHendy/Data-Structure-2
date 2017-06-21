package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import dictionary.DictionaryImp;
import eg.edu.alexu.csd.filestructure.avl.IAVLTree;
import eg.edu.alexu.csd.filestructure.avl.IDictionary;
import tree.avl.AVLTreeImpl;

public class AVLTreeTest {

	@Test
	public void test1() {
		IAVLTree<Integer> avlTree = new AVLTreeImpl<Integer>();
		avlTree.insert(10);
		avlTree.insert(7);
		avlTree.insert(5);
		avlTree.insert(12);
		assertTrue(avlTree.search(10));
		assertTrue(avlTree.search(7));
		assertTrue(avlTree.search(5));
		assertTrue(avlTree.delete(5));
		assertFalse(avlTree.search(5));
		assertFalse(avlTree.delete(5));
	}
	
	@Test
	public void test2() {
		IDictionary dictionary = new DictionaryImp();
		dictionary.insert("Ahmed");
		dictionary.insert("Ahmed ali");
		dictionary.insert("Mohamed");
		dictionary.insert("Mohamedd ali");
		assertTrue(dictionary.exists("Ahmed"));
		assertTrue(dictionary.exists("Ahmed ali"));
		assertTrue(dictionary.delete("Ahmed"));
		assertFalse(dictionary.exists("Ahmed"));
	}
}
