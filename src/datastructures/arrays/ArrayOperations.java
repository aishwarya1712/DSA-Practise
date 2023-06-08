package datastructures.arrays;

public class ArrayOperations {
    /* REVERSE ARRAY METHOD 1 */
    public Integer[] reverseArray(Integer[] array) {
        if (array == null) {
            return null;
        }

        if (array.length == 1) {
            return array;
        }
        int i = 0;
        int j = array.length - 1;
        while (i < j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
        return array;
    }

    /* REVERSE ARRAY METHOD 2 */
    public void reverseArrayRecursive(Integer[] array, int start, int end) {
        /* this method can also be used to reverse a portion of the array */
        if (array == null || start >= end) {
            return;
        }
        int temp = array[start];
        array[start] = array[end];
        array[end] = temp;
        reverseArrayRecursive(array, start + 1, end - 1);
    }

    /* ROTATE ARRAY LEFT METHOD 1 */
    public void rotateArrayToLeft(Integer[] array, int d) {
        /* shift array to left by one position d times */
        int pass = 0;
        while (pass < d) {
            /* this is the code for rotating array to left one time */
            /* store first element in temp */
            Integer temp = array[0];
            for (int i = 0; i < array.length - 1; i++) {
                array[i] = array[i + 1];
            }
            array[array.length - 1] = temp;
            pass++;
        }
    }

    /* ROTATE ARRAY LEFT METHOD 2 */
    public void rotateArrayToLeftUsingReverse(Integer[] array, int d) {
        // [1, 2, 3, 4, 5]
        reverseArrayRecursive(array, 0, array.length - 1); // [5, 4, 3, 2, 1]
        reverseArrayRecursive(array, 0, d); // [3, 4, 5, 2, 1]
        reverseArrayRecursive(array, d + 1, array.length - 1); // [3, 4, 5, 1, 2]
    }

    /* ROTATE ARRAY RIGHT METHOD 1 */
    private void rotateArrayToRight(Integer[] array, int d) {
        /* rotate array to right by one position d times */
        int pass = 0;
        while (pass < d) {
            Integer temp = array[array.length - 1];
            for (int i = array.length - 1; i > 0; i--) {
                array[i] = array[i - 1];
            }
            array[0] = temp;
            pass++;
        }
    }

    /* ROTATE ARRAY RIGHT METHOD 2 */
    public void rotateArrayToRightUsingReverse(Integer[] array, int d) {
        // [1, 2, 3, 4, 5]
        reverseArrayRecursive(array, 0, array.length - 1); // [5, 4, 3, 2, 1]
        reverseArrayRecursive(array, 0, d - 1); // [4, 5, 3, 2, 1]
        reverseArrayRecursive(array, d, array.length - 1); // [4, 5, 1, 2, 3]
    }

    /* SEARCH ELEMENT IN UNSORTED ARRAY */
    public int linearSearch(Integer[] array, Integer num) {
        /* checks if num is present in an unsorted array */
        if (array == null || array.length == 0 || num == null) {
            return -1;
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] == num) {
                return i;
            }
        }
        return -1;
    }

    /* SEARCH ELEMENT IN SORTED ARRAY: Often used in combination with other algorithms */
    public int binarySearch(Integer[] array, Integer num, int low, int high) {
        /* checks if num is present in a sorted array */
        if (high < low) {
            return -1;
        }
        int mid = (low + high) / 2;
        if (array[mid] == num) {
            return mid;
        } else if (array[mid] < num) {
            return binarySearch(array, num, mid + 1, high);
        } else {
            return binarySearch(array, num, low, mid - 1);
        }
    }

    /* INSERT ELEMENT AT GIVEN POSITION IN UNSORTED ARRAY */
    public void insertElementUnsorted(Integer[] array, int lastFilledIndex, int position, int numToInsert) {
        /* insertElementUnsorted(array of length 15, 4, 2, 101); */
        int arrayLastIndex = array.length - 1;
        if (position > arrayLastIndex) {
            return;
        }
        /* Case 1: insert at end */
        if (position == lastFilledIndex + 1) {
            array[position] = numToInsert;
        }
        /* Case 2: insert at given position */
        else {
            for (int i = lastFilledIndex; i >= position; i--) {
                array[i + 1] = array[i];
            }
            array[position] = numToInsert;
        }
    }

    /* INSERT ELEMENT IN A SORTED ARRAY */
    public void insertElementSorted(Integer[] array, int numToInsert, int lastFilledIndex) {
        if (numToInsert >= array[lastFilledIndex]) {
            array[lastFilledIndex + 1] = numToInsert;
            return;
        }
        int i;
        for (i = lastFilledIndex; (i >= 0 || array[i] > numToInsert); i++) {
            array[i + 1] = array[i];
        }
        array[i + 1] = numToInsert;
    }

    /* DELETE AN ELEMENT IN AN UNSORTED ARRAY */
    public int deleteElementUnsorted(Integer[] array, int numToDelete) {
        /* deletes element and returns length of new array  */
        int deleteIndex = linearSearch(array, numToDelete);
        if (deleteIndex == -1) {
            return array.length;
        }
        /* till array.length - 1 because we don't want to copy null into the last filled element */
        for (int i = deleteIndex; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }
        return array.length - 1;
    }

    /* DELETE AN ELEMENT IN A SORTED ARRAY */
    public int deleteElementSorted(Integer[] array, int numToDelete) {
        int deleteIndex = binarySearch(array, numToDelete, 0, array.length - 1);
        if (deleteIndex == -1) {
            return array.length;
        }
        for (int i = deleteIndex; i < array.length; i++) {
            array[i] = array[i + 1];
        }
        return array.length - 1;
    }
}
