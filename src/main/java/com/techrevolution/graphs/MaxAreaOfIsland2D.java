package com.techrevolution.graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.IntBinaryOperator;

public class MaxAreaOfIsland2D {

    private static final int[][] directions = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    private static final IntBinaryOperator INT_BINARY_OPERATOR = (Integer::sum);


    private static final BiIntPredicate BI_INT_PREDICATE = (row, col, bluePrint) -> row >= 0 && col >= 0
            && row < bluePrint.length && col < bluePrint[row].length
            && bluePrint[row][col] == 1;


    public static void main(String[] args) {
        var startTime = System.nanoTime();
        int[][] image = {{0, 1, 0, 0, 0}, {0, 0, 0, 0, 0}, {1, 1, 0, 1, 0}, {1, 1, 0, 1, 1}, {1, 1, 0, 1, 1}};
        System.out.println(maxAreaOfIsland(image));
        int[][] image2 = {{0, 1, 0, 0, 0}, {0, 1, 1, 0, 0}, {1, 1, 0, 1, 0}, {1, 1, 0, 1, 1}, {1, 1, 0, 1, 1}};
        System.out.println(maxAreaOfIsland(image2));
        int[][] image3 = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}};
        System.out.println(maxAreaOfIsland(image3));
        var endTime = System.nanoTime();
        System.out.println("Execution time in milliseconds: " + (endTime - startTime));
        //118,875 , 394208 , 178834
        //4,271,792 , 3,170,250 , 3,717,042
    }

    //0,1,0,0,0.
    //0,1,1,0,0.
    //1,1,0,1,0.
    //1,1,0,1,1.
    //1,1,0,1,1.
    public static int maxAreaOfIsland(int[][] bluePrint) {
        var queue = new LinkedList<int[]>();
        var maxArea = 0;
        for (var i = 0; i < bluePrint.length; i++) {
            for (var j = 0; j < bluePrint[i].length; j++) {
                if (bluePrint[i][j] == 1) {
                    var currentArea = dfs(bluePrint, i, j, 0);
                    maxArea = Math.max(maxArea, currentArea);
                }
            }
        }
        return maxArea;
//        return Stream.iterate(0, integer -> integer < bluePrint.length, integer -> integer + 1)
//                .flatMap(row -> Stream.iterate(0, column -> column < bluePrint[0].length, column -> column + 1)
//                        .filter(column -> bluePrint[row][column] == 1)
//                        .map(column -> dfs(bluePrint, row, column, 0))
//                ).max(Comparator.naturalOrder())
//                .orElse(-1);

    }


    //3,231,625 , 4,039,709 , 3,520,042
    private static int dfs(int[][] bluePrint, int row, int col, int currentArea) {
        for (int[] direction : directions) {
            var nextRow = INT_BINARY_OPERATOR.applyAsInt(row, direction[0]);
            var nextCol = INT_BINARY_OPERATOR.applyAsInt(col, direction[1]);
            if (BI_INT_PREDICATE.test(nextRow, nextCol, bluePrint)) {
                bluePrint[nextRow][nextCol] = -1;
                currentArea++;
                currentArea = dfs(bluePrint, nextRow, nextCol, currentArea);
            }
        }
        return currentArea;
    }

    private static int bfs(Queue<int[]> queue, int[][] bluePrint, int row, int col) {
        queue.add(new int[]{row, col});
        var currentArea = 0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            var currentRow = poll[0];
            var currentCol = poll[1];
            for (int[] direction : directions) {
                var nextRow = currentRow + direction[0];
                var nextCol = currentCol + direction[1];
                if (nextRow >= 0 && nextRow < bluePrint.length
                        && nextCol >= 0 && nextCol < bluePrint[nextRow].length
                        && bluePrint[nextRow][nextCol] == 1
                ) {
                    queue.add(new int[]{nextRow, nextCol});
                    currentArea++;
                    bluePrint[nextRow][nextCol] = -1;
                }
            }
        }
        return currentArea;
    }
}

@FunctionalInterface
interface BiIntPredicate {
    boolean test(int a, int b, int[][] bluePrint);
}