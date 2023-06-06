package datastructures.arrays;

/* Advantages
    1. Allows you to store objects of same type and refer to it by the same name
    2. Random access => efficient access performance
    3. Versatile, easy to implement
 */

/* Disadvantages
    1. Cannot change size of array once it has been declared
    2. Deletion and shifting are costly operations
    3. Large datastructures.arrays may cause out-of-memory issues
    4. Does not support storing multiple data types
    5. Wasted space if not entire array is used
 */

/* Applications
    1. In algorithms: Sorting, searching (linear, binary), dynamic programming to store intermediate results of sub-problems
    2. To implement other data structures: Matrices, stacks, queues, graphs
    3. Real-time applications: image processing, data mining, robotics
 */

public class ArrayBasics {
    public void initializeArray(){
        /* declare and allocate memory to array */
        int[] arr1 = new int[5];

        /* declaring an array literal */
        int[] arr2 = new int[]{1, 2, 3};

        /* new way to declare array literal that does not require new int[] */
        int[] arr3 = {1, 2, 3, 4, 5};
    }

    public void insertAndAccessElement(){
        /* datastructures.arrays allow random access, hence insertion, searching and access is efficient */
        String[] fruits = new String[3];

        /* insertion
        * time complexity: O(1) to insert one element and O(n) to insert N elements
        *  */
        fruits[0] = "Orange";
        fruits[1] = "Banana";
        fruits[2] = "Mango";

        /* access
        * time complexity: O(1) */
        System.out.println("The first element is: " + fruits[0]);
    }

    public void searchElementInArray(String[] arr, String ele){
        boolean found = false;
        for (String item: arr) {
            if(item.equalsIgnoreCase(ele)){
                found = true;
                break;
            }
        }
        System.out.println("Found " + ele + ": " + found);
    }

    public <T> void printArray(T[] array){
        if(array != null && array.length > 0 ){
            for(T element: array){
                System.out.println(element);
            }
        }
    }
}
