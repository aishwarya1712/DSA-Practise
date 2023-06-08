package datastructures.arrays;

import java.util.Arrays;

public class ArraySort {
    /** Bubble Sort
     * Definition: Repeatedly compare adjacent elements and swap if the right is less than left until the array is sorted.
     * Advantages:
     * 1) Easy to understand & implement
     * 2) No additional memory space
     * 3) Is "stable", preserves relative order of equal elements
     *
     * Disadvantages:
     * 1) Worst case time complexity is O(n^2), so not suitable for larger datasets
     * **/
    public void bubbleSort(Integer[] array){
        boolean swappedSomething = true;
        /* keep iterating until no swaps take place.
        ** if we get through an iteration without any swap, the array is sorted
        * */
        while(swappedSomething) {
            swappedSomething = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    swappedSomething = true;
                    /* swap */
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }
        }
    }

    public void bubbleSortTwo(Integer[] array){
        for(int p = 0; p < array.length - 1 ; p++) {
            boolean swappedSomething = false;
            /* first optimization: don't check the already sorted part of the array in the end */
            for (int i = 0; i < array.length - 1 - p; i++) {
                if (array[i] > array[i + 1]) {
                    /* swap */
                    swappedSomething = true;
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }
            /* second optimization: if no swaps took place in the iteration, array is sorted, so don't proceed to next pass */
            if(!swappedSomething){
                break;
            }
        }
    }

    /** Selection Sort
     * Definition: In each iteration, find the lowest element and place it in the front of the array.
     * Repeatedly do this until the end of the array is reached.
     * Advantages:
     * 1) Easy to understand and implement
     * 2) No additional memory space
     *
     * Disadvantages:
     * 1) Very high time complexity of O(n^2), so not good for large datasets
     * 2) Is not "stable", does not preserve relative order of equal elements
     */
    public void selectionSort(Integer[] array){
        int pass = 0;
        while(pass < array.length - 1) {
            int minIndex = pass;
            /* in each pass, elements from index pass to end are checked */
            for (int i = pass ; i < array.length; i++) {
                if (array[i] < array[minIndex]) {
                    minIndex = i;
                }
            }
            int temp = array[pass];
            array[pass] = array[minIndex];
            array[minIndex] = temp;
            pass++;
        }
    }
}
