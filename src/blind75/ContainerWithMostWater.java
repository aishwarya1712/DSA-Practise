package blind75;

public class ContainerWithMostWater {
    /* 4. Container With Most Water
    You are given an integer array height
    There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
    Find two lines that together with the x-axis form a container, such that the container contains the most water.
    Return the maximum amount of water a container can store.
    Level of Difficulty: Medium
     */
    public int containerWithMostWater_Efficient(int[] height){
        /* TC: O(N) */
        int maxWater = 0;
        int left = 0;
        int right = height.length - 1;
        while(left < right){
            int width = right - left; /* this is the width along the x axis */
            int length = Math.min(height[left], height[right]); /* length along the y axis:will be minimum of the two lines*/
            int area = width * length;
            maxWater = Math.max(maxWater, area);
            if(height[left] > height[right]){
                right--;
            } else {
                left++;
            }
        }
        return maxWater;
    }
    public int containerWithMostWater_BruteForce(int[] height){
        /* TC: O(N^2) */
             int maxWater = 0;
             /* calculate area of every two lines */
             for(int i = 1; i < height.length; i++){
                 for(int j = i + 1; j <= height.length; j++){
                     int length = Math.min(height[i-1], height[j-1]);
                     int width = j - i;
                     int area = length * width;
                     maxWater = Math.max(maxWater, area);
                 }
             }
             return maxWater;
    }
}
