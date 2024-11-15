package datastructures.trees;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree2 {
    static class Node {
        int data;
        Node left;
        Node right;
        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree {
        int idx = -1;
        public Node buildTreePreorder(int[] nodes) {
            idx++;
            // preorder: root, left, right
            if (nodes[idx] == -1) {
                return null;
            }
            Node node = new Node(nodes[idx]);
            node.left = buildTreePreorder(nodes);
            node.right = buildTreePreorder(nodes);

            return node;
        }

        public void traverseTreePreorder(Node root){
            // preorder: root, left, right
            if(root == null){
                return;
            }
            System.out.print(root.data +" ");
            traverseTreePreorder(root.left);
            traverseTreePreorder(root.right);
        }

        public void inorderTraversal(Node root){
            // inorder: left, root, right
            if(root == null){
                return;
            }
            inorderTraversal(root.left);
            System.out.print(root.data + " ");
            inorderTraversal(root.right);
        }

        public void postorderTraversal(Node root){
            // postorder: left, right, root
            if(root == null){
                return;
            }
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            System.out.print(root.data + " ");
        }

        public void levelOrderTraversal(Node root){
            if(root == null){
                return;
            }
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null);
            while(!q.isEmpty()){
                Node curr = q.remove();
                if(curr == null){
                    System.out.println();
                    if(!q.isEmpty()){
                        q.add(null);
                    }
                }
                else {
                    System.out.print(curr.data +" ");

                    if(curr.left != null){
                        q.add(curr.left);
                    }
                    if(curr.right != null){
                        q.add(curr.right);
                    }
                }

            }
        }

        public int countNodes(Node root){
            if (root == null){
                return 0;
            }
            return 1 + countNodes(root.left) + countNodes(root.right);
        }

        public int sumOfNodes(Node root){
            if (root == null){
                return 0;
            }

            return root.data + sumOfNodes(root.left) + sumOfNodes(root.right);
        }

        public int heightOfTree(Node root){
            if(root == null){
                return 0;
            }
            return 1 + Math.max(heightOfTree(root.left), heightOfTree(root.right));
        }

        public int diameterOfTree(Node root){
            if(root == null){
                return 0;
            }
            // case 1: diameter in LST
            // case 2: diameter in RST
            // case 3: passes through root
            int leftDiam = diameterOfTree(root.left);
            int rightDiam = diameterOfTree(root.right);
            int heightDiameter = 1 + heightOfTree(root.left) + heightOfTree(root.right);
            return Math.max(heightDiameter, Math.max(leftDiam, rightDiam));
        }

        public int sumOfNodesAtLevelK(Node root, int k){
            if(root == null){
                return 0;
            }
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null);
            int level = 1;
            int sum = 0;
            while(level <= k && !q.isEmpty()){
                Node curr = q.remove();


                if(curr == null){
                    level++;
                    if(!q.isEmpty()){
                        q.add(null);
                    }
                } else{
                    if(level == k){
                        sum = sum + curr.data;
                    }
                    if(curr.left != null){
                        q.add(curr.left);
                    }
                    if(curr.right != null){
                        q.add(curr.right);
                    }
                }
            }
            return sum;
        }

    }

    public static void main(String[] args){
        BinaryTree binaryTree = new BinaryTree();
        int[] nodes = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        Node root = binaryTree.buildTreePreorder(nodes);

        /* Traversals */
//        binaryTree.traverseTreePreorder(root);
//        System.out.println();
//        binaryTree.inorderTraversal(root);
//        System.out.println();
//        binaryTree.postorderTraversal(root);
//        System.out.println();
        binaryTree.levelOrderTraversal(root);

        System.out.println(binaryTree.countNodes(root));
        System.out.println(binaryTree.sumOfNodes(root));
        System.out.println(binaryTree.heightOfTree(root));
        System.out.println(binaryTree.diameterOfTree(root));

        System.out.println(binaryTree.sumOfNodesAtLevelK(root, 1));
    }


}
