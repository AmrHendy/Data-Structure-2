package util;

import universal.hashing.IHashFunction;

public class SecondaryHashCell {
	private IHashFunction hashFunction;
	private int[] secondaryTable;
	
	public SecondaryHashCell() {
		
	}
	
	public IHashFunction getHashFunction() {
		return hashFunction;
	}

	public void setHashFunction(IHashFunction hashFunction) {
		this.hashFunction = hashFunction;
	}

	public int[] getSecondaryTable() {
		return secondaryTable;
	}

	public void setSecondaryTable(int[] secondaryTable) {
		this.secondaryTable = secondaryTable;
	}
	
}
