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
    public String longestPalindrome_BruteForce(String s) {
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