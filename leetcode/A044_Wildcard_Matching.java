package leetcode;

public class A044_Wildcard_Matching {
    public boolean isMatch(String s, String p) {
        int slen = s.length();
		int plen = p.length();
		int sp=0, pp=0, matchp=0, starp=-1;
		
		while (sp<slen) {
		    // advancing both pointers
			if (pp<plen && (s.charAt(sp)==p.charAt(pp) || p.charAt(pp)=='?')) {
				sp++; pp++;
			}
			// * found, only advancing pattern pointer
			else if (pp<plen && p.charAt(pp)=='*') {
				starp=pp;
				matchp=sp;
				//sp++;
				pp++;
			}
			// last pattern pointer was *, advancing string pointer
			else if(starp!=-1) {
				pp = starp+1;
				matchp++;
				sp=matchp;
			}
			// current pattern pointer is not star, last pattern pointer was not *
            // characters do not match
			else
				return false;
		}
		// check for remaining characters in pattern
		while (pp<plen && p.charAt(pp)=='*')
			pp++;
		
		return (pp==plen);
    }
    
    //DP solution
    public static boolean isMatch2(String s, String p) {
        if (s == null && p == null) return true;
        if (s.length() == 0 && p.length() == 0) return true;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        //init
        dp[0][0] = true;
        for (int j = 1; j <= p.length(); j++) {
            if (p.charAt(j - 1) == '*' && dp[0][j - 1]) {
                dp[0][j] = true;
            }
        }
        //loop
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }
                if (p.charAt(j - 1) == '*') {
                    if (dp[i - 1][j]) {
                        dp[i][j] = true;
                        continue;
                    }
                    for (int k = i; k >= 0; k--) {
                        if (dp[k][j - 1]) {
                            dp[i][j] = true;
                            break;
                        }
                    }
                }
            }
        }
        //debug code
        System.out.print(" \t_\t");
        for (int j = 1; j <= p.length(); j++) {
        	System.out.print(p.charAt(j - 1) + "\t");
        }
        System.out.println();
        for (int i = 0; i <= s.length(); i++) {
        	if (i == 0) System.out.print("_\t");
        	else System.out.print(s.charAt(i - 1) + "\t");
            for (int j = 0; j <= p.length(); j++) {
            	System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }
        //result
        return dp[s.length()][p.length()];
    }
    
    public static void main(String[] args) {
    	System.out.println(isMatch2("abefcdgiescdfimde","ab*cd?i*de"));
    }
}
