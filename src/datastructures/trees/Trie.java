package datastructures.trees;

/* leetcode */
public class Trie {
    static class TrieNode{
        public TrieNode[] children;
        public boolean eow;

        public TrieNode(){
            children = new TrieNode[26];
            for(int i = 0; i < 26; i++){
                children[i] = null;
            }
            eow = false;
        }
    }

    public TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for(int i = 0; i < word.length(); i++){
            int index = word.charAt(i) - 'a';
            if(current.children[index] == null){
                current.children[index] = new TrieNode();
            }
            if( i == word.length() - 1){
                current.children[index].eow = true;
            }
            current = current.children[index];
        }
    }

    public boolean search(String word) {
        TrieNode current = root;
        for(int i = 0; i < word.length(); i++){
            int index = word.charAt(i) - 'a';
            if(current.children[index] == null){
                return false;
            }
            if(i == word.length() - 1 && current.children[index].eow == true){
                return true;
            }
            current = current.children[index];
        }
        return false;
    }

    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for(int i = 0; i < prefix.length(); i++){
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
         Trie obj = new Trie();
         for(int i = 0; i < words.length; i++){
             obj.insert(words[i]);
         }
        boolean param_2 = obj.search("apple");
        boolean param_3 = obj.startsWith("wom");
        System.out.println(param_2);
        System.out.println(param_3);
    }
}
