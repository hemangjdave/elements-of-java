package com.techrevolution.tree;

public class InvertBinaryTreeRecursive {
    public static void main(String[] args) {
        var startTime = System.nanoTime();
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
        var endTime = System.nanoTime();
        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");
        //414,4958 , 3,587,167 , 4,332,000
        //4,373,250 , 3,381,167 , 4,821,750
    }

    public static Node invertTree(Node rootNode) {
        if (rootNode == null) return null;
        Node tempLeft = rootNode.getLeft();
        rootNode.setLeft(rootNode.getRight());
        rootNode.setRight(tempLeft);
        invertTree(rootNode.getLeft());
        invertTree(rootNode.getRight());
        return rootNode;
    }
}
