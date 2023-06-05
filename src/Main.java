import datastructures.arrays.ArrayBasics;

public class Main {
    public static void arrayMethods(){
        ArrayBasics arrays = new ArrayBasics();
        arrays.initializeArray();
        arrays.insertAndAccessElement();
        arrays.searchElementInArray(new String[]{"Banana", "Orange", "Kiwi"}, "Orange");
        arrays.searchElementInArray(new String[]{"Banana", "Orange", "Kiwi"}, "Mango");
    }
    public static void main(String[] args) {
       arrayMethods();
    }
}