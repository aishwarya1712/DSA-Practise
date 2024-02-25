package dynamicprogramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* Problem
    You are climbing a staircase with n steps to reach the top.
    Each time, you can either climb one step or two steps.
    In how many distinct ways can you reach the top?

    Framework for solving DP problems:
    1. Define Objective Function
    2. Identify Base-Cases
    3. Recurrence Function
    4. Order of Computation
    5. Location of Answer
 */
public class ClimbingStairs {

    /* TC: O(N), SC: O(N) */
    public static int numberOfWaysToReachTop(int n){
        /* 1. Objective function, to find F(i), which represents the number of distinct ways to reach the ith stair */
        if(n == 0 || n == 1){
            return 1;
        }

        int[] dp = new int[n + 1];

        /* 2. Identify base cases: F(0) and F(1) */
        dp[0] = 1; // only one way to get to the top of 0 steps
        dp[1] = 1; // only one way to get to the top of 1 step

        /* 3. Recurrence function F(n) = F(n-1) + F(n-2) */
        for(int i = 2; i < dp.length; i++){
            /* 4. Order of computation: bottom-up, since we always rely on the two previous calculations */
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        /* 5. Location of answer - in the last element */
        return dp[n];
    }

    /* TC: O(N), SC: O(N) */
    public static int numberOfWaysToReachTop_SCOptimized(int n){
        /* 1. Objective function, to find F(i), which represents the number of distinct ways to reach the ith stair */
        if(n == 0 || n == 1){
            return 1;
        }

        /* 2. Identify base cases: F(0) and F(1) */
        int a = 1;
        int b = 1;
        int c = 0;
        /* 3. Recurrence function F(n) = F(n-1) + F(n-2) */
        for(int i = 2; i <= n; i++){
            c = a + b;
            a = b;
            b = c;
        }

        /* 5. Location of answer - in the last element */
        return c;
    }

    public static int numWaysToReachTop_3Steps(int n){
        /* Problem
    You are climbing a staircase with n steps to reach the top.
    Each time, you can take 1 step, 2 steps or 3 steps.
    In how many distinct ways can you reach the top?
     */
        int a = 1;
        int b = 1;
        int c = 2;
        int d = 0;
        for(int i = 3; i <= n; i++){
            d = a + b + c;
            a = b;
            b = c;
            c = d;
        }

        return d;
    }

    public static int numWaysToReachTop_KSteps(int n, int k){
        /* Problem
    You are climbing a staircase with n steps to reach the top.
    Each time, you can take [1,k] steps.
    In how many distinct ways can you reach the top?
     */
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            for(int j = 1; j <= k; j++){
                if(i - j < 0){
                    continue;
                }
                dp[i] = dp[i] + dp[i - j];
            }
        }

        return dp[n];
    }

    public static int numWaysToReachTop_KSteps_SkipRed(int n, int k, boolean[] redStairs){
        /* Problem
    You are climbing a staircase with n steps to reach the top.
    Each time, you can take [1,k] steps.
    In how many distinct ways can you reach the top?
     */

        int[] dp = new int[n + 1];
        dp[0] = 1;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= k; j++){
                if(i - j < 0){
                    continue;
                }
                /* skip red */
                if(redStairs[i]){
                    dp[i] = 0;
                }else {
                    dp[i] = dp[i] + dp[i - j];
                }
            }
        }

        return dp[n];
    }

    public static int cheapestWayToTop(int n, int[] prices){
        /* You are climbing a paid staircase with n steps.
        Each time, you can take 1 or 2 steps.
        It takes prices[i] to reach the ith step.
        What's the cheapest amount you have to pay to reach the top?
         */
        /* 1. Define Objective function: F(i) is the cheapest way to get to the ith stair
        * 2. Identify Base Cases: F(0) is always 0, F(1) is prices[1]
        * 3. Recurrence function: F(n) = min(F(n-1), F(n-2)) + prices[n]
        * 4. Order of computation: Bottom-up
        * 5. Location of answer: dp[n] */

        int[] dp = new int[n + 1];
        String path = "";
        dp[0] = 0;
        dp[1] = prices[1];
        for(int i = 2; i <= n; i++){
            int min = 0;
            if(dp[i - 1] <= dp[i - 2]){
                min = dp[i - 1];
                path += "-->" + (i - 1);
            } else {
                min = dp[i - 2];
                path += "-->" + (i - 2);
            }
            dp[i] = min + prices[i];
        }
        path += "-->" + n;
        System.out.println("Path is: " + path);
        return dp[n];
    }

    public static List<Integer> cheapestPathToTop(int n, int[] prices){
        /* You are climbing a paid staircase with n steps.
        Each time, you can take 1 or 2 steps.
        It takes prices[i] to reach the ith step.
        Return the cheapest path to reach the top?
         */
        /* 1. Define Objective function: F(i) is the cheapest way to get to the ith stair
         * 2. Identify Base Cases: F(0) is always 0, F(1) is prices[1]
         * 3. Recurrence function: F(n) = min(F(n-1), F(n-2)) + prices[n]
         * 4. Order of computation: Bottom-up
         * 5. Location of answer: dp[n] */

        int[] dp = new int[n + 1];
        int[] from = new int[n + 1];
        dp[0] = 0;
        dp[1] = prices[1];
        for(int i = 2; i <= n; i++){
            int min;
            if(dp[i - 1] <= dp[i - 2]){
                min = dp[i - 1];
                from[i] = i - 1;
            } else {
                min = dp[i - 2];
                from[i] = i - 2;
            }
            dp[i] = min + prices[i];
        }

        List<Integer> path = new ArrayList<>();
        for(int i = n; i >= 0; i = from[i]){
            path.add(i);
            if(i == 0){
                break;
            }
        }
        System.out.println("path is: " + path);
        return path.reversed();
    }


    public static void main(String[] args){

        System.out.println("Number of ways to reach top of 5 stairs with 1 or 2 steps: " + numberOfWaysToReachTop_SCOptimized(5));
        System.out.println("Number of ways to reach top of 5 stairs with 1 2 or 3 steps at a time: " + numWaysToReachTop_3Steps(5));
        System.out.println("Number of ways to reach top of 5 stairs with [1,4] steps at a time: " + numWaysToReachTop_KSteps(5, 4));

        boolean[] redStairs = new boolean[8];
        redStairs[1] = true;
        redStairs[3] = true;
        redStairs[4] = true;
        System.out.println("Number of ways to reach top of 7 stairs with [1,3] steps at a time, skipping step numbers 1, 3 and 4 are: " + numWaysToReachTop_KSteps_SkipRed(7, 3, redStairs));


        int[] prices = new int[]{0, 3, 2, 4};
        System.out.println("Cheapest way to get to the top of 3 stairs with max 2 steps at a time: " + cheapestWayToTop(3, prices));

        int[] pricesNew = new int[]{0, 3, 2, 4, 6, 1, 1, 5, 3};
        System.out.println("Cheapest path to get to the top of 8 stairs with max 2 steps at a time: " + cheapestPathToTop(8, pricesNew));
    }
}
