package blind75;

import java.util.*;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {

         /* sort the i/p array */
        Arrays.sort(nums); // O(n logn)


        /* we are using a hashset so any duplicate values get automatically removed */
        Set<List<Integer>> output_arr = new HashSet<>();

        /* since we want to triplet, we need to stop at the third last element */
        // O(N^2)
        for(int i = 0; i < nums.length - 2; i++){

            /* start left at one past current element */
            int left = i + 1;
            /* right is the last element */
            int right = nums.length - 1;

            int target = 0 - nums[i];

            /* now this is a two sum problem */
            while(left < right){
                int sum = nums[left] + nums[right];
                if(sum == target){
                    List<Integer> triplet = Arrays.asList(nums[i], nums[left], nums[right]);
                    output_arr.add(triplet);
                    left++;
                    right--;
                } else if(sum < target){
                    left++;
                } else {
                    right--;
                }
            }
        }

        return new ArrayList<>(output_arr);
    }
}
