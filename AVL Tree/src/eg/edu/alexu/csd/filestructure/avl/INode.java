package eg.edu.alexu.csd.filestructure.avl;

public interface INode <T extends Comparable> {
	/**
	 * Returns the left child of the current element/node in the heap tree
	 * @return INode wrapper to the left child of the current element/node 
	 */ 
	public INode<T> getLeftChild();
	
	/**
	 * Returns the right child of the current element/node in the heap tree
	 * @return INode wrapper to the right child of the current element/node 
	 */ 
	public INode<T> getRightChild();
	
	public void setLeftChild(INode<T> leftChild);
	public void setRightChild(INode<T> rightChild);
	
	/**
	 * Set/Get the value of the current node 
	 * @return Value of the current node 
	 */ 
	public T getValue(); 
	public void setValue(T value);
	public int getHeight();
	public void setHeight(int height);
}
