package leetcode;
/*
 * Given two strings S and T, determine if they are both one edit distance apart.
 * Hint:
 * 1. If | n – m | is greater than 1, we know immediately both are not one-edit
 *    distance apart.
 * 2. It might help if you consider these cases separately, m == n and m != n.
 * 3. Assume that m is always <= n, which greatly simplifies the conditional 
 *    statements. If m > n, we could just simply swap S and T.
 * 4. If m == n, it becomes finding if there is exactly one modified operation.
 *    If m != n, you do not have to consider the delete operation. Just consider
 *    the insert operation in T.
 */
public class L161_One_Edit_Distance {
	public boolean isOneEditDistance(String s, String t) {
		if (s.length() > t.length())
			return isOneEditDistance(t, s);
		
		int diff = (t.length() - s.length());
		if (diff > 1) return false;
		
		int i = 0, shift = 1;
		while (i < s.length() && s.charAt(i) == t.charAt(i))
			i++;
		
		if (diff == 0) {
			if (i == s.length()) return true;
			shift = 0;
			i++;
		}
		
		while (i < s.length() && s.charAt(i) == t.charAt(i + shift))
			i++;
		
		return i == s.length();
	}
	public static void main(String[] args) {
		L161_One_Edit_Distance oed = new L161_One_Edit_Distance();
		
		System.out.println(oed.isOneEditDistance("", ""));
		System.out.println(oed.isOneEditDistance("a", ""));
		System.out.println(oed.isOneEditDistance("", "b"));
		System.out.println(oed.isOneEditDistance("a", "b"));
		System.out.println(oed.isOneEditDistance("a", "aa"));
		System.out.println(oed.isOneEditDistance("aa", "a"));
		System.out.println(oed.isOneEditDistance("aa", "ab"));
		System.out.println(oed.isOneEditDistance("aaaaaccccc", "aaaaabcccc"));
		System.out.println(oed.isOneEditDistance("aaaaaccccc", "aaaaabcccd"));
		System.out.println(oed.isOneEditDistance("aaaaaccccc", "aaaaacccccd"));
		System.out.println(oed.isOneEditDistance("aaaaaccccc", "aaaaacccccde"));
	}

}
