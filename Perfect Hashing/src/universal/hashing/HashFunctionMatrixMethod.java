package universal.hashing;

import java.util.Random;

public class HashFunctionMatrixMethod implements IHashFunction {
	private int u,b;
	private short[][] hashMatrix;
	public HashFunctionMatrixMethod(int u, int m) {
		this.u = u;
		this.b = (int)(Math.log(m)/Math.log(2));
		
		Random random = new Random();
		hashMatrix = new short[b][u];
		for(int i=0;i<b;i++){
			for(int j=0;j<u;j++){
				hashMatrix[i][j] = (short)random.nextInt(2);
			}
		}
	}
	
	public int computeHashValue(int key) {
	
		short[] keyMatrix = new short[u];
		for(int i=0;i<u;i++){
			keyMatrix[i] = (short)(key%2);
			key/=2;
		}
		
		//Multiplication
		int hashValue = 0;
		int pow = 1;
		for(int i=0;i<b;i++){
			int val = 0;
			for(int k=0;k<u;k++){
				val+=hashMatrix[i][k]*keyMatrix[k];
			}
			val%=2;
			hashValue += val*(pow);
			pow*=2;
		}
		return hashValue;
	}
	
	public void print(){
		System.out.println("hash Matrix");
		for(int i=0;i<b;i++){
			for(int j=0;j<u;j++){
				System.out.print(hashMatrix[i][j]  + " ");
			}
			System.out.println();
		}
	}			
}
