package dynamicprogramming;

public class HouseRobber {
    /*
    You are a professional robber planning to rob houses along a street.
    Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that
    adjacent houses have security systems connected and it will automatically contact the police
    if two adjacent houses were broken into on the same night.

    Given an integer array nums representing the amount of money of each house,
    return the maximum amount of money you can rob tonight without alerting the police.
     */
    public static int rob(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(i-2>=0){
                dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1]);
            } else {
                dp[i] = Math.max(nums[i-1], nums[i]); // when i = 1
            }
        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args){
        System.out.println(rob(new int[]{1, 2, 3, 1}));
        System.out.println(rob(new int[]{2,7,9,3,1}));
        System.out.println(rob(new int[]{5, 1, 1, 20}));
    }
}
