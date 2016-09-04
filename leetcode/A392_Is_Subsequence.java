package leetcode;

public class A392_Is_Subsequence {
	public boolean isSubsequence(String s, String t) {
		if (s == null || t == null)
			return false;
		if (s.length() > t.length())
			return false;

		int i = 0, j = 0;
		for (; i < s.length(); i++) {
			while (j < t.length() && s.charAt(i) != t.charAt(j)) {
				j++;
			}
			j++;
		}

		if (i == s.length() && j <= t.length())
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		A392_Is_Subsequence is = new A392_Is_Subsequence();
		System.out.println(is.isSubsequence("abc", "ahbgdc"));
		System.out.println(is.isSubsequence("axc", "ahbgdc"));
		System.out.println(is.isSubsequence("abc", "ahbgdcxxxx"));
	}

}
