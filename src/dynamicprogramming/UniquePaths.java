package dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/* LC 62 */
/* Problem: There is a robot on an m x n grid.
The robot is initially located at the top-left corner (i.e., grid[0][0]).
The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
The robot can only move either down or right at any point in time.
Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
*/
public class UniquePaths {
    public static int numUniquePaths(int m, int n){
        /* 1. F(i,j) is the number of unique ways to reach i,j from 0,0 */
        int dp[][] = new int[m][n];

        /* 2. Identify base cases */
        dp[0][0] = 1;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                /* 3. Recurrence function: F(i, j) = F(i - 1, j) + F(i, j - 1) */
                if(i > 0 && j > 0){
                    /* regular */
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else if(i > 0 ){
                    /* meaning j == 0 */
                    dp[i][j] = dp[i - 1][j];
                } else if(j > 0){
                    /* meaning i == 0 */
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        /* 4. Order of computation: bottom up */
        /* 5. Location of answer */
        return dp[m-1][n-1];
    }

    public static int numUniquePaths_WithObstacles(int m, int n, boolean[][] obstacle){
        /* 1. F(i,j) is the number of unique ways to reach i,j from 0,0 */
        int dp[][] = new int[m][n];

        /* 2. Identify base cases */
        dp[0][0] = 1;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(obstacle[i][j]){
                    dp[i][j] = 0; // imp!
                }
                /* 3. Recurrence function: F(i, j) = F(i - 1, j) + F(i, j - 1) */
                else if(i > 0 && j > 0){
                    /* regular */
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else if(i > 0 ){
                    /* meaning j == 0 */
                    dp[i][j] = dp[i - 1][j];
                } else if(j > 0){
                    /* meaning i == 0 */
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        /* 4. Order of computation: bottom up */
        /* 5. Location of answer */
        return dp[m-1][n-1];
    }

    public static int maxProfit(int[][] grid){
        /* Recurrence/transition function
        F(i, j) = Math.max(F(i-1,j), F(i, j-1)) + grid[i][j]
         */
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i > 0 && j > 0){
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                    if(dp[i - 1][j] >= dp[i][j - 1]){
                    }
                } else if (i > 0){
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else if(j > 0){
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                }
            }
        }
        System.out.println("Path is: " + getMaxProfitPath(dp, m-1, n-1, new ArrayList<>()));
        return dp[m - 1][n - 1];
    }

    public static List<List<Integer>> getMaxProfitPath(int[][] dp, int i, int j, List<List<Integer>> path){
        if(i == 0 && j == 0){
            List<Integer> toAdd = new ArrayList<>();
            toAdd.add(i);
            toAdd.add(j);
            path.add(toAdd);
            return path;
        } else if(i > 0 && j > 0){
            if(dp[i - 1][j] > dp[i][j-1]){
                // came from top
                path = getMaxProfitPath(dp, i-1, j, path);
            } else {
                // came from left
                path = getMaxProfitPath(dp, i, j - 1, path);
            }
        } else if(i == 0){
            // came from left
            path = getMaxProfitPath(dp, i, j - 1, path);

        } else if(j == 0) {
            // came from top
            path = getMaxProfitPath(dp, i-1, j, path);

        }
        List<Integer> toAdd = new ArrayList<>();
        toAdd.add(i);
        toAdd.add(j);
        path.add(toAdd);
        return path;
    }
    public static void main(String[] args){
        System.out.println("Num of unique paths for 3x7 array: " + numUniquePaths(3, 7));
        System.out.println("Num of unique paths for 3x2 array: " + numUniquePaths(3, 2));

        boolean[][] obstacles = new boolean[3][4];
        obstacles[1][2] = true;
        obstacles[1][3] = true;
        System.out.println("Num of unique paths for 3x2 array, with obstacles: " + numUniquePaths_WithObstacles(3, 2, obstacles));

        int[][] costGrid = {
                {0, 2, 2, 50},
                {3, 1, 1, 100},
                {4, 4, 2, 0}
        };
        System.out.println("Most profit attainable in path to the bottom-right corner is: " + maxProfit(costGrid));
    }
}
