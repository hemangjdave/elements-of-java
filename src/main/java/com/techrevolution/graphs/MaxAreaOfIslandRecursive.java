package com.techrevolution.graphs;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class MaxAreaOfIslandRecursive {

    private static final int[][] directions = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};


    public static void main(String[] args) {
//        int[][] image = {{0, 1, 0, 0, 0}, {0, 0, 0, 0, 0}, {1, 1, 0, 1, 0}, {1, 1, 0, 1, 1}, {1, 1, 0, 1, 1}};
        int[][] image = {{0, 1, 0, 0, 0}, {0, 0, 0, 0, 0}, {1, 1, 0, 1, 0}, {1, 1, 0, 1, 1}, {1, 1, 0, 1, 1}};

        System.out.println(maxAreaOfIsland(image));
    }

    public static int maxAreaOfIsland(int[][] bluePrint) {
        Set<IslandPos> visitedPos = new HashSet<>();
        var maxArea = 0;
        for (var i = 0; i < bluePrint.length; i++) {
            for (var j = 0; j < bluePrint[0].length; j++) {
                if (bluePrint[i][j] == 1) {
                    var currentArea = getAreaOfIsland(bluePrint, i, j, visitedPos);
                    if (currentArea > maxArea) maxArea = currentArea;
                }
            }
        }
        return maxArea;
    }

    private static int getAreaOfIsland(int[][] bluePrint, int row, int column, Set<IslandPos> visitedPos) {
        IslandPos islandPos = new IslandPos(row, column);
        if (!visitedPos.contains(islandPos)) {
            var defaultArea = 1;
            Queue<IslandPos> queue = new ArrayDeque<>();
            queue.add(islandPos);
            while (!queue.isEmpty()) {
                IslandPos polled = queue.poll();
                var cRow = polled.row();
                var cCol = polled.column();
                for (var i = 0; i < directions.length; i++) {
                    var currentRow = cRow + directions[i][0];
                    var currentCol = cCol + directions[i][1];
                    if (isValidCoordinates(bluePrint, currentRow, currentCol)) {
                        IslandPos currentCoordinates = new IslandPos(currentRow, currentCol);
                        if (isValidArea(bluePrint, currentRow, currentCol, visitedPos, currentCoordinates)) {
                            defaultArea++;
                        } else {
                            visitedPos.add(currentCoordinates);
                        }
                    }
                }
            }
            return defaultArea;
        }
        return 0;
    }

    private static boolean isValidArea(int[][] bluePrint, int row, int column, Set<IslandPos> visitedPos, IslandPos pos) {
        return !visitedPos.contains(pos) && bluePrint[row][column] == 1;
    }

    private static boolean isValidCoordinates(int[][] bluePrint, int row, int column) {
        return row > -1 && row < bluePrint.length && column > -1 && column < bluePrint[0].length;
    }
}

record IslandPos(int row, int column) {

}
