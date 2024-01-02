package datastructures.trees;

import com.sun.source.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTrees {

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

    static class BinaryTree{
        static int index = -1;

        public Node buildTreePreorder(int nodes[], String cameFrom){

            index++;
            System.out.println("Index: " + index +"  current element: " + nodes[index] +" Came from: " + cameFrom);
            if(nodes[index] == -1){
                return null;
            }

            Node newNode = new Node(nodes[index]);
            newNode.left = buildTreePreorder(nodes, "left");
            newNode.right = buildTreePreorder(nodes, "right");
            return newNode;
        }

        public void traverseTreePreoder(Node root){
            if(root == null){
                return;
            }

            System.out.println(root.data);
            traverseTreePreoder(root.left);
            traverseTreePreoder(root.right);
        }

        public void traverseTreeInorder(Node root){
            if(root == null){
                return;
            }
            traverseTreeInorder(root.left);
            System.out.println(root.data);
            traverseTreeInorder(root.right);
        }

        public void traverseTreePostorder(Node root){
            if(root == null){
                return;
            }
            traverseTreePostorder(root.left);
            traverseTreePostorder(root.right);
            System.out.print(root.data);
        }

        public void traverseTreeLevelOrder(Node root){
            /* BFS traversal */
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            queue.add(null);
            while(!queue.isEmpty()){
                Node currentNode = queue.remove();
                if(currentNode == null){
                    System.out.println();
                    if(!queue.isEmpty()){
                        queue.add(null);
                    }
                } else{
                    System.out.print(currentNode.data);
                    if(currentNode.left != null) {
                        queue.add(currentNode.left);
                    }
                    if(currentNode.right != null) {
                        queue.add(currentNode.right);
                    }
                }
            }

        }

        public int sumOfNodesAtKthLevel(Node root, int k){
            Queue<Node> q = new LinkedList();
            int level = 1;
            if(root == null){
                return 0;
            }
            q.add(root);
            q.add(null);
            int sum = 0;
            while(!q.isEmpty()){
                Node current = q.remove();
                if(current == null){
                    level++;
                    if(!q.isEmpty()){
                        q.add(null);
                    }
                } else {
                    if(k == level){
                        sum += current.data;
                    }

                    if(current.left != null){
                        q.add(current.left);
                    }
                    if(current.right != null){
                        q.add(current.right);
                    }
                }


            }
            return sum;
        }

        public int countNodes(Node root){
            if(root == null){
                return 0;
            }
            int leftcount = countNodes(root.left);
            int rightcount = countNodes(root.right);
            return 1 + leftcount + rightcount;
        }

        public int sumOfNodes(Node root){
            if(root == null){
                return 0;
            }
            int leftSum = sumOfNodes(root.left);
            int rightSum = sumOfNodes(root.right);
            return root.data + leftSum + rightSum;
        }

        public int heightOfTree(Node root){
            if(root == null){
                return 0;
            }
            int leftHeight = heightOfTree(root.left);
            int rightHeight = heightOfTree(root.right);
            return 1 + Math.max(leftHeight, rightHeight);
        }

        public int diameterOfTree(Node root){
            /* O(N^2) time */
            if(root == null){
                return 0;
            }

            int leftDiameter = diameterOfTree(root.left);
            int rightDiameter = diameterOfTree(root.right);
            int heightDiameter = heightOfTree(root.left) + heightOfTree(root.right) + 1;
            return Math.max(Math.max(leftDiameter, rightDiameter), heightDiameter);
        }

        static class TreeInfo{
            int ht;
            int diam;
            TreeInfo(int ht, int diam){
                this.ht = ht;
                this.diam = diam;
            }
        }

        public TreeInfo diameter2(Node root){
            /* O(N) time */
            if (root == null){
                return new TreeInfo(0, 0);
            }

            TreeInfo left = diameter2(root.left);
            TreeInfo right = diameter2(root.right);

            int myHeight = Math.max(left.ht, right.ht) + 1;

            int diamLeft = left.diam;
            int diamRight = right.diam;
            int diamWithRoot = left.ht + right.ht + 1;

            int myDiam = Math.max(Math.max(diamLeft, diamRight), diamWithRoot);

            TreeInfo myInfo = new TreeInfo(myHeight, myDiam);

            return myInfo;
        }
    }

    public static void main(String[] args){
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTreePreorder(nodes, "init");
        System.out.println("Root data: " + root.data);
//        tree.traverseTreePreoder(root);
//        tree.traverseTreeInorder(root);
//        tree.traverseTreePostorder(root);
        tree.traverseTreeLevelOrder(root);
        System.out.println(tree.countNodes(root)) ;
        System.out.println(tree.sumOfNodes(root)) ;
        System.out.println(tree.heightOfTree(root)) ;
        System.out.println(tree.diameterOfTree(root)) ;
        BinaryTree.TreeInfo treeInfo = tree.diameter2(root);
        System.out.println(treeInfo.diam);
        System.out.println(tree.sumOfNodesAtKthLevel(root, 3));
    }
}
