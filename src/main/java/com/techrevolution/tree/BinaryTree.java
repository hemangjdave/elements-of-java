package com.techrevolution.tree;

public class BinaryTree {

    private Node rootNode;

    public void addToTree(int value) {
        if (rootNode == null) {
            rootNode = new Node(value, null, null);
            return;
        }
        Node traverseNode = rootNode;
        while (true) {
            if (value < traverseNode.getValue()) {
                if (traverseNode.getLeft() == null) {
                    traverseNode.setLeft(new Node(value, null, null));
                    break;
                } else {
                    traverseNode = traverseNode.getLeft();
                }
            } else {
                if (traverseNode.getRight() == null) {
                    traverseNode.setRight(new Node(value, null, null));
                    break;
                } else {
                    traverseNode = traverseNode.getRight();
                }
            }
        }
    }

    public void printBinaryTree(Node rootNode) {
        if (rootNode != null) {
            printBinaryTree(rootNode.getLeft());
            System.out.print(rootNode.getValue() + " ");
            printBinaryTree(rootNode.getRight());
        }
    }

    public Node getRootNode() {
        return rootNode;
    }
}
