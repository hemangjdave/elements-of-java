package com.techrevolution.graphs;

public class FloodFillRecursive {
    public static void main(String[] args) {
        var startTime = System.nanoTime();
        var floodFill = new FloodFillRecursive();
        int[][] image = {{0, 1, 0, 0, 0}, {0, 0, 0, 0, 0}, {1, 1, 0, 1, 0}, {1, 0, 0, 1, 1}, {1, 0, 0, 1, 1}};
        System.out.println("---------------Before flood filling----------------");
        for (int[] rows : image) {
            for (int column : rows) {
                System.out.print(column + " ");
            }
            System.out.println();
        }
        floodFill.floodFill(image, 2, 0, 2);
        System.out.println("---------------After flood filling-----------------");
        for (int[] rows : image) {
            for (int column : rows) {
                System.out.print(column + " ");
            }
            System.out.println();
        }
        var endTime = System.nanoTime();
        System.out.println(endTime - startTime);//2,986,292
    }

    public void floodFill(int[][] image, int sr, int sc, int color) {
        bfs(image, sr, sc, color, image[sr][sc]);
    }

    private void bfs(int[][] image, int sr, int sc, int destinationColor, int sourceColor) {
        if (isValidCoordinates(image, sr, sc) && image[sr][sc] == sourceColor) {
            image[sr][sc] = destinationColor;
            bfs(image, sr, sc + 1, destinationColor, sourceColor);
            bfs(image, sr - 1, sc, destinationColor, sourceColor);
            bfs(image, sr, sc - 1, destinationColor, sourceColor);
            bfs(image, sr + 1, sc, destinationColor, sourceColor);
        }
    }

    public boolean isValidCoordinates(int[][] image, int sr, int sc) {
        return sr > -1 && sr < image.length && sc > -1 && sc < image[0].length;
    }
}
