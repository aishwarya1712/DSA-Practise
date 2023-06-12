import datastructures.arrays.ArrayBasics;
import datastructures.arrays.ArrayOperations;
import datastructures.arrays.ArraySort;

public class Main {
    public static void arrayMethods(){
        ArrayBasics arrays = new ArrayBasics();
//        arrays.initializeRandomArray(10, 100);
//        arrays.insertAndAccessElement();
//        arrays.searchElementInArray(new String[]{"Banana", "Orange", "Kiwi"}, "Orange");
//        arrays.searchElementInArray(new String[]{"Banana", "Orange", "Kiwi"}, "Mango");
//
//        ArrayOperations arrayOperations = new ArrayOperations();
//        Integer[] arrayToReverse = new Integer[]{10, 20, 30};
//        Integer[] reversedArray = arrayOperations.reverseArray(arrayToReverse);
//        arrays.printArray(reversedArray);
//        arrayOperations.reverseArrayRecursive(reversedArray, 0, reversedArray.length-1);
//        arrays.printArray(reversedArray);
//
//        Integer[] arrayToRotate = new Integer[]{1, 2, 3, 4, 5};
//        arrayOperations.rotateArrayToLeftUsingReverse(arrayToRotate, 2);
//        arrays.printArray(arrayToRotate);
//
//
//        System.out.println("Position: " + arrayOperations.binarySearch(new Integer[]{10, 20, 30, 40, 60, 100}, 100, 0, 5));
//        System.out.println("Position: " + arrayOperations.binarySearch(new Integer[]{10, 20, 30, 40, 60, 100}, 10, 0, 5));
//        System.out.println("Position: " + arrayOperations.binarySearch(new Integer[]{10, 20, 30, 40, 60, 100}, 70, 0, 5));

        ArraySort arraySort = new ArraySort();
        Integer[] arrayToSort = new Integer[]{12, 11, 13, 5, 6};
        arrays.printArray(arrayToSort);
        arraySort.mergeSort(arrayToSort);
        arrays.printArray(arrayToSort);
    }
    public static void main(String[] args) {
       arrayMethods();
    }
}