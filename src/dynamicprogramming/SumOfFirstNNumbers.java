package dynamicprogramming;

public class SumOfFirstNNumbers {
    /* Find the sum of the first n numbers, given n */
    /* DP solution to calculate sum of first n numbers */
    /* sum(n) = sum(n-1) + n */

    /* 1. Objective function: F(i) represents the sum of the first i numbers */
    public static int nSum(int n){
       int[] dp = new int[n + 1];
       /* 2. Base Case */
       /* we start with finding sum(1) instead of sum(0) because for sum(0), we'll need sum(-1) which is not possible */
       dp[0] = 0;

       /* start with i = 1 instead of i = 0 */
       for(int i = 1; i < dp.length; i++){
           /* 3 & 4: Recurrence relation & Order of Computation */
           /* F(n) = F(n-1) + 1 */
           dp[i] = dp[i - 1] + i;
       }

       /* 5. Location of answer */
       /* answer in last element */
        return dp[n];
    }
    public static void main(String[] args){
        System.out.println("Sum of first 0 numbers is " + nSum(0));
        System.out.println("Sum of first 1 numbers is " + nSum(1));
        System.out.println("Sum of first 10 numbers is " + nSum(10));
    }
}
