package blind75;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RottingOranges {
    /*
        Input
            You are given an m x n grid where each cell can have one of three values:
            0 representing an empty cell,
            1 representing a fresh orange, or
            2 representing a rotten orange.
        Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

        Output
            Return the minimum number of minutes that must elapse until no cell has a fresh orange.
            If this is impossible, return -1.
    */
    public static int orangesRotting(int[][] grid) {
        int time = 0;
        int numRows = grid.length;
        int numCols = grid[0].length;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int freshOrangesCount = 0;
        boolean[][] visited = new boolean[numRows][numCols];
        Queue<List<Integer>> queue = new LinkedList<>();

        for(int r = 0; r < numRows; r++){
            for(int c = 0; c < numCols; c++){
                if(grid[r][c] == 1){
                    freshOrangesCount++;
                } else if(grid[r][c] == 2){
                    List<Integer> rottenOrangeCoordinate = new ArrayList<>();
                    rottenOrangeCoordinate.add(r);
                    rottenOrangeCoordinate.add(c);
                    queue.add(rottenOrangeCoordinate);
                }
            }
        }
        while(!queue.isEmpty() && freshOrangesCount != 0){
            /* remove everything in the queue currently, because at a given minute, all rotten oranges will act */
            /* so, we are not popping one by one. we pop all the current rotten oranges and make the oranges next to it rotten*/
            int qSize = queue.size();
            for(int i = 0; i < qSize; i++){
                List<Integer> rottenOrangeCoordinate = queue.remove();
                int currRow = rottenOrangeCoordinate.get(0);
                int currCol = rottenOrangeCoordinate.get(1);
                for(int[] dir: dirs){
                    int x = dir[0] + currRow;
                    int y = dir[1] + currCol;
                    if(x >= 0 && x < numRows && y >= 0 && y < numCols && grid[x][y] == 1){
                        grid[x][y] = 2;
                        List<Integer> newlyRottenOrangeCoord = new ArrayList<>();
                        newlyRottenOrangeCoord.add(x);
                        newlyRottenOrangeCoord.add(y);
                        queue.add(newlyRottenOrangeCoord);
                        freshOrangesCount -= 1;
                    }
                }
            }
            time++;
        }
        return freshOrangesCount == 0 ? time: -1;
    }


    public static void main(String[] args){
        int[][] grid = {{2, 1, 1},{1, 1, 0},{0, 1,1}}; // expected output: 4
//        int[][] grid ={{2,1,1}, {0,1,1}, {1,0,1}}; // expected output: -1
//        int[][] grid = {{0,2}}; // expected output: 0
        int time = orangesRotting(grid);
        System.out.println("Time taken for all oranges to be rotten: " + time);
    }
}
