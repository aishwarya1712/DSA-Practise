package datastructures.heaps;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap {
    public ArrayList<Integer> heap;

    public MaxHeap(){
        heap = new ArrayList<>();
    }

    private void swap(int index1, int index2){
        int value1 = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, value1);
    }

    private void heapifyUp(int index){
        int parent = (index - 1)/ 2;
        while(index > 0 && heap.get(index) > heap.get(parent)){
            swap(parent, index);
            index = parent;
            parent = (index - 1)/2;
        }
    }
    public void insert(int value){
        // first add to the end
        heap.add(value);
        // max heap, so max value first. so keep exchanging this value with its parent if this value > parent
        heapifyUp(heap.size() - 1);
    }

    private void heapifyDown(int index){
        // compare index value with left qnd right, switch it with the grater one
        int leftChildIndex = (2 * index) + 1;
        int rightChildIndex = (2 * index) + 2;
        int swapIndex = index;
        if(leftChildIndex < heap.size() && heap.get(leftChildIndex) > heap.get(swapIndex)){
            swapIndex = leftChildIndex;
        } else if(rightChildIndex < heap.size() && heap.get(rightChildIndex) > heap.get(swapIndex)) {
            swapIndex = rightChildIndex;
        }
        if(index != swapIndex){
            swap(index, swapIndex);
            heapifyDown(swapIndex);
        }
    }
    public int removeMax(){
        int max = heap.get(0);
        int lastValue = heap.remove(heap.size() - 1);
        if(!heap.isEmpty()){
            heap.set(0, lastValue);
            heapifyDown(0);
        }
        return max;

    }

    public List<Integer> heapSort(){
        List<Integer> sorted = new ArrayList<>();
        while(!heap.isEmpty()){
            sorted.add(this.removeMax());
        }
        return sorted;
    }

    public void printHeap() {
        System.out.println(heap);
    }

    public static void main(String[] args){
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.insert(10);
        maxHeap.insert(20);
        maxHeap.insert(15);
        maxHeap.insert(30);
        maxHeap.insert(40);
        maxHeap.printHeap();
        maxHeap.removeMax();
        maxHeap.printHeap();

    }

}
