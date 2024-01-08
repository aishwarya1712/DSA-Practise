package datastructures.trees;

public class Tries {

    static class Node{
        Node[] children;
        boolean eow;

        public Node(){
            children = new Node[26]; // initializing an empty array of 26
            for(int i = 0; i < 26; i++){
                children[i] = null; // important, because
            }
            eow = false;
        }
    }



    static Node root = new Node();

    public static void insertIntoTrie(String word){
        Node current = root;
        for(int i = 0; i < word.length(); i++){
            int index = word.charAt(i) - 'a';
            if(current.children[index] == null){
                Node newNode = new Node();
                current.children[index] = newNode;
            }
            if(i == word.length() - 1){
                current.children[index].eow = true;
            }
            current = current.children[index];
        }
    }

    public static boolean searchInTrie(String key){
        Node current = root;
        for(int i = 0; i < key.length(); i++){
            int index = key.charAt(i) - 'a';
            if(current.children[index] == null){
                return false;
            }
            current = current.children[index];
            if(i == key.length() - 1 && current.eow == true){
                return true;
            }
        }
        return false;
    }


    public static boolean wordBreak(String key){
        if(key.length() == 0){
            return true;
        }
        for(int i = 1; i <= key.length(); i++){
            String left = key.substring(0, i);
            System.out.println("Checking if " + left + " exists in trie");
            if(searchInTrie(left)){
                String right = key.substring(i, key.length());
                System.out.println(left + " found! Now recursively applying word break on: "+ right);
                if(wordBreak(right)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean startsWith(String prefix){
        Node current = root;
        for(int i =0; i < prefix.length(); i++){
            int index = prefix.charAt(i) - 'a';
            if(current.children[index] == null){
                return false;
            }
            current = current.children[index];
        }
        return true;
    }

    public static void main(String[] args){
        String[] words = {"apple", "app", "mango", "man", "woman"};
        for(int i = 0; i < words.length; i++){
            insertIntoTrie(words[i]);
        }
        System.out.println(wordBreak("applemango"));
        System.out.println(wordBreak("applemangoe"));
        System.out.println(startsWith("app"));
        System.out.println(startsWith("moon"));
        System.out.println(startsWith("wo "));
//        System.out.println(searchInTrie("a"));

    }
}
