package leetcode;
/*
 * Problem:
 * Given a string, determine if a permutation of the string could form a palindrome.
 * For example, "code" -> False, "aab" -> True, "carerac" -> True.
 */
public class L266_Palindrome_Permutation {
	public boolean canPermutePalindrome(String s) {
		if (s == null || s.length() == 0) return true;
		s = s.toLowerCase();
		int[] counter = new int[26];
		
		for (int i = 0; i < s.length(); i++) {
			int j = s.charAt(i) - 'a';
			counter[j]++;
		}
		
		int odd = 0;
		for (int i = 0; i < 26; i++) {
			if (counter[i] % 2 != 0) {
				odd++;
			}
		}
		
		return odd < 2;
	}
	public static void main(String[] args) {
		L266_Palindrome_Permutation pp = new L266_Palindrome_Permutation();
		System.out.println(pp.canPermutePalindrome("code"));
		System.out.println(pp.canPermutePalindrome("aab"));
		System.out.println(pp.canPermutePalindrome("carerac"));
	}

}
