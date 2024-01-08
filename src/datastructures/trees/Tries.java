package datastructures.trees;

import java.util.ArrayList;
import java.util.List;

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

    public static List<String> getSuffixes(String key){
        List<String> result = new ArrayList<>();
        for(int i = 0; i < key.length(); i++){
            result.add(key.substring(i, key.length()));
        }
        return result;
    }

    public static int countNodes(Node root){
        if(root == null){
            return 0;
        }
        int count = 0;
        for(int i = 0; i < 26; i++){
            if(root.children[i] != null){
                count += countNodes(root.children[i]);
            }
        }
        return 1 + count;
    }
    public static int countUniqueSubstrings(String key){
        // example: for input ababa, output is 10 -> a, ab, aba, abab, ababa, b, ba, bab, baba, ''

        // clear tree
        root = null;
        root = new Node();
        // step 1: generate suffixes
        List<String> suffixes = getSuffixes(key);
        System.out.println(suffixes.size());

        // step 2: insert into trie
        // create new trie
        for(int i = 0; i < suffixes.size(); i++){
            insertIntoTrie(suffixes.get(i));
        }

        // step 3: count # of prefix
        int numPrefixes = countNodes(root);

        return numPrefixes;

    }

    public static String ans = "";
    public static void longestWordWithAllPrefixes(Node root, StringBuilder temp){
        if(root == null){
            return;
        }

        for(int i = 0; i < 26; i++ ){
            if(root.children[i] != null && root.children[i].eow == true){
                // update temp
                temp.append((char)(i + 'a'));

                // compare temp and ans
                if(temp.length() > ans.length()){
                    ans = temp.toString();
                }

                // call recursively on current child
                longestWordWithAllPrefixes(root.children[i], temp);

                // we'll backtrack and come here so we have to remove the character we just added
                temp.deleteCharAt(temp.length() - 1);

            }
        }
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
        System.out.println(startsWith("wo"));
        System.out.println( countUniqueSubstrings("ababa"));
        System.out.println(searchInTrie("a"));


        System.out.println("ababa: " + countUniqueSubstrings("ababa"));
        System.out.println("apple: " + countUniqueSubstrings("apple"));


        root = new Node();
        String[] words2 = {"a", "banana", "ap", "app", "appl", "apple", "apply"};
        for(int i = 0; i < words2.length; i++){
            insertIntoTrie(words2[i]);
        }
        longestWordWithAllPrefixes(root, new StringBuilder(""));
        System.out.println(ans);



    }
}
