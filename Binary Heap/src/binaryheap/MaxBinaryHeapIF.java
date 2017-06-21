package binaryheap;

public interface MaxBinaryHeapIF {
    public void max_heapify(int starting_node);
    public void build_max_heap(int[] unordered_array);
    public int[] heap_sort(int[] unordered_array);
    public void max_heap_insert(int element);
    public int max_heap_extract();
    public int[] getArray();
}
    