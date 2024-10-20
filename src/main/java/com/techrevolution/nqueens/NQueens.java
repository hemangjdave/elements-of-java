package com.techrevolution.nqueens;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class NQueens {

    public static void main(String[] args) {
        List<Coordinates> coordinates = generateQueensCoordinates(8);
        coordinates.forEach(coordinates1 -> System.out.printf("x:--%s , y:--%s%n", coordinates1.row(), coordinates1.column()));
    }

    record Coordinates(int row, int column) {
    }

    public static List<Coordinates> generateQueensCoordinates(int numberOfQueens) {
        List<Coordinates> coordinates = new ArrayList<>();
        Queue<Coordinates> queue = new ArrayDeque<>();
        int[][] chessBoard = new int[numberOfQueens][numberOfQueens];
        var row = 0;
        var column = 0;
        while (queue.size() != numberOfQueens) {
            HashMap<int[][], Boolean> hashMap = new HashMap<>();
            if (isValidCoordinates(row, column, chessBoard, hashMap)) {
                chessBoard[row][column] = 1;
                Coordinates e = new Coordinates(row, column);
                queue.add(e);
                coordinates.add(e);
                column++;
                row = 0;
            } else {
                row++;
                if (row >= numberOfQueens) {
                    Coordinates polled = queue.poll();
                    assert polled != null;
                    row = polled.row();
                    column = polled.column();
                    row++;
                }
            }
            hashMap.clear();
        }

        return coordinates;
    }

    private static boolean isValidCoordinates(int row, int column, int[][] chessBoard, Map<int[][], Boolean> map) {
        if (row < 0 || row >= chessBoard.length || column < 0) {
            map.put(new int[row][column], true);
            return true;
        }
        if (chessBoard[row][column] == 1) {
            return false;
        }
        boolean isValidCoordinates = isValidCoordinates(row - 1, column - 1, chessBoard, map);
        if (isValidCoordinates) {
            isValidCoordinates = isValidCoordinates(row, column - 1, chessBoard, map);
            if (isValidCoordinates) {
                isValidCoordinates = isValidCoordinates(row + 1, column - 1, chessBoard, map);
            }
        }
        return isValidCoordinates;
    }
}
