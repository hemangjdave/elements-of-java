package com.techrevolution.linkedlist;

public class SortMergeLinkedList {
    public static void main(String[] args) {

        Node node1 = new Node(1, new Node(3, new Node(5, new Node(8, new Node(11, null)))));
        printNode(node1);
        Node node2 = new Node(2, new Node(4, new Node(9, new Node(12, null))));
        printNode(node2);
        Node merged = mergeTwoLinkedList(node1, node2);
        printNode(merged);
    }

    public static Node mergeTwoLinkedList(Node node1, Node node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        Node rootNode;
        if (node1.getValue() < node2.getValue()) {
            rootNode = new Node(node1.getValue(), null);
            node1 = node1.getNextNode();
        } else {
            rootNode = new Node(node2.getValue(), null);
            node2 = node2.getNextNode();
        }
        Node tempNode = rootNode;
        while (node1 != null && node2 != null) {
            if (node1.getValue() < node2.getValue()) {
                tempNode.setNextNode(node1);
                node1 = node1.getNextNode();
            } else {
                tempNode.setNextNode(node2);
                node2 = node2.getNextNode();
            }
            tempNode = tempNode.getNextNode();
        }
        if (node1 != null) {
            tempNode.setNextNode(node1);
        }
        if (node2 != null) {
            tempNode.setNextNode(node2);
        }

        return rootNode;
    }

    public static void printNode(Node node) {
        Node tempNode = node;
        while (tempNode != null) {
            System.out.print(tempNode.getValue() + " ");
            tempNode = tempNode.getNextNode();
        }
        System.out.println();
    }
}
