package view.binrayheap;

import java.util.Arrays;

import binaryheap.MaxBinaryHeapIF;
import binaryheap.MaxBinaryHeapImp;

public class Main {

	public static void main(String[] args) {
		MaxBinaryHeapIF maxBinrayHeap = new MaxBinaryHeapImp(12);
		maxBinrayHeap.max_heap_insert(10);
		maxBinrayHeap.max_heap_insert(2);
		maxBinrayHeap.max_heap_insert(5);
		maxBinrayHeap.max_heap_insert(7);
		System.out.println(maxBinrayHeap.max_heap_extract());
		System.out.println(maxBinrayHeap.max_heap_extract());
		System.out.println(maxBinrayHeap.max_heap_extract());
		System.out.println(maxBinrayHeap.max_heap_extract());
		maxBinrayHeap.build_max_heap(new int[]{1,4,7,1,-1,12});
		System.out.println(Arrays.toString(maxBinrayHeap.heap_sort(new int[]{5,6,10,2,1,-2,0})));
	}

}
