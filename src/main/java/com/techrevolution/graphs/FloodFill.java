package com.techrevolution.graphs;

import java.util.Deque;
import java.util.LinkedList;

public class FloodFill {

    public static void main(String[] args) {
        var startTime = System.nanoTime();
        var floodFill = new FloodFill();
        int[][] image = {{0, 1, 0, 0, 0}, {0, 0, 0, 0, 0}, {1, 1, 0, 1, 0}, {1, 0, 0, 1, 1}, {1, 0, 0, 1, 1}};
        System.out.println("---------------Before flood filling----------------");
        for (int[] rows : image) {
            for (int column : rows) {
                System.out.print(column + " ");
            }
            System.out.println();
        }
        floodFill.floodFill(image, 2, 3, 2);
        System.out.println("---------------After flood filling-----------------");
        for (int[] rows : image) {
            for (int column : rows) {
                System.out.print(column + " ");
            }
            System.out.println();
        }
        var endTime = System.nanoTime();
        System.out.println(endTime - startTime);//9,862,125
    }

    public void floodFill(int[][] image, int sr, int sc, int color) {
        int[][] directions = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
        Deque<int[]> deque = new LinkedList<>();
        var sourceColor = image[sr][sc];
        if (isValidCoordinates(image, sr, sc)) {
            deque.add(new int[]{sr, sc});
        }
        while (!deque.isEmpty()) {
            int[] polled = deque.pollFirst();
            var row = polled[0];
            var column = polled[1];
            if (image[row][column] == sourceColor) {
                image[row][column] = color;
            }
            for (var i = 0; i < 4; i++) {
                var newRow = row + directions[i][0];
                var newColumn = column + directions[i][1];
                if (isValidCoordinates(image, newRow, newColumn) && (image[newRow][newColumn] == sourceColor)) {
                    deque.add(new int[]{newRow, newColumn});
                }
            }
        }
    }

    public boolean isValidCoordinates(int[][] image, int sr, int sc) {
        return sr > -1 && sr < image.length && sc > -1 && sc < image[0].length;
    }

}
