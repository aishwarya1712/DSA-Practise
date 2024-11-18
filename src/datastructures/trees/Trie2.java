package datastructures.trees;

import java.util.ArrayList;
import java.util.List;

public class Trie2 {

    static class Node {
        Node[] children;
        boolean eow;

        public Node() {
            eow = false;
            children = new Node[26];
            for (int i = 0; i < children.length; i++) {
                children[i] = null;
            }
        }
    }

    static Node root = new Node();

    public static void insertIntoTrie(String word){
        /* TC: O(word length) */
        Node curr = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            int index = c - 'a';
            if(curr.children[index] == null) {
                curr.children[index] = new Node();
            }
            if (i == word.length() - 1) {
                curr.children[index].eow = true;
            }
            curr = curr.children[index]; // go to the next level after inserting one character
        }

    }

    public static boolean searchInTrie(String word){
        /*TC: O(word length)*/
        Node curr = root;
        for(int i = 0; i < word.length(); i++){
            int index = word.charAt(i) - 'a';
            if(curr.children[index] == null){
                return false;
            }
            if(i == word.length() - 1){
                return curr.children[index].eow;
            }
            curr = curr.children[index];
        }
        return false;
    }

    public static boolean wordBreak(String word){
        if(word.length() == 0){
            return true;
        }
        // word = 'ilikesamsung'
        for(int i = 0; i < word.length(); i++){
            String left = word.substring(0, i + 1);
            String right = word.substring(i + 1);
            if(searchInTrie(left) && wordBreak(right)){
                return true;
            }
        }
        return false;
    }

    public static boolean startsWith(String prefix){
        Node curr = root;
        for(int i = 0; i < prefix.length(); i++){
            int index = prefix.charAt(i) - 'a';
            if(curr.children[index] == null){
                return false;
            }
            curr = curr.children[index];
        }
        return true;
    }

    private static int countNodes(Node root){
        if(root == null){
            return 0;
        }
        int count = 0;
        for(int i = 0; i < 26; i++){
            if(root.children[i] != null){
                count = count + countNodes(root.children[i]);
            }
        }
        return 1 + count;
    }


    public static int countUniqueSubstrings(String word){
        // get all suffixes
        List<String> suffixes = new ArrayList<>();
        for(int i = 0; i < word.length(); i++){
            String substr = word.substring(i, word.length());
            suffixes.add(substr);
        }

        // add all suffixes to trie
        root = new Node();
        for(String suffix: suffixes){
            insertIntoTrie(suffix);
        }

        // total number of nodes of trie = # of unique substrings
        return countNodes(root);
    }


    public static String result = "";
    public static void longestWordWithAllPrefixes(Node curr, StringBuilder temp){
        if(curr == null){
            return;
        }
        for(int i = 0; i < 26; i++){
            if(curr.children[i] != null && curr.children[i].eow == true){
                char c = (char)(i + 'a');
                temp.append(c);
                if(temp.length() > result.length()){
                    result = temp.toString();
                }
                longestWordWithAllPrefixes(curr.children[i], temp);
                temp.deleteCharAt(temp.length() - 1);
            }
        }

    }

    public static void main(String[] args){
        String[] words = {"there", "their", "a", "any", "the"};

        for(int i = 0; i < words.length; i++){
            insertIntoTrie(words[i]);
        }
        System.out.println(searchInTrie("there"));
        System.out.println(wordBreak("theretheir"));
        System.out.println(wordBreak("therea"));
        System.out.println(wordBreak("thereforyou"));
        System.out.println(startsWith("anu"));
        System.out.println(countUniqueSubstrings("ababa"));

        root = new Node();
        String[] words2 = {"a", "banana", "ap", "app", "appl", "apple", "apply"};
        for(int i = 0; i < words2.length; i++){
            insertIntoTrie(words2[i]);
        }
        longestWordWithAllPrefixes(root, new StringBuilder(""));
        System.out.println(result);
    }
}
