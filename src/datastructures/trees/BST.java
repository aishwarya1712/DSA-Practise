package datastructures.trees;

public class BST {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static Node insert(Node root, int val){
        if(root == null){
            root = new Node(val);
            return root;
        }
        if(val < root.data){
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        return root;
    }

    public static void inorder(Node root){
        if(root == null){
            return;
        }
        inorder(root.left);
        System.out.print(root.data +" ");
        inorder(root.right);
    }

    public static boolean searchBst(Node root, int data){
        if(root == null){
            return false;
        }
        if(data == root.data){
            return true;
        } else if(data < root.data){
            return searchBst(root.left, data);
        } else{
            return searchBst(root.right, data);
        }
    }

    public static void main(String[] args){
        int[] values = {5, 1, 3, 4, 2, 7};
        Node root = null;
        for(int i = 0; i < values.length; i++){
            root = insert(root, values[i]);
        }

        inorder(root);
        boolean result = searchBst(root, 912);
        System.out.println(result);

    }
}
