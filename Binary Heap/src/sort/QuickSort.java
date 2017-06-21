package sort;

public class QuickSort extends SortStrategy {

    @Override
    public int[] sort(int[] items) {
        // TODO Auto-generated method stub
        this.items = items.clone();
        quickSort(0, items.length - 1);
        return this.items;
    }

    private void quickSort(int start, int end) {
        if (start < end) {
            int part = partation(start, end);
            quickSort(start, part - 1);
            quickSort(part + 1, end);
        }
    }

    private int partation(int start, int end) {
        int ind = start;
        int val = items[ind];
        for (int i = start + 1; i <= end; i++) {
            if (val > items[i]) {
                ind++;
                int tmp = items[i];
                items[i] = items[ind];
                items[ind] = tmp;
            }
        }
        items[start] = items[ind];
        items[ind] = val;
        return ind;
    }
}