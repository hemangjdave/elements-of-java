package com.techrevolution.nqueens;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class NQueensWithoutRecursion {
    public static void main(String[] args) {
        var startTime = System.nanoTime();
        List<Coordinates> coordinates = generateQueensCoordinates(8);
        coordinates.forEach(coordinates1 -> System.out.printf("x:--%s , y:--%s%n", coordinates1.row(), coordinates1.column()));
        var endTime = System.nanoTime();
        System.out.println(endTime - startTime);//3816750
//        coordinates = generateQueensCoordinates(10);
//        coordinates.forEach(coordinates1 -> System.out.printf("x:--%s , y:--%s%n", coordinates1.row(), coordinates1.column()));
    }

    record Coordinates(int row, int column) {
    }

    private static List<Coordinates> generateQueensCoordinates(int numberOfQueens) {
        int[][] chessBoard = new int[numberOfQueens][numberOfQueens];
        ArrayDeque<Coordinates> queue = new ArrayDeque<>();
        int row = 0;
        int column = 0;
        while (queue.size() != numberOfQueens) {
            if (isValidCoordinates(row, column, chessBoard)) {
                Coordinates coordinates = new Coordinates(row, column);
                queue.add(coordinates);
//                System.out.printf("Adding to queue with x:--%d and y=%d%n", row, column);
                markQueenPosition(column, chessBoard[row]);
                column++;
                row = 0;
            } else {
                row++;
                if (row >= numberOfQueens) {
                    Coordinates polled = queue.pollLast();
                    assert polled != null;
//                    System.out.printf("Polling from queue with coordinates x:--%d and y:-%d%n", polled.row, polled.column);
                    row = polled.row();
                    column = polled.column();
                    resetQueenPosition(column, chessBoard[row]);
                    row++;
                }
            }
        }
        return new ArrayList<>(queue);
    }

    private static void resetQueenPosition(int column, int[] chessBoard) {
        chessBoard[column] = 0;
    }

    private static void markQueenPosition(int column, int[] chessBoard) {
        chessBoard[column] = 1;
    }

    private static boolean isValidCoordinates(int row, int column, int[][] chessBoard) {
        if (row >= chessBoard.length) {
            return false;
        }
        return isUpperLeftScanValid(row, column, chessBoard)
                && isLeftScanValid(row, column, chessBoard)
                && isBottomLeftScanValid(row, column, chessBoard);
    }

    private static boolean isUpperLeftScanValid(int row, int column, int[][] chessBoard) {
        while (row > -1 && column > -1) {
            if (chessBoard[row--][column--] == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean isLeftScanValid(int row, int column, int[][] chessBoard) {
        while (column > -1) {
            if (chessBoard[row][column--] == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean isBottomLeftScanValid(int row, int column, int[][] chessBoard) {
        while (row < chessBoard.length && column > -1) {
            if (chessBoard[row++][column--] == 1) {
                return false;
            }
        }
        return true;
    }

}
