package blind75;

import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharacters {
    /* 2. Longest Substring Without Repeating Characters
     * Difficulty level: Medium
     * */
    public int lengthOfLongestSubstring_BruteForce(String s) {
        /* given a string s, find the longest substring in s which does not have repeating characters */

        int maxLen = 0;

        for(int i = 0; i < s.length(); i++){
            /* Initialize a string builder object to append to */
            StringBuilder currentSub = new StringBuilder();
            for(int j = i; j < s.length(); j++){
                /*  check if the value we are going to add to the substring is already present. */
                if(currentSub.indexOf(String.valueOf(s.charAt(j))) != -1){
                    /* it is present the index will be greater than -1 in which case we want to break out of loop */
                    break;
                } else {
                    /* s.charAt(j) does not exist in currentSub, so we can add the new character and calculate its length */
                    currentSub.append(s.charAt(j));
                    if(currentSub.length() > maxLen){
                        maxLen = currentSub.length();
                    }
                }
            }
        }
        return maxLen;
    }

    public int lengthOfLongestSubstring_Efficient(String s) {
        /* Initialize left=0 and right=0 pointers and maxLen = 0 */
        int maxLen = 0;
        int left = 0;
        /* Create a hashmap, key = character of string, value = index it was last found */
        HashMap<Character, Integer> subMap = new HashMap<>();

        /* Loop through the string and increment right in each iteration  */
        for(int right = 0; right < s.length(); right++){
            char currentChar = s.charAt(right);

            /* if character at right pointer already exists in hashmap, and it is part of the current substring -> i.e its index is left or more */
            if(subMap.containsKey(currentChar) && subMap.get(currentChar) >= left){
                /* increment the left pointer to one more than the last position it was found  */
                left = subMap.get(currentChar) + 1;
            }
            /* Add character at right pointer to hashmap */
            subMap.put(currentChar, right);

            /* recalculate maxLen = max of old maxLen and (right - left + 1) which is the current substring */
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
