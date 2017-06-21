package universal.hashing;

public class HashFuncionImpl implements IHashFunction {
	private long a, b, p, m;

	public HashFuncionImpl(long a, long b, int p, int m) {
		this.a = a;
		this.b = b;
		this.p = p;
		this.m = m;
	}

	public int computeHashValue(int key) {
		return (int)(((a* key + b) % p) % m);
	}
	
	public void print(){
		System.out.println("a = " + a + " , b = " + b);
	}

}

