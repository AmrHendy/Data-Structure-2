package binaryheap;

public class MaxBinaryHeapImp implements MaxBinaryHeapIF {
    private int[] array_elements;
    private int max_size , current_index;
    
    public MaxBinaryHeapImp (int max_size) {
        this.max_size = max_size;
        array_elements = new int[max_size];
        current_index = 0;
    }
    
    public MaxBinaryHeapImp () {
        this.max_size = 0;
        array_elements = new int[max_size];
        current_index = 0;
    }
    
    @Override
    public void max_heapify(int starting_node) {
        if(starting_node*2 + 1 >= current_index)return;
        int left_node_index = get_leftchild_ind(starting_node);
        int right_node_index = get_rightchild_ind(starting_node);
        int greater_ind;
        if(left_node_index >= this.current_index){
            greater_ind = starting_node;
        } else if(right_node_index >= this.current_index){
            greater_ind = left_node_index;
        } else if(array_elements[left_node_index] > array_elements[right_node_index]){
            greater_ind = left_node_index;
        } else{
            greater_ind = right_node_index;
        }
        if(array_elements[greater_ind] > array_elements[starting_node]){
            swap(greater_ind,starting_node);
            max_heapify(greater_ind);
        }
    }

    @Override
    public void build_max_heap(int[] unordered_array) {
        this.max_size = unordered_array.length;
        this.array_elements = new int[this.max_size];
        this.array_elements = unordered_array;
        this.current_index = this.max_size;
        for(int i=current_index-1;i>=0;i--){
            this.max_heapify(i);
        }
    }

    @Override
    public int[] heap_sort(int[] unordered_array) {
        build_max_heap(unordered_array.clone());
        int i = this.current_index -1;
        while(this.current_index!=0){
            unordered_array[i] = this.max_heap_extract();
            i--;
        }
        return unordered_array;
    }

    @Override
    public void max_heap_insert(int element) {
        if(current_index >= this.max_size){
            throw new RuntimeException("you exceed max size limit");
        }
        array_elements[current_index] = element;
        int i = current_index;
        current_index++;
        int parent_index;
        while(i!=0 && array_elements[i] > array_elements[(parent_index=get_parent_ind(i))]){
            swap(i,parent_index);
            i = parent_index;
        }
    }
    
    @Override
    public int max_heap_extract() {
        if(current_index == 0){
            throw new RuntimeException("there is no elements to extract");
        }
        int value = array_elements[0];
        current_index--;
        // to correct heap structural propety
        array_elements[0] = array_elements[current_index];
        array_elements[current_index] = 0;
        // to correct heap order property
        this.max_heapify(0);
        return value;
    }
    
    public int[] getArray(){
        int[] arr = new int[this.current_index];
        for(int i=0;i<this.current_index;i++){
            arr[i] = this.array_elements[i];
        }
        return arr;
    }
    
    private int get_parent_ind(int child_index){
        return Math.max((child_index-1)/2,0);
    }
    
    private int get_leftchild_ind(int parent_index){
        int ind = parent_index*2 + 1;
        return ind;
    }
    
    private int get_rightchild_ind(int parent_index){
        int ind = parent_index*2 + 2;
        return ind;
    }

    private void swap(int first_index , int second_index){
        int temp = array_elements[first_index];
        array_elements[first_index] = array_elements[second_index];
        array_elements[second_index] = temp;
    }
}