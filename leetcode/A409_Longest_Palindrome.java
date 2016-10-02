package leetcode;
import java.util.*;

public class A409_Longest_Palindrome {
    public int longestPalindrome(String s) {
        int result = 0;
    	if (s == null || s.length() == 0) return result;
        
    	Map<Character, Integer> map = new HashMap<>();
    	for (int i = 0; i < s.length(); i++) {
    		char c = s.charAt(i);
    		if (map.get(c) != null) {
    			map.put(c, map.get(c) + 1);
    		}
    		else {
    			map.put(c, 1);
    		}
    	}
    	
    	boolean countedSingle = false;
    	for (Character c : map.keySet()) {
    		int count = map.get(c);
    		if (count % 2 == 0) {
    			result += count;
    		}
    		else {
    			if (count > 2) {
    				result += count - 1;
    			}
    			if (!countedSingle) {
    				result++;
    				countedSingle = true;
    			}
    		}
    	}
    	
    	return result;
    }
	public static void main(String[] args) {
		A409_Longest_Palindrome lp = new A409_Longest_Palindrome();
		System.out.println(lp.longestPalindrome("abccccdd"));
		System.out.println(lp.longestPalindrome("a"));
		System.out.println(lp.longestPalindrome("ab"));
		System.out.println(lp.longestPalindrome("abc"));
		System.out.println(lp.longestPalindrome("abccccddb"));
		System.out.println(lp.longestPalindrome("ccccc"));
	}

}
