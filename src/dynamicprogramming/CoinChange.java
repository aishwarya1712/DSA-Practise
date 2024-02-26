package dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinChange {
    /* Problem Part 1: Given denominations {1, 3, 5, 10},
    count the # of ways to make a change of size 'amount', where order matters. i.e 1-3 is different from 3-1
    */
    public static int numWaysToMakeChange_WithDenominations(int amount, int[] denominations){
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for(int i = 1; i <= amount; i++){
            for(int d: denominations){
                if(i >= d){
                    dp[i] += dp[i - d];
                }
            }
        }
        return dp[amount];
    }
    /* End of Part 1 */

    /* Problem Part 2(a): Given an unlimited supply of coins of given denominations,
    * find the total number of ways to make a change of size n using exactly t coins */
    public static int numWays_exactlyTCoins(int n, int t, List<Integer> denominations) {
        /* 1. Objective function: F(i, t) is the number of ways to make change of size i using exactly t coins */
        int[][] dp = new int[n + 1][t + 1];

        /* 2. Identify base cases */
        dp[0][0] = 1;

        // F(i, 0) = 0
        for(int i = 1; i <= n; i++){
            dp[i][0] = 0;
        }
        // F(0, i)
        for(int j = 1; j <= t; j++){
            dp[0][j] = 0;
        }
        // F(i, 1)
        for(int i = 1; i <= n; i++){
            if(denominations.contains(i)){
                dp[i][1] = 1;
            } else {
                dp[i][1] = 0;
            }
        }

        /* 3. Recurrence function. If denominations are {1, 2, 3, 5} then:
                F(i,t) = F(i - 1, t - 1) + F(i - 2, t - 1) + F(i - 3, t - 1) + F(i - 5, t - 1)
         */
        for(int i = 1; i <=n; i++){
            for(int j = 2; j <= t; j++){
                for(int coin:denominations){
                    if(i - coin >= 0){
                        dp[i][j] += dp[i - coin][j - 1];
                    }
                }
            }
        }

        /* 5. Location of answer */
        return dp[n][t];
    }

    /* End of Problem Part 2(a)  */

    /* Start of Problem 2(b)

     */

    public static void main(String[] args){
        System.out.println("Num ways to make change of size 10 with denominations 1,3,5,10 are: "+ numWaysToMakeChange_WithDenominations(10, new int[]{1,3, 5,10}));
        System.out.println(numWays_exactlyTCoins(7, 3, new ArrayList<>(Arrays.asList(1, 2, 3, 5))));
    }
}
