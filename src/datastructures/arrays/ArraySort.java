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

    /** Merge Sort
     * Definition: A sorting algorithm that works by dividing the array into smaller sub-arrays until they can no longer be divided,
     * then merging the sub-arrays in sorted order recursively until the entire array is fully sorted.
     * This algorithm is based on the Divide & Conquer paradigm.
     * Advantages:
     * 1) Worst case time complexity is O(NlogN), performs well on large datasets. Can sort billions of numbers in seconds.
     * 2) It is stable. Relative order of equal elements is preserved.
     * Disadvantages:
     * 1) Required additional space complexity O(N)
     * 2) Not an in-place sorting algorithm
     * 3) Might be overkill for smaller datasets.
     * */
    public void mergeSort(Integer[] inputArray){
        int inputLength = inputArray.length;

        /* if the input array length is less than 2, i.e 1 or 0, the array is already in sorted order, so simply return so the next line of code can be executed */
        if(inputLength < 2)
            return;
        // -------------------- DIVIDE  --------------------

        /* find the midIndex and create two arrays leftHalf and rightHalf */
        int midIndex = inputLength / 2;
        Integer[] leftHalf = new Integer[midIndex];
        Integer[] rightHalf = new Integer[inputLength - midIndex]; // not [midIndex] to account for odd number of elements in inputArray

        /* populate left half array from 0 until midIndex */
        for(int i = 0; i < midIndex; i++){
            leftHalf[i] = inputArray[i];
        }

        /* populate right half array from midIndex until inputLength */
        for(int i = midIndex; i < inputLength; i++){
            rightHalf[i - midIndex] = inputArray[i];
        }
        System.out.println("Left half: " + Arrays.toString(leftHalf));
        System.out.println("Right half: " + Arrays.toString(rightHalf));

        /* recursively split leftHalf and rightHalf sub arrays */
        mergeSort(leftHalf);
        mergeSort(rightHalf);
        // -------------------- END DIVIDE  --------------------

        /* merge leftHalf and rightHalf into one */
        merge(inputArray, leftHalf, rightHalf);
    }

    private void merge(Integer[] inputArray, Integer[] leftHalf, Integer[] rightHalf){
        // -------------------- CONQUER  --------------------
        System.out.println("Input array during merge: " + Arrays.toString(inputArray));
        System.out.println("Merging: "+ Arrays.toString(leftHalf) +" and " + Arrays.toString(rightHalf));
        /* i is for leftHalf, j is for rightHalf and k is for storing sorted values back into inputArray */
        int i = 0, j = 0, k = 0;
        while(i < leftHalf.length && j < rightHalf.length){
            if(leftHalf[i] <= rightHalf[j]){
                /* if lh[i] is smaller, store that into input array and increment i pointer */
                inputArray[k] = leftHalf[i];
                i++;
            } else {
                inputArray[k] = rightHalf[j];
                /* if rh[i] is smaller, store that into input array and increment j pointer */
                j++;
            }
            k++;
        }

        /* if i or j reached the end of lh or rh, copy over the remaining elements into inputArray */
        /* only one of the two while loops is actually executed */
        while(i < leftHalf.length){
            inputArray[k] = leftHalf[i];
            i++;
            k++;
        }
        while(j < rightHalf.length){
            inputArray[k] = rightHalf[j];
            j++;
            k++;
        }
        // -------------------- END CONQUER  --------------------
    }

}
