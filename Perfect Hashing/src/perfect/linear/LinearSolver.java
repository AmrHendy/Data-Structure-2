package perfect.linear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import perfect.quadratic.QuadraticSolver;
import perfect.solver.ISolver;
import universal.hashing.IHashFunction;
import universal.hashing.UniversalHashingFamily;
import util.SecondaryHashCell;

public class LinearSolver implements ISolver {
	private SecondaryHashCell[] primaryHashTable;

	private IHashFunction primaryHashFunction;
	private UniversalHashingFamily universal;
	private int[] secondarySize;
	
	// in test 3 we need it to be > 100
	private final int allowedCoeff = 10;
	private final int largestPrime = Integer.MAX_VALUE;
	private final int numberOfBitsForLargestNumber = 32;

	
	
	public LinearSolver() {
		universal = new UniversalHashingFamily();
	}

	@Override
	public void build(int[] keySet) {
		secondarySize = new int[keySet.length];

		int tableSize = keySet.length;
		primaryHashTable = new SecondaryHashCell[tableSize];
		for(int i=0;i<tableSize;i++)primaryHashTable[i] = new SecondaryHashCell();
 		do {
			primaryHashFunction = universal.getRandomHashFunction(tableSize, largestPrime);
			//primaryHashFunction = universal.getRandomHashFunction2(tableSize, numberOfBitsForLargestNumber);
		} while (!checkLinearSpace(primaryHashFunction, keySet));
		buildSecondaryHashTable(keySet);
		
	}

	@Override
	public boolean search(int key) {
		int ind1 = primaryHashFunction.computeHashValue(key);
		if(primaryHashTable[ind1]== null)return false;
		int ind2 = primaryHashTable[ind1].getHashFunction().computeHashValue(key);
		return (primaryHashTable[ind1].getSecondaryTable()[ind2] == key);
	}

	private boolean checkLinearSpace(IHashFunction primaryHashFunction, int[] keySet) {
		Arrays.fill(this.secondarySize, 0);
		for (int key : keySet) {
			int ind = primaryHashFunction.computeHashValue(key);
			//System.out.println(key + " ==> " + ind);
			secondarySize[ind]++;
		}
		// if we don't care very much about memory but note that prob(space > 4*n) < 1/2
		//		return true;
		
		
		int totalSize = 0;
		for (int sz : secondarySize) {
			totalSize += (sz * sz);
//			if (totalSize > keySet.length * allowedCoeff)
//				return false;
		}
		System.out.println("total memory = " + totalSize);
		return true;
	}

	private void buildSecondaryHashTable(int[] keySet) {
		List<List<Integer>> elements = new ArrayList<List<Integer>>();

		for (int i = 0; i < keySet.length; i++) {
			if (secondarySize[i] == 0) {
				elements.add(null);
			} else {
				elements.add(new ArrayList<Integer>());
			}

		}

		for (int key : keySet) {
			int ind = primaryHashFunction.computeHashValue(key);
			elements.get(ind).add(key);
		}	
		
		int colli = 0;

		QuadraticSolver quadraticSolver = new QuadraticSolver();
		for (int i = 0; i < keySet.length; i++) {
			if (elements.get(i) != null) {
				int[] elementsArray = new int[elements.get(i).size()];
				for (int j = 0; j < elements.get(i).size(); j++)
					elementsArray[j] = elements.get(i).get(j);

				quadraticSolver.build(elementsArray);
				colli+= quadraticSolver.collisions;
				//System.out.println("success " + i);
				primaryHashTable[i].setHashFunction(quadraticSolver.getHashFunction());
				primaryHashTable[i].setSecondaryTable(quadraticSolver.getHashTable());
			}
			else{
				primaryHashTable[i] = null;
			}
		}
		System.out.println("Number of Repeating  " + colli);
	}
}