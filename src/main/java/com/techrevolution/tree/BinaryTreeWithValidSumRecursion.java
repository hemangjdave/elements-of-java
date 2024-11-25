package com.techrevolution.tree;

public class BinaryTreeWithValidSumRecursion {
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
        System.out.println("----------------SECOND SCENARIO WITH RECURSION-----------------------");
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
        //278541 , 527042 , 268708 , 344541
    }

    public static boolean hasValidSum(Node root, int desiredSum) {
        if (root == null) {
            return false;
        }
        return dfs(root, desiredSum, root.getValue());
    }

    private static boolean dfs(Node root, int desiredSum, int currentSum) {
        if (root == null) return false;
        if (desiredSum == currentSum && isLeaf(root)) return true;
        boolean isValid = false;
        if (root.getLeft() != null) {
            Node tempNode = root.getLeft();
            var sum = currentSum + tempNode.getValue();
            tempNode.setValue(sum);
            isValid = dfs(root.getLeft(), desiredSum, sum);
        }
        if (root.getRight() != null) {
            Node tempNode = root.getRight();
            var sum = currentSum + tempNode.getValue();
            tempNode.setValue(sum);
            isValid = dfs(root.getRight(), desiredSum, sum);
        }
        return isValid;
    }

    private static boolean isLeaf(Node node) {
        return node.getLeft() == null && node.getRight() == null;
    }
}
