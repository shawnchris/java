package leetcode;

import java.util.*;

public class A395_Longest_Substring_with_At_Least_K_Repeating_Characters {
	public int longestSubstring(String s, int k) {
		if (s == null || k > s.length())
			return 0;
		if (k == 1)
			return s.length();

		char[] ss = s.toCharArray();

		return helper(ss, 0, s.length(), k);
	}

	private int helper(char[] ss, int start, int end, int k) {
		// count number of each char
		int[] count = new int[26];
		for (int i = start; i < end; i++) {
			count[ss[i] - 'a']++;
		}

		// find which char is less than k
		boolean hasLonger = false;
		Set<Character> shorter = new HashSet<>();
		for (int i = 0; i < 26; i++) {
			if (count[i] >= k) {
				hasLonger = true;
			} else if (count[i] > 0) {
				shorter.add((char) ('a' + i));
			}
		}

		// base cases
		// no solution
		if (!hasLonger)
			return 0;
		// no less than k
		if (shorter.size() == 0)
			return end - start;

		// recursively search sub string
		int max = 0, i = start, j = i;
		while (i < end) {
			while (i < end && shorter.contains(ss[i])) {
				i++;
			}
			j = i + 1;
			while (j < end && !shorter.contains(ss[j])) {
				j++;
			}
			if (i < end && j - i >= k) {
				max = Math.max(max, helper(ss, i, j, k));
			}
			i = j;
		}

		return max;
	}

	public static void main(String[] args) {
		A395_Longest_Substring_with_At_Least_K_Repeating_Characters ls = new A395_Longest_Substring_with_At_Least_K_Repeating_Characters();
		System.out.println(ls.longestSubstring("aaabb", 3));
		System.out.println(ls.longestSubstring("ababbc", 2));
	}

}
