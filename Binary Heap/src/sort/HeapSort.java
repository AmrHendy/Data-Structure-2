package sort;

import binaryheap.MaxBinaryHeapIF;
import binaryheap.MaxBinaryHeapImp;

public class HeapSort extends SortStrategy{
    private MaxBinaryHeapIF max_binary_heap;
    
    public HeapSort() {
         max_binary_heap = new MaxBinaryHeapImp();
    }
    
    @Override
    public int[] sort(int[] items) {
        return max_binary_heap.heap_sort(items);
    }
    
}
