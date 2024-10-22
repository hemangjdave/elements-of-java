package com.techrevolution.nqueens;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.IntUnaryOperator;

public class NQueensWith2DArray {

    private static final IntUnaryOperator MINUS_UNARY_OPERATOR = (value -> value - 1);
    private static final IntUnaryOperator PLUS_UNARY_OPERATOR = (value -> value + 1);

    public static void main(String[] args) {
        var startTime = System.nanoTime();
        List<Position> coordinates = generateCoordinates(30);
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
                fillOrResetQueenPosition(row, column, chessBoard, PLUS_UNARY_OPERATOR);
                column++;
                row = 0;
            } else {
                row++;
                if (row > numberOfQueens) {
                    Position polled = deque.pollLast();
                    assert polled != null;
                    row = polled.row();
                    column = polled.column();
                    fillOrResetQueenPosition(row, column, chessBoard, MINUS_UNARY_OPERATOR);
                    row++;
                }
            }
        }

        return new ArrayList<>(deque);

    }

    private static void fillOrResetQueenPosition(int row, int column, int[][] chessBoard, IntUnaryOperator unaryOperator) {
        genericUpdate(
                row, column, chessBoard, unaryOperator,
                ((v1, v2) -> v1 > -1 && v2 < chessBoard[0].length), MINUS_UNARY_OPERATOR
        );
        genericUpdate(
                row, column, chessBoard, unaryOperator,
                ((v1, v2) -> v2 < chessBoard[0].length), null
        );
        genericUpdate(
                row, column, chessBoard, unaryOperator,
                ((v1, v2) -> v1 < chessBoard.length && v2 < chessBoard[0].length), PLUS_UNARY_OPERATOR
        );
    }

    private static void genericUpdate(int row, int column, int[][] chessBoard, IntUnaryOperator unaryOperator,
                                      BiIntPredicate intPredicate, IntUnaryOperator rowOperation) {
        while (intPredicate.test(row, column)) {
            chessBoard[row][column] = unaryOperator.applyAsInt(chessBoard[row][column]);
            if (Objects.nonNull(rowOperation))
                row = rowOperation.applyAsInt(row);
            column = NQueensWith2DArray.PLUS_UNARY_OPERATOR.applyAsInt(column);
        }
    }

    private static boolean isValidCoordinates(int row, int column, int[][] board) {
        return row < board.length && board[row][column] == 0;
    }
}

@FunctionalInterface
interface BiIntPredicate {
    boolean test(int v1, int v2);
}