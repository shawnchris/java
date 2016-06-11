package leetcode;

public class A005_Longest_Palindromic_Substring {
    int maxLen, leftIndex;
    
    private void extendPalindrome(String s, int left, int right) {
        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        int newLen = right - left - 1;
        if (newLen > maxLen) {
            maxLen = newLen;
            leftIndex = left + 1;
        }
    }
    
    public String longestPalindrome(String s) {
        if (s == null) return null;
        int len = s.length();
        if (len < 2) return s;
        
        for (int i = 0; i < len; i++) { // i is the mid point
            extendPalindrome(s, i, i); // odd length;
            extendPalindrome(s, i, i + 1); // even length
        }
        
        return s.substring(leftIndex, leftIndex + maxLen);
    }
}
