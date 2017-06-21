package sort;

public abstract class SortStrategy {
	 protected int[] items;
	 
	 public SortStrategy() {
	 }
	 
	 public abstract int[] sort(int[] items);
	 
}
