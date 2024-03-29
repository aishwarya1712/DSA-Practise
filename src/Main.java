import blind75.LongestPalindromicSubstring;
import datastructures.arrays.ArrayBasics;
import datastructures.arrays.ArrayOperations;
import datastructures.arrays.ArrayProblems;
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

//        ArraySort arraySort = new ArraySort();
//        Integer[] arrayToSort = new Integer[]{12, 11, 13, 5, 6};
//        arrays.printArray(arrayToSort);
//        arraySort.quickSort(arrayToSort);
//        arrays.printArray(arrayToSort);

        ArrayProblems arrayProblems = new ArrayProblems();
//        arrayProblems.largestThreeDistinctElements(new Integer[]{12, 11, 11, 5, 6});
//        arrayProblems.secondLargestElement(new Integer[]{11, 11, 11, 11, 11});
//        arrayProblems.pushZerosToEnd(new Integer[]{1, 2, 0, 3, 6});
//        arrayProblems.evenPositionedGreaterThanOdd(new Integer[]{1, 2, 3, 4, 5, 6});
//        arrayProblems.oddPositionGreaterThanEven(new Integer[]{1, 2, 3, 4, 5, 6});
//        arrayProblems.segregateEvenOdd2(new Integer[]{1,9,5,3,2,6,7,11});
//        arrayProblems.segregateEvenOddLomutoPartitionScheme(new Integer[]{2,4,6,8});
//        arrayProblems.cyclicSort(new Integer[]{10, 7, 9, 2, 8, 3, 5, 4, 6, 1});
//        arrayProblems.numberOfTriangles(new Integer[]{1, 3, 4, 6, 12, 13, 16, 20});
//        arrayProblems.printDistinct(new Integer[]{12,10,9,45,2,10,10,45});
//        arrayProblems.findSingle(new Integer[]{3, 5, 4, 5, 3, 4,2, 2, 10});
//        arrayProblems.printLeaders(new Integer[]{16, 17, 4, 3, 5, 2});
        arrayProblems.findSubArrayWithGivenSum(new Integer[]{1, 4, 0, 0, 3, 10, 5}, 7);
    }
    public static void main(String[] args) {
//       arrayMethods();

        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
        System.out.println(longestPalindromicSubstring.longestPalindrome_BruteForce("babad"));
    }
}