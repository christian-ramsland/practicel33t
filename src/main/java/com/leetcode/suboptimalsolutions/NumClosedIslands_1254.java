package com.leetcode.suboptimalsolutions;

import java.util.Arrays;

/**
 * this seems very similar to the flood fill problem. could basically replicate the flood fill algo
 * dfs method converting 1s -> 0s for any "land" connected to the edges.
 *
 *
 * ok so a tip I found for keeping sanity in the future courtesy of stack overflow
 * https://stackoverflow.com/questions/2203525/are-the-x-y-and-row-col-attributes-of-a-two-dimensional-array-backwards
 * int x = array[0].length;
 * int y = array.length;
 *
 * x always does the zero, y just the regular length of the multidimensional array, and they're called with
 *
 * array[y][x]
 *
 * which is flipped from math intuition but w/e, I can remember 3 things if it means not making any mistakes
 * also initializing a complement boolean array, don't put minus ones here ffs, wouldn't do that for normal array:
 * boolean[][] edgeIncremented = new boolean[grid.length-1][grid[0].length-1];
 *
 * accepted but sort of terrible
 */
public class NumClosedIslands_1254 {
    public static void main(String[] args) {
        int[][] grid = {{1,1,1,1,1,1,1,0},{1,0,0,0,0,1,1,0},{1,0,1,0,1,1,1,0},{1,0,0,0,0,1,0,1},{1,1,1,1,1,1,1,0}};
        System.out.println(closedIsland(grid));
    }

    public static int closedIsland(int[][] grid) {
//        print2D(grid);

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int x = 0; x < grid[0].length; x++) {
            dfsLowerEdges(grid, x, 0, visited);
            dfsLowerEdges(grid, x, grid.length-1, visited);
        }

//        System.out.println("after transformation");
//        print2D(grid);

        for (int i = 0; i < grid.length; i++) {
             dfsLowerEdges(grid, 0, i, visited);
             dfsLowerEdges(grid, grid[0].length - 1, i, visited);
        }

//        System.out.println("after transformation2");
//        print2D(grid);

        int islandCounter = 0;
        for (int x = 0; x < grid[0].length; x++) {
            for (int y = 0; y < grid.length; y++) {
                islandCounter = islandCounter + dfsCountIslands(grid, x, y, visited);
            }
        }

        return islandCounter;
    }

    public static int dfsCountIslands(int[][] grid, int x, int y, boolean[][] visited) {
        int islandFound = 0;
        if (grid[y][x] == 0 && visited[y][x] == false) {
            System.out.println("x=" + x + " y=" + y);
            islandFound++;
            visited[y][x] = true;
            if (x + 1 < grid[0].length) {
                islandFound = islandFound + dfsCountIslands(grid, x + 1, y, visited);
            }
            if (x - 1 >= 0 ) {
                islandFound = islandFound + dfsCountIslands(grid, x - 1, y, visited);
            }
            if (y + 1 < grid.length) {
                islandFound = islandFound + dfsCountIslands(grid, x, y + 1, visited);
            }
            if (y - 1 >= 0) {
                islandFound = islandFound + dfsCountIslands(grid, x, y - 1, visited);
            }
        }
        if (islandFound > 0) {
            return 1;
        }
        return 0;
    }

    public static void dfsLowerEdges(int[][] grid, int x, int y, boolean[][] visited) {
//        System.out.println("x=" + x + " y=" + y);
        if (grid[y][x] == 0) {
            grid[y][x] = 1;
            visited[y][x] = true;
            if (x + 1 < grid[0].length && visited[y][x+1] == false) {
                dfsLowerEdges(grid, x + 1, y, visited);
            }
            if (x - 1 >= 0 && visited[y][x-1] == false) {
                dfsLowerEdges(grid, x - 1, y, visited);
            }
            if (y + 1 < grid.length && visited[y + 1][x] == false) {
                dfsLowerEdges(grid, x, y + 1, visited);
            }
            if (y - 1 >= 0 && visited[y - 1][x] == false) {
                dfsLowerEdges(grid, x, y - 1, visited);
            }
        } else {
            return;
        }
    }

    /**
     * for some reason the edges aren't getting counted out
     * @param mat
     */
    public static void print2D(int mat[][])
    {
        // Loop through all rows
        for (int[] row : mat)

            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }

    public static void print2Dbool(boolean mat[][])
    {
        // Loop through all rows
        for (boolean[] row : mat)

            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }




}


