package leetcode;

public class A005_Longest_Palindromic_Substring {
	// Manacher’s Algorithm
	
	// Transform S into T. For example, S = "abba", T = "^#a#b#b#a#$".
	// ^ and $ signs are sentinels appended to each end to avoid bounds checking
	char[] preProcess(String s) {
	  int n = s.length();
	  if (n == 0) return "^$".toCharArray();
	  StringBuilder sb = new StringBuilder("^");
	  for (int i = 0; i < n; i++)
		  sb.append("#" + s.charAt(i));
	 
	  sb.append("#$");
	  return sb.toString().toCharArray();
	}
	 
	String longestPalindrome(String s) {
	  char[] T = preProcess(s);
	  int n = T.length;
	  int[] P = new int[n];
	  int C = 0, R = 0;
	  for (int i = 1; i < n-1; i++) {
	    int i_mirror = 2 * C - i; // equals to i' = C - (i-C)
	    
	    P[i] = (R > i) ? Math.min(R - i, P[i_mirror]) : 0;
	    
	    // Attempt to expand palindrome centered at i
	    while (T[i + 1 + P[i]] == T[i - 1 - P[i]])
	      P[i]++;
	 
	    // If palindrome centered at i expand past R,
	    // adjust center based on expanded palindrome.
	    if (i + P[i] > R) {
	      C = i;
	      R = i + P[i];
	    }
	  }
	 
	  // Find the maximum element in P.
	  int maxLen = 0;
	  int centerIndex = 0;
	  for (int i = 1; i < n - 1; i++) {
	    if (P[i] > maxLen) {
	      maxLen = P[i];
	      centerIndex = i;
	    }
	  }
	  
	  int start = (centerIndex - 1 - maxLen) / 2;
	  return s.substring(start, start + maxLen);
	}
    
    public static String longestPalindrome2(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start + 1) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
    
    public static String longestPalindrome3(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = extend(s, i, i); // odd
            int len2 = extend(s, i, i + 1); // even
            int max = Math.max(len1, len2);
            if (max > end - start + 1) {
                start = i - (max - 1) / 2;
                end = i + max / 2;
            }
        }
        return s.substring(start, end + 1);
    }
    
    private static int extend(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return i - j - 1;
    }
    
    public static void main(String[] args) {
    	System.out.println(longestPalindrome2("babad"));
    	System.out.println(longestPalindrome3("babad"));
    }
}
