package com.techrevolution.tree;

import java.util.Deque;
import java.util.LinkedList;

public class BinaryTreeWithValidSum {
    public static void main(String[] args) {
        var startTime = System.nanoTime();
        BinaryTree tree = new BinaryTree();
        tree.addToTree(11);
        tree.addToTree(10);
        tree.addToTree(11);
        Node rootNode = tree.getRootNode();
        System.out.println(hasValidSum(rootNode, 22)); // true
        System.out.println(hasValidSum(rootNode, 23)); // false
        System.out.println(hasValidSum(rootNode, 33)); // false
        System.out.println();
        System.out.println("----------------SECOND SCENARIO-----------------------");
        BinaryTree tree2 = new BinaryTree();
        tree2.addToTree(1);
        tree2.addToTree(2);
        tree2.addToTree(3);
        tree2.addToTree(4);
        System.out.println(hasValidSum(tree2.getRootNode(), 10));//true
        System.out.println(hasValidSum(tree2.getRootNode(), 11));//false
        System.out.println(hasValidSum(tree2.getRootNode(), 12));//false
        var endTime = System.nanoTime();
        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");
        //1040458 , 541875 , 1031792 , 526042
    }

    public static boolean hasValidSum(Node root, int desiredSum) {
        Deque<Node> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            Node node = deque.pollFirst();
            var currentSum = node.getValue();
            if (currentSum == desiredSum && isLeaf(node)) return true;
            if (desiredSum == currentSum && !isLeaf(node)) return false;
            if (node.getLeft() != null) {
                Node left = node.getLeft();
                extracted(currentSum, left, deque);
            }
            if (node.getRight() != null) {
                Node right = node.getRight();
                extracted(currentSum, right, deque);
            }
        }
        return false;
    }

    private static void extracted(int currentSum, Node left, Deque<Node> deque) {
        var sum = currentSum + left.getValue();
        left.setValue(sum);
        deque.add(left);
    }

    private static boolean isLeaf(Node node) {
        return node.getLeft() == null && node.getRight() == null;
    }

}
