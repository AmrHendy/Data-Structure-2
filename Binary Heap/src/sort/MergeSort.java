package sort;

public class MergeSort extends SortStrategy {
	
	public MergeSort() {
	}

	@Override
    public int[] sort(int[] items) {
        // TODO Auto-generated method stub
        this.items = items.clone();
        sort(0, items.length - 1);
        return this.items;
    }

    private void sort(int start, int end) {
    	if (start < end) {
            int mid = (start + end) / 2;
            sort(start, mid);
            sort(mid + 1, end);
            merge(start, mid, end);
    	}
    }

    private void merge(int start, int mid, int end) {
        int[] tmp = new int[end - start + 1];
        int i = start, j = mid + 1, k = 0;
        while (i <= mid && j <= end) {
            if (items[i] < items[j]) {
                tmp[k++] = items[i++];
            } else {
                tmp[k++] = items[j++];
            }
        }
        while (i <= mid) {
            tmp[k++] = items[i++];
        }
        while (j <= end) {
            tmp[k++] = items[j++];
        }
        k = 0;
        for (int m = start; m <= end; m++) {
            items[m] = tmp[k++];
        }
    }
}