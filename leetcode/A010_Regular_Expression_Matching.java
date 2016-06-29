package leetcode;

public class A010_Regular_Expression_Matching {
	/*
	DP[i][j] means preceding substring of length i of s and length j of p. For any two substrings, the value of DP[i][j] can be from one of following four cases:

    case 1: DP[i-1][j-1] is true, and ith character of s is equal to jth character of p. Or jth character of p is '.'
    case 2: DP[i-1][j] is true, then my pattern now is '*' and preceding character is equal to incoming character of s
    case 3: DP[i][j-1] is true, then my pattern now is '*' which can match an empty string
    case 4: DP[i][j-2] is true, and the pattern like (a*) matches an empty string

	base case is the DP[0][0], DP[i][0], DP[0][j].
	*/
	public boolean isMatch(String s, String p) {
		if (s == null && p == null) return true;
        if (s.length() == 0 && p.length() == 0) return true;

        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= s.length(); i++) {
            dp[i][0] = false;
        }
        for (int j = 1; j <= p.length(); j++) {
            if (p.charAt(j - 1) == '*' && j > 1) {
                dp[0][j] = dp[0][j - 2];
            }
            else {
                dp[0][j] = false;
            }
        }
        
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                // Case 1:
                if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else if (p.charAt(j - 1) == '*' && j > 1) {
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
                    	// Case 2, 3, 4:
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2] || dp[i][j - 1];
                        //dp[i-1][j]:abb vs ab*: depends on ab vs ab*
                        //dp[i][j-2] a vs ab* or a vs a.*: depends on a vs a
                        //dp[i][j-1] ab vs ab*: depends on ab vs ab
                    }
                    else {
                    	// Case 4:
                        dp[i][j] = dp[i][j - 2];
                    }
                }
                else {
                    dp[i][j] = false;
                }
            }
        }
        
        return dp[s.length()][p.length()];
    }
}
