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
     * 2) Is not "stable", does not preserve relative order of equal elements. But, we can make it stable by rotating element instead of swaping.
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
            /* swap array[pass] and array[minIndex] */
            int temp = array[pass];
            array[pass] = array[minIndex];
            array[minIndex] = temp;
            pass++;
        }
    }

    /** this algorithm preserves the relative order of equal elements by rotating instead of swapping
     * The time complexity is still O(n^2)
     **/
    public void stableSelectionSort(Integer[] array){
        int pass = 0;
        while(pass < array.length - 1){
            int minIndex = pass;
            for(int i = pass + 1; i < array.length; i++){
                if(array[i] < array[minIndex]){
                    minIndex = i;
                }
            }
            // [4A 5 3 1 4B 2]
            /* instead of swapping, copy array[minIndex] to the beginning and rotate each item by one position to the right */
            int temp = array[minIndex];
            for(int j = minIndex; j > pass; j--){
                array[j] = array[j-1];
            }
            array[pass] = temp;
            // [1, 4A, 4, 3, 4B, 2]
            pass++;
        }
    }

    /** Insertion Sort
     * Definition: Array virtually has two parts: sorted & unsorted. Iterate through the array. Place values from the unsorted part of the array to the sorted part.
     * Similar to sorting playing cards in your hand.
     * Advantages:
     * 1) Easy to understand and implement
     * 2) O(1) Memory complexity
     * 3) Stable algorithm: relative position of equal elements remains same before and after the sort
     * 4) Good to use when a portion of the array is already sorted
     * Disadvantages:
     * 1) Worst case O(n^2) time complexity makes this unsuitable for larger datasets
     */
    public void insertionSort(Integer[] array){
        int i = 1;
        while(i < array.length){
            /* If current element is lesser than its predecessor, it has to be moved. Check other predecessors too. */
            if(array[i] < array[i-1]){
                int j = i-1;
                /* keep reducing j until we find the right position to insert array[i] */
                while(j !=-1 && array[j] > array[i]){
                    j--;
                }
               /* save the value of array[i] so we don't lose it while copying into index i*/
                int temp = array[i];
                /* We want to insert array[i] at j+1 position.
                To make space for it, move the elements one position up.
                start at x=i, keep reducing x until x=j+1.
                 at each iteration, copy i-1 element into i */
                for(int x=i; x > j+1; x--){
                    array[x] = array[x-1];
                }
                /* insert one position after j */
                array[j+1] = temp;
            }
            i++;
        }
    }

    /** this is a more optimized way to program insertion sort **/
    public void insertionSortOptimized(Integer[] array){
        /* start at i = 1 and compare with predecessors */
        for(int i = 1; i < array.length; i++){
            int j = i-1;
            /* store array[i] in temp because we will be copying into index i later */
            int temp = array[i];
            /* keep reducing pointer j as long as element at index j is greater than element at index i */
            while(j >=0 && array[j] > temp){
                /* copy item at index j into j+1 */
                array[j+1] = array[j];
                j--;
            }
            /* insert the current element at the right position */
            array[j+1] = temp;
        }
    }

}
