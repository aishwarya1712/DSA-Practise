package dynamicprogramming;

import java.util.*;

public class Fibonacci {
    /* 1. regular, pure recursive fibonacci: TC O(2^n) */
    public static int fibRecursive(int n){
        if(n == 0){
            return 0;
        }
        if(n == 1 || n == 2){
            return 1;
        }
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    /* 2. Top Down DP fibonacci: recursive + memoization */
    public static int fibRecursive_topDown(int n){
        Map<Integer, Integer> memo = new HashMap<>();
        return fibRecursive_topDown_Helper(n, memo);

    }

    public static int fibRecursive_topDown_Helper(int n, Map<Integer, Integer> memo){
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        memo.put(n, fibRecursive_topDown_Helper(n - 1, memo) + fibRecursive_topDown_Helper(n - 2, memo));
        return memo.get(n);
    }

    /* 3. forward because dp[i-1] and dp[i-2] used to calculate dp[i] *///
    // f(i-1)
    //      \
    //       >-------> f(i)
    //      /
    // f(i-2)
    //
    public static int fib_bottomUp_forward(int n){
        if(n == 0){
            return 0;
        }
        if(n == 1 || n == 2){
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }


    /* 4. Backward DP fibonacci, use dp[i] to calculate dp[i+1] and dp[i+2] */
    //
    //   -----> f(i+2)
    //   |
    // f(i)
    //   |
    //   -----> f(i+1)
    public static int fib_bottomUp_backward(int n){
        if(n == 0){
            return 0;
        }
        if(n == 1 || n == 2){
            return 1;
        }
        int[] dp = new int[n + 2];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 1; i < n; i++){
            // dp[i] is already solved, we can use it to solve other sub-problems
            dp[i + 1] += dp[i];
            dp[i + 2] += dp[i];
        }
        return dp[n];
    }
    public static void main(String[] args){
        System.out.println("Fib 5 using pure recursive: " + fibRecursive(5));
        System.out.println("Fib 5 using top-down dynamic programming, recursive + memoization: " + fibRecursive_topDown(5));
        System.out.println("Fib 5 using bottom-up, forward dynamic programming is: " + fib_bottomUp_forward(5));
        System.out.println("Fib 5 using bottom-up, backward dynamic programming is: "+ fib_bottomUp_backward(5));
    }
}
