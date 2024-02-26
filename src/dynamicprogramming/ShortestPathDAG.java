package dynamicprogramming;

/*
Problem:
	Shortest Path in DAG

	Given a graph as an adjacency matrix, find the shortest path
	from the first to the last vertex.

	Objective function:
	F[i] is the shortest path from i to the last vertex.

	Transition function:
	F[i] = min[weight + F[j]]

	Base case:
	F[n] = 0
*/

public class ShortestPathDAG {
    public static int shortestPath(int[][] graph){
        /* 1. Define Objective Function
        * F(i) is the shortest path to get from i to the last vertex */
        int n = graph.length;
        int[] dp = new int[n];
        /* 2. Base Cases */
        dp[n - 1] = 0; // shortest way to get from node n-1 to node n-1 is 0.
        for(int i = 0; i < n -1; i++){
            dp[i] = Integer.MAX_VALUE; // for all other nodes, it is initially infinity
        }
        for(int i = n - 2; i >= 0; i--){ // start from the second last node
            for(int j = 0; j < graph[i].length; j++){
                int wt = graph[i][j];
                if(wt > 0){
                    /* 3. Recurrence relation */
                    dp[i] = Math.min(dp[i], wt + dp[j]);
                }
            }
        }
        /* Location of answer */
        return dp[0];
    }

    public static void main(String[] args){
        int[][] graph = new int[10][10];
        graph[0][1] = 1;
        graph[0][2] = 2;
        graph[0][3] = 3;

        graph[1][4] = 2;
        graph[1][5] = 1;
        graph[1][6] = 1;

        graph[2][4] = 1;
        graph[2][5] = 2;
        graph[2][6] = 1;

        graph[3][4] = 2;
        graph[3][5] = 3;
        graph[3][6] = 2;

        graph[4][7] = 3;
        graph[4][8] = 2;

        graph[5][7] = 3;
        graph[5][8] = 3;

        graph[6][7] = 1;
        graph[6][8] = 3;

        graph[7][9] = 4;
        graph[8][9] = 3;
        System.out.println(shortestPath(graph));
    }
}
