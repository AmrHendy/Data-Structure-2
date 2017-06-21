package eg.edu.alexu.csd.filestructure.avl;
import java.util.ArrayList;
import java.util.List;

public interface IAVLTree<T extends Comparable> {
	
	/** 
	 * Insert the given value using the key 
	 * @param key the value to be inserted in the tree 
	 */ 
	public void insert(T key);
	
	/** 
	 * Delete the key (if exists) 
	 * @param key the key of the node 
	 * @return true if node deleted, false if not exists 
	 */
	public boolean delete(T key);
	
	/**
	 * Search for a specific element using the key in the tree 
	 * @param key the key of the node 
	 * @return true if the key exists, false otherwise 
	 */ 
	public boolean search(T key);
	
	/** 
	 * Return the height of the AVL tree. This is the longest path from 
	 * the root to a leaf-node 
	 * @return tree height 
	 */ 
	public int height();
	
	/** 
	 * Return the root of your AVL tree. 
	 * @return root of the AVL tree. 
	 */ 
	public INode<T> getTree();

	/** 
	 * Return the number of nodes in the AVL tree. 
	 * @return size of the AVL tree. 
	 */
	public int getTreeSize();
	
	/**
	 * show the tree content
	 */
	public void printTree();
	
	/** 
	 * Sort the Contents in AVL tree. 
	 * @return sorted list containing elements of AVL tree.
	 */
	public List<T> sortTree();
}
