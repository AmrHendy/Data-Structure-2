package universal.hashing;

import java.util.Random;

public class UniversalHashingFamily {
	
	public UniversalHashingFamily() {

	}

	public IHashFunction getRandomHashFunction(int hashTableSize, int largestPrime) {
		Random random = new Random();
		
		long a = random.nextInt(largestPrime);
		long b = random.nextInt(largestPrime);
		// a != b must be achived or the prob of collision will not be = 1/m
		if (a == b) {
			b = (b + 1) % largestPrime;
		}
		IHashFunction hashFunction = new HashFuncionImpl(a, b, largestPrime, hashTableSize);
		return hashFunction;
	}
	
	public IHashFunction getRandomHashFunction2(int hashTableSize, int numberOfBitsForLargestNumber) {
		IHashFunction hashFunction = new HashFunctionMatrixMethod(numberOfBitsForLargestNumber,hashTableSize);
		return hashFunction;
	}
}
