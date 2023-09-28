package blind75;

public class LongestPalindromicSubstring {
    /* 3. Longest Palindromic Substring
        Input: s = "babad"
        Output: "bab"
        Explanation: "aba" is also a valid answer.

        Input: s = "cbbd"
        Output: "bb"

        Level of difficulty: medium

     */

    public String longestPalindrome_Efficient(String s){
        /* TC: O(N^2) */
        String longestPalindrome = "";
        for(int i = 0; i < s.length(); i++){
            /* check odd length palindromes */
            int left = i;
            int right = i;
            while(left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)){
                String newPalindrome = s.substring(left, right + 1);
                if(newPalindrome.length() > longestPalindrome.length()){
                    longestPalindrome = newPalindrome;
                }
                left--;
                right++;
            }

            /* check even length palindromes */
            left = i;
            right = i + 1;
            while(left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)){
                String newPalindrome = s.substring(left, right + 1);
                if(newPalindrome.length() > longestPalindrome.length()){
                    longestPalindrome = newPalindrome;
                }
                left--;
                right++;
            }

        }
        return longestPalindrome;
    }
    public String longestPalindrome_BruteForce(String s) {
        /* TC: O(N^3) */
        if(s.length() == 1){
            return s;
        }
        if(s.length() == 2){
            if (s.charAt(0) == s.charAt(1)){
                return s;
            } else{
                return s.substring(0,1);
            }
        }
        String longestPalindrome = s.substring(0,1);
        for(int i = 0; i < s.length() - 1; i++){
            for(int j = i + 1; j < s.length(); j++){
                String sub = s.substring(i, j + 1 );
                if(isPalindrome(sub) && sub.length() > longestPalindrome.length()){
                    longestPalindrome = sub;
                }
            }
        }
        return longestPalindrome;
    }

    public static boolean isPalindrome(String s){
        if(s.length() == 0 || s.length() == 1){
            return true;
        }
        int left = 0;
        int right = s.length() - 1;
        while(left <= right){
            if(s.charAt(left) == s.charAt(right)){
                left++;
                right--;
            } else{
                return false;
            }
        }
        return true;
    }
}