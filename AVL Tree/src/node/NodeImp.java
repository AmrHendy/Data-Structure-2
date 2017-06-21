package node;

import eg.edu.alexu.csd.filestructure.avl.INode;

public class NodeImp <T extends Comparable<T>> implements INode{
	private INode<T> leftNode,rightNode;
	private T data;
	private int height;
	public NodeImp(T data) {
		this.data = data;
		this.leftNode = null;
		this.rightNode = null;
		this.height = 0;
	}
	
	@Override
	public INode<T> getLeftChild() {
		return leftNode;
	}

	@Override
	public INode<T> getRightChild() {
		return rightNode;
	}

	@Override
	public void setLeftChild(INode leftChild) {
		leftNode = leftChild;
	}

	@Override
	public void setRightChild(INode rightChild) {
		rightNode = rightChild;
	}

	@Override
	public T getValue() {
		return this.data;
	}
	
	@Override
	public void setValue(Comparable value) {
		this.data = (T) value;
	}
	
	@Override
	public int getHeight() {
		return this.height;
	}
	
	@Override
	public void setHeight(int height) {
		this.height = height;
	}
}