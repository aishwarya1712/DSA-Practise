package blind75;

import java.util.HashMap;

public class TwoSum {
    /* 1. Two Sum
    * Difficulty level: Easy
    * */
    public int[] twoSum(int[] nums, int target) {
        // given a target sum and an array of numbers, return indices of the two numbers such that they add up to target

        // create the result array
        int[] result = new int[2];

        // create and populate hashmap with key as nums[i] and value as the index i
        HashMap<Integer, Integer> numMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            numMap.put(nums[i], i);
        }

        // loop once through the array
        for(int i = 0; i < nums.length; i++){
            // calculate the complement value such that complement + nums[i] = target
            int complement = target - nums[i];

            // check if complement exists in the numMap. if it does, return the two indices
            if(numMap.containsKey(complement) && numMap.get(complement) != i){
                result[0] = i;
                result[1] = numMap.get(complement);
                return result;
            }
        }
        return nums;
    }
}
