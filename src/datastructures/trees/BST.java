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

    public static Node findInorderSuccessor(Node root){
        while(root.left != null){
            root = root.left;
        }
        return root;
    }

    public static Node deleteNode(Node root, int val){
        if(val < root.data){
            root.left = deleteNode(root.left, val);
        } else if(val > root.data){
            root.right = deleteNode(root.right, val);
        } else{
            // val found in tree

            // case 1: node to be deleted has no children, both LC and RC of node to be deleted are null
            if(root.left == null && root.right == null){
                return null;
            }

            // case 2: node to be deleted has only one child, either one of left or right are null
            if(root.left == null){
                return root.right;
            } else if(root.right == null){
                return root.left;
            }

            // case 3: node to be deleted has two children
            // step 1: find inorder successor, which is the left-most element in the right subtree of the node to be deleted
            Node inorderSuccessor = findInorderSuccessor(root.right);

            // step 2: replace value of node to be deleted with value of inorder successor
            root.data = inorderSuccessor.data;

            // step 3: delete the inorder successor, so call the delete function
            root.right = deleteNode(root.right, inorderSuccessor.data);
        }
        return root;
    }

    public static void printInRange(Node root, int X, int Y){
        if(root == null){
            return;
        }
        if(root.data >= X && root.data <= Y){
            printInRange(root.left, X, Y);
            System.out.print(root.data);
            printInRange(root.right, X, Y);
        }
        if(root.data >= Y){
            printInRange(root.left, X, Y);
        }
        else if(root.data <= X){
            printInRange(root.right, X, Y);

        }
    }

    public static void main(String[] args){
        int[] values = {8, 5, 1, 3, 4, 6, 10, 11, 14};
        Node root = null;
        for(int i = 0; i < values.length; i++){
            root = insert(root, values[i]);
        }

        inorder(root);
        System.out.println();
        boolean result = searchBst(root, 912);
        System.out.println(result);

        deleteNode(root, 4); // no child
        inorder(root);
        System.out.println();

        deleteNode(root, 10); // single child
        inorder(root);
        System.out.println();

        deleteNode(root, 5); // two children
        inorder(root);
        System.out.println();

        // adding back values so we can call printInRange function
        values = new int[]{8, 5, 1, 3, 4, 6, 10, 11, 14};
        root = null;
        for(int i = 0; i < values.length; i++){
            root = insert(root, values[i]);
        }
        printInRange(root, 3, 10);

    }
}
