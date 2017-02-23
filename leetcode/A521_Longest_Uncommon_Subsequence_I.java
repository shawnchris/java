package leetcode;

public class A521_Longest_Uncommon_Subsequence_I {
	public int findLUSlength(String a, String b) {
	    return a.equals(b) ? -1 : Math.max(a.length(), b.length());
	}
}
