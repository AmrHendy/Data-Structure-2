package perfect.solver;

public interface ISolver {

	public void build(int[] keySet);

	public boolean search(int key);
}
