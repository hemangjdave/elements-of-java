package com.techrevolution.linkedlist;

import lombok.Getter;

@Getter
public class CustomLinkedList {

    private Node rootNode;

    public static void main(String[] args) {
        CustomLinkedList list = new CustomLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        System.out.println(list);

    }

    public void add(int value) {
        if (rootNode == null) {
            rootNode = new Node(value, null);
            return;
        }
        Node traverseNode = rootNode;
        while (traverseNode.getNextNode() != null) {
            traverseNode = traverseNode.getNextNode();
        }
        traverseNode.setNextNode(new Node(value, null));
    }

    @Override
    public String toString() {
        Node traverseNode = rootNode;
        StringBuilder builder = new StringBuilder();
        while (traverseNode != null) {
            builder.append(traverseNode.getValue());
            traverseNode = traverseNode.getNextNode();
        }
        return builder.toString();
    }
}
