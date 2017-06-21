package sort;

public class SortController {

    public int[] sort(int[] items, SortStrategy sort_strategy) {
        return sort_strategy.sort(items);
    }

}
