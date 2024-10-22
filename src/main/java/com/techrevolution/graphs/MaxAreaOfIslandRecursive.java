package com.techrevolution.graphs;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class MaxAreaOfIslandRecursive {

    private static final int[][] directions = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};


    public static void main(String[] args) {
        int[][] image = {{0, 1, 0, 0, 0}, {0, 0, 0, 0, 0}, {1, 1, 0, 1, 0}, {1, 1, 0, 1, 1}, {1, 1, 0, 1, 1}};
        System.out.println(maxAreaOfIsland(image));
    }

    public static int maxAreaOfIsland(int[][] bluePrint) {
        var visitedIsland = new HashSet<IslandPos>();
        return Stream
                .iterate(0, integer -> integer < bluePrint.length, integer -> integer + 1)
                .flatMap(
                        integer -> Stream.iterate(
                                0, integer1 -> integer1 < bluePrint[0].length, integer1 -> integer1 + 1
                        ).filter(
                                integer1 -> bluePrint[integer][integer1] == 1
                                        && !visitedIsland.contains(new IslandPos(integer, integer1))
                        ).map(value -> floodFill(bluePrint, integer, value, visitedIsland))
                ).max(Comparator.naturalOrder())
                .orElse(-1);
    }

    private static int floodFill(int[][] image, int row, int column, Set<IslandPos> visitedIsland) {
        var islandQueue = new ArrayDeque<IslandPos>();
        IslandPos islandPos = new IslandPos(row, column);
        islandQueue.add(islandPos);
        visitedIsland.add(islandPos);
        var currentIslandArea = 1;
        while (!islandQueue.isEmpty()) {
            var island = islandQueue.poll();
            var islandRow = island.row();
            var islandColumn = island.column();
            for (int[] direction : directions) {
                var currentRow = islandRow + direction[0];
                var currentColumn = islandColumn + direction[1];
                if (isValidCoordinates(image, currentRow, currentColumn)) {
                    IslandPos currentIsland = new IslandPos(currentRow, currentColumn);
                    if (!visitedIsland.contains(currentIsland)) {
                        visitedIsland.add(currentIsland);
                        if (image[currentRow][currentColumn] == 1) {
                            islandQueue.add(currentIsland);
                            currentIslandArea++;
                        }
                    }
                }
            }
        }
        return currentIslandArea;
    }

    private static boolean isValidCoordinates(int[][] image, int row, int column) {
        return row > -1 && row < image.length && column > -1 && column < image[row].length;
    }
}

record IslandPos(int row, int column) {

}
