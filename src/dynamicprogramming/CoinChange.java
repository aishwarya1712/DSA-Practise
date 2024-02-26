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
        // F(0, j)
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

    /* Problem Part 2(b): Given an unlimited supply of coins of given denominations,
     * find the total number of ways to make a change of size n using NO MORE THAN t coins
     */

    public static int numWays_noMoreThanTCoins(int n, int t, List<Integer> denominations){
        /* 1. Define Objective Function
        F(i, t) is the number ways to make a change of size i using no more than t coins
         */

        int[][] dp = new int[n+1][t+1];

        // F(0,0) = 1
        dp[0][0] = 1;

        // F(0, j) = 1;
        for(int j = 1; j <=t; j++){
            dp[0][j] = 1;
        }

        // F(i, 0) = 0
        for(int i = 1; i <= n; i++){
            dp[i][0] = 0;
        }

        // F(i, 1) = 1 if i is in denominations;
        for(int i = 1; i <= n; i++){
            if(denominations.contains(i)){
                dp[i][1] = 1;
            } else {
                dp[i][1] = 0;
            }
        }

        for(int i = 2; i <= n; i++){
            for(int j = 2; j <=t; j++){
                for(int coin: denominations){
                    if(i - coin >= 0){
                        dp[i][j] += dp[i - coin][j - 1];
                    }
                }
            }
        }
        return dp[n][t];
    }
    /* End of Problem Part 2(b) */


    /* Part 3:
        Given an unlimited supply of coins of given denominations,
     * find the total number of ways to make a change of size n using an even number of coins
     */
    public static int numWays_EvenNumberOfCoins(int n, List<Integer> denominations){
        int[][] dp = new int[n+1][2];
        /* 0 represents odd and 1 represents even */
        dp[0][0] = 0;
        dp[0][1] = 1;
        // dp[i][0]
        for(int i = 1; i <= n; i++){
            for(int coin : denominations) {
                if (i - coin >= 0) {
                        dp[i][0] += dp[i - coin][1];
                        dp[i][1] += dp[i - coin][0];
                }
            }
        }
        return dp[n][1];
    }
    /* End of part 3 */

    /* Part 4: Print Unique sets of coins only! */
    public static int numWays_unique(int n, List<Integer> coins){
        int[][] dp = new int[n + 1][coins.size()];
        dp[0][0] = 1;
        // dp[i][0] when i > 0 => eg: how to make 1 such that the last coin is <= 0? = 0!
        for(int i = 1; i <= n; i++){
            dp[i][0] = 0;
        }

        //dp[0][j] => total number of ways to make zero such that the last count <= i? 1!
        for(int j = 1; j < coins.size(); j++){
            dp[0][j] = 1;
        }

        for(int i = 0; i <= n; i++){
            for(int j = 0; j < coins.size(); j++){
                for(int k = 0; k <= j; k++){
                    if(i - coins.get(k) >= 0) {
                        dp[i][j] += dp[i - coins.get(k)][k];
                    }
                }
            }
        }

        return dp[n][coins.size() - 1];


    }

    public static int numWays_unique_easier(int n, List<Integer> coins){
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for(int d: coins){
            for(int i = 1; i <= n; i++){
                if(i >= d){
                    dp[i] += dp[i - d];
                }
            }
        }
        return dp[n];

    }
    /* end of part 4 */

    /* Part 5:
        You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
        Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
        You may assume that you have an infinite number of each kind of coin.
     */
    public static int minCoins(int n, List<Integer> denominations){
        int[] dp = new int[n+1];
        dp[0] = 0;
        for(int i = 1; i<=n; i++){
            dp[i] = Integer.MAX_VALUE;
        }
        for(int coin: denominations){
            for(int i = 1; i <=n ; i++){
                if(i - coin >= 0) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }
        return dp[n];
    }

    /* End of Part 5 */
    public static void main(String[] args){
        System.out.println("Num ways to make change of size 10 with denominations 1,3,5,10 are: "+ numWaysToMakeChange_WithDenominations(10, new int[]{1,3, 5,10}));
        System.out.println(numWays_exactlyTCoins(7, 3, new ArrayList<>(Arrays.asList(1, 2, 3, 5))));
        System.out.println(numWays_noMoreThanTCoins(7, 3, new ArrayList<>(Arrays.asList(1, 2, 3, 5))));
        System.out.println(numWays_EvenNumberOfCoins(4, new ArrayList<>(Arrays.asList(1, 3, 5, 10))));
        System.out.println(numWays_EvenNumberOfCoins(6, new ArrayList<>(Arrays.asList(1, 3, 5, 10))));
        System.out.println(numWays_unique(75, new ArrayList<>(Arrays.asList(1, 2, 3, 5))));
        System.out.println(numWays_unique_easier(75, new ArrayList<>(Arrays.asList(1, 2, 3, 5))));
        System.out.println(minCoins(29, new ArrayList<>(Arrays.asList(1, 3, 5))));
    }
}
