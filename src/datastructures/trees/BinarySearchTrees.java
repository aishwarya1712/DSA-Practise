package datastructures.trees;

import java.util.ArrayList;

public class BinarySearchTrees {
    /*
    Properties:
        a) Max two children
        b) all nodes to the left of root < root
        c) all nodes to the right of root > root
        d) search time complexity: O(height)
     */
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public static Node insertInBST(Node root, int val){
            if(root == null){
                return new Node(val);
            }
            if(val < root.data){
                root.left = insertInBST(root.left, val);
            }
            else {
                root.right = insertInBST(root.right, val);
            }
            return root;
        }

        public static void printInorder(Node root){
            if(root == null){
                return;
            }
            printInorder(root.left);
            System.out.print(root.data + " ");
            printInorder(root.right);

        }

        public static boolean searchInBST(Node root, int val){
            if(root == null){
                return false;
            }
            if(root.data == val){
                return true;
            }
            else if(val < root.data){
                return searchInBST(root.left, val);
            }
            else{
                return searchInBST(root.right, val);
            }
        }

        private static Node findInorderSuccessor(Node root){
            // left most node of right subtree
            while(root.left != null){
                root = root.left;
            }
            return root;
        }
        public static Node deleteNode(Node root, int val){
            if(root.data == val){
                // node to delete found
                if(root.left == null && root.right == null){
                    // case 1: no child
                    return null;
                }else if(root.left == null){
                    // case 2: 1 child
                    return root.right;
                }else if(root.right == null){
                    // case 2: 1 child
                    return root.left;
                } else {
                    // case 3: 2 children
                   Node inorderSuccessor = findInorderSuccessor(root.right);
                   root.data = inorderSuccessor.data;
                   deleteNode(root.right, inorderSuccessor.data);
                   return root;
                }

            }
            else if(root.data < val){
                // find node to delete in LST
                root.left = deleteNode(root.left, val);
            } else {
                // find node to delete in RST
                root.right = deleteNode(root.right, val);
            }
            return root;
        }

        public static void printInRange(Node root, int X, int Y){
            if (root == null){
                return;
            }
            // Root = 6, X = 5, Y = 10
            if(Y < root.data){
                // search in LST
                printInRange(root.left, X, Y);
            }
            if(X > root.data){
                // search in RST
                printInRange(root.right, X, Y);
            }
            else {
                // print nums in lst, then root, then nums in rst
                printInRange(root.left, X, Y);
                System.out.print(root.data + " ");
                printInRange(root.right, X, Y);
            }

        }

        private static void printPath(ArrayList<Integer> path){
            for(Integer val: path){
                System.out.print(val +" -> ");
            }
            System.out.println();
        }
        public static void printRootToLeafPaths(Node root, ArrayList<Integer> path){
            if(root == null){
                return;
            }
            path.add(root.data);

            if(root.left == null && root.right == null){
                // this is a leaf, so we can print the path
                printPath(path);
            }

            printRootToLeafPaths(root.left, path);
            printRootToLeafPaths(root.right, path);

            path.removeLast();
        }

        public static void main(String[] args){
            int[] values = {8, 5, 1, 3, 4, 6, 10, 11, 14};
            Node root = null;
            for(int i = 0; i < values.length; i++){
                root = insertInBST(root, values[i]);
            }
            printInorder(root);
            System.out.println(searchInBST(root, 2));
            printInRange(root, 5, 15);
            System.out.println();
            printRootToLeafPaths(root, new ArrayList<>());
        }
    }


}