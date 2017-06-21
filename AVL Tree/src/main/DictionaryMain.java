package main;

import java.io.File;

import dictionary.DictionaryImp;
import eg.edu.alexu.csd.filestructure.avl.IDictionary;

public class DictionaryMain {

	public static void main(String[] args) {
		IDictionary dictionary = new DictionaryImp();
		dictionary.load(new File("test.txt"));
		dictionary.sortDictionary(new File("out.txt"));
	}

}
