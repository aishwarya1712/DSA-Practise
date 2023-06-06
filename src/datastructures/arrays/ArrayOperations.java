package datastructures.arrays;

public class ArrayOperations {
    /* REVERSE ARRAY METHOD 1 */
    public Integer[] reverseArray(Integer[] array){
        if(array == null){
            return null;
        }

        if(array.length == 1){
            return array;
        }
        int i = 0;
        int j = array.length - 1;
        while(i < j){
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
        return array;
    }

    /* REVERSE ARRAY METHOD 2 */
    public void reverseArrayRecursive(Integer[] array, int start, int end){
        if(array == null || start >= end) {
            return;
        }
        int temp = array[start];
        array[start] = array[end];
        array[end] = temp;
        reverseArrayRecursive(array, start + 1, end - 1);
    }

    /* ROTATE ARRAY LEFT METHOD 1 */
    public void rotateArrayToLeft(Integer[] array, int d){
        /* shift array to left by one position d times */
        int pass = 0;
        while(pass < d) {
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
    public void rotateArrayToLeftUsingReverse(Integer[] array, int d){
        // [1, 2, 3, 4, 5]
        reverseArrayRecursive(array, 0, array.length - 1); // [5, 4, 3, 2, 1]
        reverseArrayRecursive(array, 0, d); // [3, 4, 5, 2, 1]
        reverseArrayRecursive(array, d + 1, array.length - 1); // [3, 4, 5, 1, 2]
    }

    /* ROTATE ARRAY RIGHT METHOD 1 */
    private void rotateArrayToRight(Integer[] array, int d){
        /* rotate array to right by one position d times */
        int pass = 0;
        while(pass < d) {
            Integer temp = array[array.length - 1];
            for (int i = array.length - 1; i > 0; i--) {
                array[i] = array[i - 1];
            }
            array[0] = temp;
            pass++;
        }
    }

    /* ROTATE ARRAY RIGHT METHOD 2 */
    public void rotateArrayToRightUsingReverse(Integer[] array, int d){
        // [1, 2, 3, 4, 5]
        reverseArrayRecursive(array, 0, array.length - 1); // [5, 4, 3, 2, 1]
        reverseArrayRecursive(array, 0, d - 1); // [4, 5, 3, 2, 1]
        reverseArrayRecursive(array, d, array.length - 1); // [4, 5, 1, 2, 3]
    }
}
