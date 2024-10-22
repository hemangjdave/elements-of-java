package com.techrevolution.tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class InvertBinaryTree {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.addToTree(6);
        tree.addToTree(5);
        tree.addToTree(7);
        tree.addToTree(3);
        tree.addToTree(2);
        tree.addToTree(4);
        tree.addToTree(10);
        tree.addToTree(8);
        tree.addToTree(9);
        Node rootNode1 = tree.getRootNode();
        tree.printBinaryTree(rootNode1);
        System.out.println();
        Node invertTree = invertTree(rootNode1);
        tree.printBinaryTree(invertTree);
        System.out.println();
        Node normalTree = invertTree(invertTree);
        tree.printBinaryTree(normalTree);
    }

    public static Node invertTree(Node rootNode) {
        if (rootNode == null) {
            return null;
        }
        Queue<Node> nodes = new ArrayDeque<>();
        nodes.add(rootNode);
        while (!nodes.isEmpty()) {
            Node poll = nodes.poll();
            Node left = poll.getLeft();
            Node right = poll.getRight();
            if (left != null) {
                nodes.add(left);
            }
            if (right != null) {
                nodes.add(right);
            }
            swapNodes(poll, left, right);
        }
        return rootNode;
    }

    private static void swapNodes(Node rootNode, Node left, Node right) {
        rootNode.setLeft(right);
        rootNode.setRight(left);
    }
}
