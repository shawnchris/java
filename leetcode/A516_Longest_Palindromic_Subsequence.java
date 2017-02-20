package leetcode;

public class A516_Longest_Palindromic_Subsequence {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        if (n == 0) return 0;
        
        return helper(s, 0, n - 1, new int[n][n]);
    }
    
    private int helper(String s, int left, int right, int[][] cache) {
        if (left > right) return 0;
        if (left == right) return 1;
        
        if (cache[left][right] == 0) {
            if (s.charAt(left) == s.charAt(right)) {
                cache[left][right] = helper(s, left + 1, right - 1, cache) + 2;
            } else {
                cache[left][right] = Math.max(helper(s, left + 1, right, cache), helper(s, left, right - 1, cache));
            }
        }
        return cache[left][right];
    }
}
