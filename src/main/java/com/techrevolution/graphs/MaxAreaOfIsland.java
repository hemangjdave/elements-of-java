package com.techrevolution.graphs;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class MaxAreaOfIsland {
    public static void main(String[] args) {
        int[][] image = {{0, 1, 0, 0, 0}, {0, 0, 0, 0, 0}, {1, 1, 0, 1, 0}, {1, 1, 0, 1, 1}, {1, 1, 0, 1, 1}};
        System.out.println(maxAreaOfIsland(image));

    }

    private static int maxAreaOfIsland(int[][] bluePrint) {
        Set<Coordinates> visitedCoordinates = new HashSet<>();
        Deque<Coordinates> deque = new LinkedList<>();
        var maxArea = 0;
        int[][] directions = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
        for (var i = 0; i < bluePrint.length; i++) {
            for (var j = 0; j < bluePrint[0].length; j++) {
                Coordinates coordinates = new Coordinates(i, j);
                if (!visitedCoordinates.contains(coordinates)) {
                    visitedCoordinates.add(coordinates);
                    if (bluePrint[i][j] == 1) {
                        var currentArea = 1;
                        deque.add(coordinates);
                        while (!deque.isEmpty()) {
                            Coordinates polled = deque.pollFirst();
                            int row = polled.row();
                            int column = polled.column();
                            for (var idx = 0; idx < 4; idx++) {
                                var newRow = row + directions[idx][0];
                                var newColumn = column + directions[idx][1];
                                if (newRow > -1 && newRow < bluePrint.length && newColumn > -1 && newColumn < bluePrint[0].length) {
                                    Coordinates currentCoordinates = new Coordinates(newRow, newColumn);
                                    if (!visitedCoordinates.contains(currentCoordinates)) {
                                        visitedCoordinates.add(currentCoordinates);
                                        if (bluePrint[newRow][newColumn] == 1) {
                                            deque.add(currentCoordinates);
                                            currentArea++;
                                        }
                                    }
                                }
                            }
                        }
                        if (currentArea > maxArea) {
                            maxArea = currentArea;
                        }
                    }
                }
            }
        }
        return maxArea;
    }
}

record Coordinates(int row, int column) {

}