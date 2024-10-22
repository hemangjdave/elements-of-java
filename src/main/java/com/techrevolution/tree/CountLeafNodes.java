package com.techrevolution.tree;

public class CountLeafNodes {
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
        tree.addToTree(17);
        Node rootNode1 = tree.getRootNode();
        System.out.println(getNumberOfLeafNodes(rootNode1));
    }

    public static int getNumberOfLeafNodes(Node rootNod) {
        if (rootNod == null) {
            return 0;
        }
        if (rootNod.getLeft() == null && rootNod.getRight() == null) {
            return 1;
        }
        int value1 = getNumberOfLeafNodes(rootNod.getLeft());
        int value2 = getNumberOfLeafNodes(rootNod.getRight());
        return value1 + value2;
    }
}
