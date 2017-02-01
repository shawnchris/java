package leetcode;

public class A471_Encode_String_with_Shortest_Length {
/*
 * dp[i][j] = string from index i to index j in encoded form. We can write the following formula as:-
 * dp[i][j] = min(dp[i][j], dp[i][k] + dp[k+1][j]) or if we can find some pattern in string from i to j 
 * which will result in more less length.
 */
	
	public String encode2(String s) {
		int len = s.length();
		String[][] dp = new String[len][len];
		
		for (int l = 0; l < len; l++) { // length from 0 to len - 1
			for (int i = 0; i < len - l; i++) { // start of substr from 0 to len - l
				int j = i + l; // end of substr
				String substr = s.substring(i, j + 1);
				dp[i][j] = substr;
				
				// no way to shorten
				if (l < 4) continue;
				
				// try different combination of existing dp strings
				for (int k = i; k < j; k++) {
					if (dp[i][k].length() + dp[k + 1][j].length() < dp[i][j].length()) {
						dp[i][j] = dp[i][k] + dp[k + 1][j];
					}
				}
				
				// try to find new pattern
				for (int k = 0; k < substr.length(); k++) {
					String pattern = substr.substring(0, k + 1);
					if (pattern.length() != 0 && substr.length() % pattern.length() == 0
						&& substr.replaceAll(pattern, "").length() == 0) {
						String ss = substr.length() / pattern.length() + "[" + dp[i][i + k] + "]";
						if (ss.length() < dp[i][j].length()) {
							dp[i][j] = ss;
						}
					}
				}
			}
		}
		
		return dp[0][s.length() - 1];
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String encode(String s) {
		String[][] dp = new String[s.length()][s.length()];

		for (int l = 0; l < s.length(); l++) {
			for (int i = 0; i < s.length() - l; i++) {
				int j = i + l;
				String substr = s.substring(i, j + 1);
				// Checking if string length < 5. In that case, we know that encoding will not help.
				if (j - i < 4) {
					dp[i][j] = substr;
				} else {
					dp[i][j] = substr;
					// Loop for trying all results that we get after dividing
					// the strings into 2 and combine the results of 2 substrings
					for (int k = i; k < j; k++) {
						if ((dp[i][k] + dp[k + 1][j]).length() < dp[i][j].length()) {
							dp[i][j] = dp[i][k] + dp[k + 1][j];
						}
					}

					// Loop for checking if string can itself found some pattern
					// in it which could be repeated.
					for (int k = 0; k < substr.length(); k++) {
						String repeatStr = substr.substring(0, k + 1);
						if (repeatStr != null && substr.length() % repeatStr.length() == 0
								&& substr.replaceAll(repeatStr, "").length() == 0) {
							String ss = substr.length() / repeatStr.length() + "[" + dp[i][i + k] + "]";
							if (ss.length() < dp[i][j].length()) {
								dp[i][j] = ss;
							}
						}
					}
				}
			}
		}

		return dp[0][s.length() - 1];
	}

	public static void main(String[] args) {
		System.out.println(new A471_Encode_String_with_Shortest_Length().encode("aaaa"));
		System.out.println(new A471_Encode_String_with_Shortest_Length().encode2("aaaa"));
		System.out.println(new A471_Encode_String_with_Shortest_Length().encode("aaaaa"));
		System.out.println(new A471_Encode_String_with_Shortest_Length().encode2("aaaaa"));
		System.out.println(new A471_Encode_String_with_Shortest_Length().encode("aabcaabcd"));
		System.out.println(new A471_Encode_String_with_Shortest_Length().encode2("aabcaabcd"));
		System.out.println(new A471_Encode_String_with_Shortest_Length().encode("abbbabbbcabbbabbbc"));
		System.out.println(new A471_Encode_String_with_Shortest_Length().encode2("abbbabbbcabbbabbbc"));
	}

}
