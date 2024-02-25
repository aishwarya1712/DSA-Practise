package dynamicprogramming;

public class PaintingFences {
    /* Problem: Paint n posts of a Fence with two colors: Blue (0) and Green (1)

    There is a fence with n posts. Each post can either be painted blue (0), or green (1).
    You have to paint all fences such that no more than two adjacent fences have the same color.
     Return the total # of ways to paint the fence.
     */
    public static int numWays(int n){
        /* Define Objective Function */
        int[][] dp = new int[n+1][2];

        /* Identify base cases */
        dp[1][0] = 1; // if we have a fence of size 1 (i=1) and the last post is blue (j=0), there is one way to color the fence: B
        dp[1][1] = 1; // if we have a fence of size 1 (i=1) and the last post is green (j=0), there is one way to color the fence: G

        dp[2][0] = 2; // if we have 2 posts (i=2), and the last post is blue (j=0), then there are 2 ways to color these two posts: BB or GB
        dp[2][1] = 2; // if we have 2 posts (i=2), and the last post is green (j=1), then there are 2 ways to color these two posts: BG or GG

        for(int i = 3; i <= n; i++){
            for(int j = 0; j < 2; j++){
                /* Recurrence/transition function */
                dp[i][j] = dp[i-1][1-j] + dp[i-2][1-j];
            }
        }

        /* Location of answer:
        number of ways to paint n posts of a fence such that nth fence is blue +
        number of ways to paint n posts of a fence such that nth fence is green
         */
        return dp[n][0] + dp[n][1];
    }

    public static void main(String[] args){
        System.out.println("Number of ways to pain 4 posts of a fence is: " + numWays(4));
        System.out.println("Number of ways to pain 5 posts of a fence is: " + numWays(5));
    }
}
