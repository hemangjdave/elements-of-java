package com.techrevolution.nqueens;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class NQueensWithMapThreads {
    public static void main(String[] args) {
        var startTime = System.nanoTime();
        List<Position> coordinates = generateCoordinates(35);
        //35 -> 15,484,813,792
        //35 -> 271,839,664,792
        //40 -> Not possible
        coordinates.forEach(coordinates1 -> System.out.printf("x:--%s , y:--%s%n", coordinates1.row(), coordinates1.column()));
        var endTime = System.nanoTime();
        System.out.println(endTime - startTime); // 3385125 , 4030500 , 3259375
    }

    private static List<Position> generateCoordinates(int numberOfQueens) {
        var deque = new ArrayDeque<Position>();
        var chessBoard = new int[numberOfQueens][numberOfQueens];
        var row = 0;
        var column = 0;
        while (deque.size() != numberOfQueens) {
            if (isValidCoordinates(row, column, chessBoard)) {
                Position position = new Position(row, column);
                deque.add(position);
                markAndFillQueenPosition(row, column, chessBoard);
                column++;
                row = 0;
            } else {
                row++;
                if (row > numberOfQueens) {
                    Position polled = deque.pollLast();
                    assert polled != null;
                    row = polled.row();
                    column = polled.column();
                    resetQueenPosition(row, column, chessBoard);
                    row++;
                }
            }
        }

        return new ArrayList<>(deque);

    }

    private static void resetQueenPosition(int row, int column, int[][] chessBoard) {
        ToIntFromInt toIntFromInt = (value -> value - 1);
        Thread thread1 = new Thread(() -> updateTopRight(row, column, chessBoard, toIntFromInt));
        thread1.start();
        Thread thread2 = new Thread(() -> updateRight(row, column, chessBoard, toIntFromInt));
        thread2.start();
        Thread thread3 = new Thread(() -> updateBottomRight(row, column, chessBoard, toIntFromInt));
        thread3.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {

        }
    }

    private static void markAndFillQueenPosition(int row, int column, int[][] chessBoard) {
        ToIntFromInt toIntFromInt = (value -> value + 1);
        Thread thread1 = new Thread(() -> updateTopRight(row, column, chessBoard, toIntFromInt));
        thread1.start();
        Thread thread2 = new Thread(() -> updateRight(row, column, chessBoard, toIntFromInt));
        thread2.start();
        Thread thread3 = new Thread(() -> updateBottomRight(row, column, chessBoard, toIntFromInt));
        thread3.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException interruptedException) {

        }
    }

    private static void updateTopRight(int row, int column, int[][] chessBoard, ToIntFromInt toIntFromInt) {
        while (row > -1 && column < chessBoard[0].length) {
            chessBoard[row][column] = toIntFromInt.apply(chessBoard[row][column]);
            row--;
            column++;
        }
    }

    private static void updateRight(int row, int column, int[][] chessBoard, ToIntFromInt toIntFromInt) {
        while (column < chessBoard[0].length) {
            chessBoard[row][column] = toIntFromInt.apply(chessBoard[row][column]);
            column++;
        }
    }

    private static void updateBottomRight(int row, int column, int[][] chessBoard, ToIntFromInt toIntFromInt) {
        while (row < chessBoard.length && column < chessBoard[0].length) {
            chessBoard[row][column] = toIntFromInt.apply(chessBoard[row][column]);
            row++;
            column++;
        }
    }

    private static boolean isValidCoordinates(int row, int column, int[][] board) {
        return row < board.length && board[row][column] == 0;
    }
}

interface ToIntFromInt {
    int apply(int value);
}


record Position(int row, int column) {
}
