package com.techrevolution.dynamic;

public class MinPathTopToBottom {
    public static void main(String[] args) {
        var grid = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum(grid));
    }

    //1,3,1
    //1,5,1
    //4,2,1

    public static int minPathSum(int[][] grid) {
        var rowLength = grid.length;
        var colLength = grid[0].length;
        for (var i = 0 ; i < grid.length ; i++){
            for (var j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) continue;
                var leftPath = Integer.MAX_VALUE;
                var upPath = Integer.MAX_VALUE;
                if (i != 0) {
                    upPath = grid[i][j] + grid[i-1][j];
                }
                if (j!= 0){
                    leftPath = grid[i][j] + grid[i][j-1];
                }
                grid[i][j] = Math.min(leftPath, upPath);
            }
        }
        return grid[rowLength - 1][colLength - 1];
    }
}