package datastructures.heaps;

import java.util.ArrayList;
import java.util.Arrays;

// generics - We want to be able to initialize Heap<Integer> or Heap<Some Other Class> later
// Comparable<T> to compare two objects
public class Heap<T extends Comparable<T>>{
    private ArrayList<T> list;

    public Heap(){
        list = new ArrayList<>();
    }

    // since we'll be swapping elements for our insertion and deletion methods, we'll create a swap function
     private void swap(int index1, int index2){
        T temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
     }

     private int parent(int i){
        return (i - 1) / 2;
     }

     private int left(int i){
        return (2 * i) + 1;
     }

     private int right(int i){
        return (2 * i) + 2;
     }

     public void insert(T value){
        list.add(value);
        upheap(list.size() - 1);
     }

     private void upheap(int index){
        if(index == 0){
            return;
        }

        int parent = parent(index);
         if(list.get(index).compareTo(list.get(parent)) < 0) {
            swap(parent, index);
            upheap(parent);
        }
     }

     public void remove(){
        if(list.isEmpty()){
            return;
        }

        T temp = list.get(0);
        T last = list.remove(list.size() - 1);

        if(!list.isEmpty()){
            list.set(0, last);
            downheap(0);
        }
     }
    private void downheap(int index){
        int left = left(index);
        int right = right(index);
        int min = index;

        if(left < list.size() && list.get(min).compareTo(list.get(left)) > 0){
            min = left;
        }
        if(right < list.size()  && list.get(min).compareTo(list.get(right)) > 0){
            min = right;
        }

        if(min != index){
            swap(min, index);
            downheap(min);
        }

    }

    public void print(){
        for(T val: list){
            System.out.print(val + "->");
        }
        System.out.println();
    }

    public static  void main(String[] args){
        Heap<Integer> heap = new Heap<>();
        int[] values= {34, 45, 22, 79, 1, 6};

        for(int i = 0; i < values.length; i++){
            heap.insert(values[i]);
        }
        heap.print();
        heap.remove();
        heap.print();
    }



}
