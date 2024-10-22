package com.techrevolution.tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class TwinTrees {
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
        BinaryTree tree2 = new BinaryTree();
        tree2.addToTree(6);
        tree2.addToTree(5);
        tree2.addToTree(7);
        tree2.addToTree(3);
        tree2.addToTree(2);
        tree2.addToTree(4);
        tree2.addToTree(10);
        tree2.addToTree(8);
        tree2.addToTree(9);
        Node rootNode2 = tree2.getRootNode();
        System.out.println(isTwinTree(rootNode1, rootNode2));
        System.out.println(isTwinTreeRecursive(rootNode1, rootNode2));
    }

    public static boolean isTwinTreeRecursive(Node node1, Node node2) {
        if (node1 != null && node2 != null) {
            isTwinTreeRecursive(node1.getLeft(), node2.getLeft());
            isTwinTreeRecursive(node1.getRight(), node2.getRight());
        }
        if (node1 == null && node2 != null) {
            return false;
        }
        if (node1 != null && node2 == null) {
            return false;
        }
        var v1 = node1 != null ? node1.getValue() : -1;
        var v2 = node2 != null ? node2.getValue() : -1;
        return v1 == v2;

    }

    public static boolean isTwinTree(Node node1, Node node2) {
        Queue<Node> queue1 = new ArrayDeque<>();
        queue1.add(node1);
        Queue<Node> queue2 = new ArrayDeque<>();
        queue2.add(node2);
        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            Node cn1 = queue1.poll();
            Node cn2 = queue2.poll();
            if (cn1 != null && cn2 == null) {
                return false;
            }
            if (cn2 != null && cn1 == null) {
                return false;
            }
            if (cn1.getValue() != cn2.getValue()) {
                return false;
            }
            addNodeToQueue(cn1.getLeft(), queue1);
            addNodeToQueue(cn1.getRight(), queue1);
            addNodeToQueue(cn2.getLeft(), queue2);
            addNodeToQueue(cn2.getRight(), queue2);
        }
        return true;
    }

    private static void addNodeToQueue(Node node, Queue<Node> nodeQueue) {
        if (node != null) {
            nodeQueue.add(node);
        }
    }
}
