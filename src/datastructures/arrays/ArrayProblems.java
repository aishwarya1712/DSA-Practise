package datastructures.arrays;

import java.util.Arrays;
import java.util.HashMap;

public class ArrayProblems {

    public void largestThreeDistinctElements(Integer[] array){
        if(array.length < 3){
            System.out.println("Invalid input");
            return;
        }

        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE, third = Integer.MIN_VALUE;
        for(int i = 0; i < array.length; i++){
            if(array[i] > first){
                third = second;
                second = first;
                first = array[i];
            } else if(array [i] > second && array[i] < first){
                third = second;
                second = array[i];
            } else if (array[i] > third&& array[i] < second){
                third = array[i];
            }
        }
        System.out.println("The three largest numbers are: " + first +", " + second + ", " + third);
    }

    public void secondLargestElement(Integer[] array){
        if(array.length < 2){
            return;
        }

        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;

        for(int i = 0; i < array.length; i++){
            if(array[i] > first){
                second = first;
                first = array[i];
            } else if(array[i] > second && array[i] < first){
                second = array[i];
            }
        }
        System.out.println("Second largest number is: " + second);
    }

    public void pushZerosToEnd(Integer[] array){
        if(array.length < 2){
            return;
        }
        /* two pointer approach */
        int nonZeroPointer = 0;
        for(int i = 0; i <array.length; i++){
            if(array[i] != 0){
                swap(array, nonZeroPointer, i);
                nonZeroPointer++;
            }
        }
        System.out.println(Arrays.toString(array));
    }

    /* if input array is [1, 2, 3, 4, 5, 6],
      output array should be [1, 6, 2, 5, 3, 4]
      Time complexity: O(N), space complexity: O(1)
       */
    public void evenPositionedGreaterThanOdd(Integer[] inputArray){
        /* Two pointer method */
        Arrays.sort(inputArray);
        Integer[] resultingArray = new Integer[inputArray.length];
        int left = 0;
        int right = inputArray.length - 1;
        int k = 0;
        while(k < resultingArray.length){
            if(k % 2 == 0){
                resultingArray[k] = inputArray[left];
                left++;
            } else {
                resultingArray[k] = inputArray[right];
                right--;
            }
            k++;
        }
        System.out.println(Arrays.toString(resultingArray));
    }

    /* Time complexity: O(N), space complexity: O(1)
     * before -> array: [1, 2, 3, 4, 5, 6]
     * after -> array: [6, 1, 5, 2, 4, 3]
     * */
    public void oddPositionGreaterThanEven(Integer[] array){
        /* Important concept: when solving in O(1) time complexity, this technique may be employed.
        * Dividend = (Quotient * Divisor) + Remainder
        * Dividend = (newValue * Divisor) + originalValue
        * newValue = Quotient = Dividend / Divisor
        * originalValue = Reminder = Dividend % Divisor
        * This is a way to derive two values from one index.
        * Example, let's say we want to derive 1 and 6 from the same spot
        * Choose a divisor greater than 1 and 6, example 7
        * Dividend = 6 * 7 + 1 = 43; so we store 43 in the array.
        * originalValue = 43%7 = 1; newValue = 43/7 = 6.
        * */
        int n = array.length;
        int divisor = array[n - 1] + 1;
        int leftPointer = 0;
        int rightPointer = n -1;
        for(int i = 0; i < n; i++){
            if(i % 2 == 0){
                /* take the rightPointer value */
                array[i] = (array[rightPointer] % divisor)* divisor + array[i];
                rightPointer--;
            } else {
                array[i] = (array[leftPointer] % divisor)* divisor + array[i];
                leftPointer++;
            }
        }
        for(int i = 0; i < n; i++){
            array[i] = array[i] / divisor;
        }
        System.out.println(Arrays.toString(array));
    }

    public void segregateEvenOddNumbers(Integer[] array){
        /* TC: O(N), SC: O(N) */
        int n = array.length;
        Integer[] outputArray = new Integer[n];
        int leftPointer = 0;
        int rightPointer = n-1;
        for(int i = 0; i < n; i++){
            if(array[i] % 2 == 0){
                outputArray[leftPointer] = array[i];
                leftPointer++;
            } else {
                outputArray[rightPointer] = array[i];
                rightPointer--;
            }
        }
        System.out.println("Segregated array: " + Arrays.toString(outputArray));
    }

