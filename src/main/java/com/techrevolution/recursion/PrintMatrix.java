package com.techrevolution.recursion;

public class PrintMatrix {
    public static void main(String[] args) {
        int[][] image = {{0, 1, 0, 0, 0}, {0, 0, 0, 0, 0}, {1, 1, 0, 1, 0}, {1, 1, 0, 1, 1}, {1, 1, 0, 1, 1}};
        printMatrix(image);
        printMatrixRecursive(image, 0, 0);
    }

    private static void printMatrix(int[][] image) {
        for (var i = 0; i < image.length; i++) {
            for (var j = 0; j < image[0].length; j++) {
                System.out.print(image[i][j] + " ");
            }
            System.out.println();
        }
    }


    private static void printMatrixRecursive(int[][] image, int row, int column) {
        if (row >= image.length || column >= image[0].length) {
            return;
        }
        System.out.print(image[row][column] + " ");
        printMatrixRecursive(image, row, column + 1);
        System.out.println();
        printMatrixRecursive(image, row + 1, 0);
    }
}
