package dynamicprogramming;

public class CoinChange {
    /* Problem: Given denominations {1, 3, 5, 10},
    count the # of ways to make a change of size 'amount', where order matters. i.e 1-3 is different from 3-1
    */
    public static int numWaysToMakeChange(int amount){
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for(int i = 1; i <= amount; i++){
            if(i >= 1){
                dp[i] += dp[i - 1];
            }
            if(i >= 3){
                dp[i] += dp[i - 3];
            }
            if(i >= 5){
                dp[i] += dp[i - 5];
            }
            if(i >= 10){
                dp[i] += dp[i - 10];
            }
        }
        return dp[amount];
    }

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

    public static void main(String[] args){
        System.out.println("Num ways to make change of size 10 with 1,3,5,10 are: "+ numWaysToMakeChange(10));
        System.out.println("Num ways to make change of size 10 with denominations 1,3,5,10 are: "+ numWaysToMakeChange_WithDenominations(10, new int[]{1,3, 5,10}));
    }
}