    public void segregateEvenOdd2(Integer[] array){
        /* TC: O(N^2), SC:O(N) */
        int n = array.length;
        int leftPointer = 0;
        int rightPointer = n-1;
        while(leftPointer < rightPointer){
            while(array[leftPointer] % 2 == 0 && leftPointer < rightPointer){
                leftPointer++;
            }
            while(array[rightPointer] % 2 != 0 && leftPointer < rightPointer){
                rightPointer--;
            }
            swap(array, leftPointer, rightPointer);
        }
        System.out.println("Segregated array: " + Arrays.toString(array));
    }

    public void segregateEvenOddLomutoPartitionScheme(Integer[] array){
        /* TC: O(N), SC: O(1) */
        /* The principles of Lomuto Partition Scheme can also be applied to Quick Sort */
        /* Basically, whenever an even element is encountered, swap it with leftPointer and increment leftPointer. */
       int leftPointer = 0;
       for(int j=0; j <array.length; j++){
           if(array[j] % 2 == 0){

               swap(array, leftPointer, j);
               leftPointer++;
           }
       }
        System.out.println("Segregated array: " + Arrays.toString(array));
    }

    public void cyclicSort(Integer[] array){
        /* this is to sort an array of length n containing numbers in the range [1,n] */
        int i = 0;
        while(i < array.length){
            int correctIndex = array[i] - 1;
            if(correctIndex != i){
                swap(array, i, correctIndex);
            } else {
                i++;
            }
        }
        System.out.println("Cyclic sorted array: " + Arrays.toString(array));
    }

    public int numberOfTriangles(Integer[] array){
        /* - sort arrayed array: {a, b, c}, where a<b<c
        - c > b ⇒ c + a > b + a
        - b + a has to be greater than b
        - Therefore, c + a > b + a > b
        - By default: c + a > b

        - c > a ⇒ c + b > a + b
        - a + b > a
        - Therefore, c + b > a + b > a
        - By default: c + b > a
        - We only need to check a + b > c

        start with count=0, a = 0, c = n-1 and b = c-1
        check if a + b > c. If it is not, increment a.
        if it is, then add b-a to the count and decrement b.
        Repeat the process as long as a < b and c > 1
        */
        if(array.length <3){
            return 0;
        }
        Arrays.sort(array);
        int count = 0;
        /* initialize c to the largest element in array */
        int c = array.length - 1;
        while(c > 1){
            int a = 0;
            int b = c-1;
            while(a < b){
                if(array[a] + array[b] > array[c]){
                    /* we found the 'a' for which array[a] + array[b] > array[c], therefore, every element after index a until index b will be a valid triangle. */
                    count = count + (b-a);
                    /* then decrement b and continue checking */
                    b--;
                } else {
                    a++;
                }
            }
            c--;
        }
        System.out.println("number of triangles: " + count);
        return count;
    }

    public void printDistinct(Integer[] array) {
        Integer[] arrayCopy = Arrays.copyOf(array, array.length);
        /* Method 1 */
        for (int i = 0; i < arrayCopy.length - 1; i++) {
            if (array[i] != null) {
                System.out.println(arrayCopy[i]);
                for (int j = i + 1; j < arrayCopy.length; j++) {
                    if (arrayCopy[j] == arrayCopy[i]) {
                        arrayCopy[j] = null;
                    }
                }
            }
        }

        /* Method 2: Alternatively, use hashmap to solve in O(N) TC and O(N) SC */
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i <array.length; i++){
            map.put(array[i], i);
        }
        System.out.println(map.keySet());

        /* Method 3: Sort array. Compare adjacent elements. Print only if not equal. */
        Arrays.sort(array);
        System.out.println("method 3");
        int i;
        for(i = 0; i < array.length - 1; i++){
            if(array[i] != array[i+1]){
                System.out.println(array[i]);
            }
        }
        System.out.println(array[i]);

    }
    private void swap(Integer[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


}
