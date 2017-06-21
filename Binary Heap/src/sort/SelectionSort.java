package sort;

public class SelectionSort extends SortStrategy {

    @Override
    public int[] sort(int[] items) {
        // TODO Auto-generated method stub
        this.items = items.clone();
        for (int i = 0; i < this.items.length; i++) {
            int minInd = i;
            for (int j = i + 1; j < this.items.length; j++) {
                if (this.items[minInd] > this.items[j]) {
                    minInd = j;
                }
            }
            int tmp = this.items[minInd];
            this.items[minInd] = this.items[i];
            this.items[i] = tmp;
        }	
        return this.items;
    }
}