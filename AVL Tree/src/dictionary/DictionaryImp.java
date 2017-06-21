package dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import eg.edu.alexu.csd.filestructure.avl.IAVLTree;
import eg.edu.alexu.csd.filestructure.avl.IDictionary;
import tree.avl.AVLTreeImpl;

public class DictionaryImp implements IDictionary{
	private IAVLTree<String> avlTree;
	private int size = 0;
	public DictionaryImp() {
		avlTree = new AVLTreeImpl<String>();
	}
	
	@Override
	public void load(File file) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			while(bufferedReader.ready()){
				String str = bufferedReader.readLine();
				insert(str);
			}
			bufferedReader.close();
		} catch (Exception e ) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(String word) {
		if(avlTree.search(word)){
			return false;
		}
		size++;
		avlTree.insert(word);
		return true;
	}

	@Override
	public boolean exists(String word) {
		return avlTree.search(word);
	}

	@Override
	public boolean delete(String word) {
		if(avlTree.delete(word)){
			size--;
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public int height() {
		return avlTree.height();
	}
	
	public void sortDictionary(File outputFile){
		List<String> sortedData = avlTree.sortTree();
		try {
			FileWriter fileWriter = new FileWriter(outputFile);
			for(String word : sortedData){
				fileWriter.write(word);
				fileWriter.write(new String("\n"));
			}
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
