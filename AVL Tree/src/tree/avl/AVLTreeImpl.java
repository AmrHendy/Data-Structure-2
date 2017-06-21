package tree.avl;
import java.util.ArrayList;
import java.util.List;

import eg.edu.alexu.csd.filestructure.avl.IAVLTree;
import eg.edu.alexu.csd.filestructure.avl.INode;
import node.NodeImp;

public class AVLTreeImpl <T extends Comparable<T>> implements IAVLTree{
	private INode<T> root;
	private boolean found;
	private int size;
	private List<T> sortedTree;
	
	public AVLTreeImpl() {
		root = null;
		found = false;
		size = 0;
	}
	
	@Override
	public void insert(Comparable key) {
		this.root = insertNode(this.root, (T)key);
	}

	@Override
	public boolean delete(Comparable key) {
		this.root = deleteNode(this.root , (T)key);
		return this.found;
	}

	@Override
	public boolean search(Comparable key) {
		return searchNode(this.root , (T)key);
	}

	@Override
	public int height() {
		//empty tree
		return Height(this.root);
	}

	@Override
	public INode<T> getTree() {
		return this.root;
	}
	
	@Override
	public int getTreeSize() {
		return this.size;
	}
	
	public List<T> sortTree(){
		sortedTree = new ArrayList<T>();
		inorderTraversal(this.root);
		return sortedTree;
	}
	
	@Override
	public void printTree(){
		INode<T> curr = this.root;
		List<INode<T>> list = new ArrayList<INode<T>>(); 
		int ind = 0;
		list.add(curr);
		while(ind!=list.size()){
			curr = list.get(ind);
			ind++;
			if(curr == null)continue;
			list.add(curr.getLeftChild());
			list.add(curr.getRightChild());
		}
		List<T> val = new ArrayList<T>();
		for(INode node : list){
			if(node == null){
				val.add(null);
			}
			else{
				val.add((T)node.getValue());
			}
		}
		System.out.println(String.valueOf(val));
	}
	
	private INode<T> insertNode(INode<T> root , T value){
		if(root == null){
			root = new NodeImp(value);
			size++;
			return root;
		}
		int comp = root.getValue().compareTo(value);
		if(comp >= 0)
			root.setLeftChild(insertNode(root.getLeftChild(), value));
		else if(comp <= 0)
			root.setRightChild(insertNode(root.getRightChild(), value));
		else;
		INode balancedSubtree = balanceTree(root);
		return balancedSubtree;
	}
	
	private INode<T> deleteNode(INode<T> root , T value){
		if(root == null){
			this.found = false;
			return null;
		}
		int comp = root.getValue().compareTo(value);
		if(comp > 0)
			root.setLeftChild(deleteNode(root.getLeftChild(), value));
		else if(comp < 0)
			root.setRightChild(deleteNode(root.getRightChild(), value));
		//we find the node
		else{
			size--;
			this.found = true;
			if(root.getLeftChild()!=null && root.getRightChild()!=null){
				root.setValue(findMinElementInTree(root.getRightChild()).getValue());
				root.setRightChild(deleteNode(root.getRightChild(),root.getValue()));
			}
			else{
				root = (root.getLeftChild() != null) ? root.getLeftChild() : root.getRightChild();
			}
		}
		INode balancedSubtree = balanceTree(root);
		return balancedSubtree;
	}
	
	private boolean searchNode(INode<T> root , T value){
		if(root == null){
			return false;
		}
		int comp = root.getValue().compareTo(value);
		if(comp > 0)
			return searchNode(root.getLeftChild(), value);
		else if(comp < 0)
			return searchNode(root.getRightChild(), value);
		//we find the node
		else{
			return true;
		}
	}
	
	private INode<T> balanceTree(INode<T> subtreeRoot){
		if(subtreeRoot == null)return null;
		int hl = Height(subtreeRoot.getLeftChild());
		int hr = Height(subtreeRoot.getRightChild());
		int balanceFactor = hl - hr;
		
		//R
		if(balanceFactor < -1){
			int hrl = Height(subtreeRoot.getRightChild().getLeftChild());
			int hrr = Height(subtreeRoot.getRightChild().getRightChild());
			//RR
			if(hrr >= hrl){
				subtreeRoot = leftRotate(subtreeRoot);
			} 
			//RL
			else{
				subtreeRoot.setRightChild(rightRotate(subtreeRoot.getRightChild()));
				subtreeRoot = leftRotate(subtreeRoot);
			}
			
		}
		//L
		if(balanceFactor > 1){
			int hll = Height(subtreeRoot.getLeftChild().getLeftChild());
			int hlr = Height(subtreeRoot.getLeftChild().getRightChild());
			//LL
			if(hll >= hlr){
				subtreeRoot = rightRotate(subtreeRoot);
			} 
			//LR
			else{
				subtreeRoot.setLeftChild(leftRotate(subtreeRoot.getLeftChild()));
				subtreeRoot = rightRotate(subtreeRoot);
			}
		}
		//this line to update height if we don't make any rotation
		//if we made rotation we will find height is already updated
		subtreeRoot.setHeight(Math.max(Height(subtreeRoot.getLeftChild()),
				Height(subtreeRoot.getRightChild()))+ 1);
		return subtreeRoot;
	}
	
	private INode<T> rightRotate(INode<T> rotatedRoot){
		INode newRoot = rotatedRoot.getLeftChild();
		rotatedRoot.setLeftChild(newRoot.getRightChild());
		newRoot.setRightChild(rotatedRoot);
		rotatedRoot.setHeight(Math.max(Height(rotatedRoot.getLeftChild()) ,
				Height(rotatedRoot.getRightChild())) + 1);
		newRoot.setHeight(Math.max(Height(newRoot.getLeftChild()), 
				Height(newRoot.getRightChild())) + 1);
		return newRoot;
	}
	
	private INode<T> leftRotate(INode<T> rotatedRoot){
		INode newRoot = rotatedRoot.getRightChild();
		rotatedRoot.setRightChild(newRoot.getLeftChild());
		newRoot.setLeftChild(rotatedRoot);
		rotatedRoot.setHeight(Math.max(Height(rotatedRoot.getLeftChild()),
				Height(rotatedRoot.getRightChild())) + 1);
		newRoot.setHeight(Math.max(Height(newRoot.getLeftChild()),
				Height(newRoot.getRightChild())) + 1);
		return newRoot;
	}
	
	private int Height (INode node){
		if(node == null)return -1;
		return node.getHeight();
	}

	private INode<T> findMinElementInTree(INode<T> treeRoot){
		if(treeRoot == null)return null;
		if(treeRoot.getLeftChild()==null)return treeRoot;
		return findMinElementInTree(treeRoot.getLeftChild());
	}

	private void inorderTraversal (INode node){
		if(node == null)return;
		inorderTraversal(node.getLeftChild());
		visit(node);
		inorderTraversal(node.getRightChild());
	}
	
	private void visit(INode node){
		sortedTree.add((T)node.getValue());
	}
}