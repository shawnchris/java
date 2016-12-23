package leetcode;
import java.util.*;

public class A467_Unique_Substrings_in_Wraparound_String {
	public static int findSubstringInWraproundString(String p) {
        int result = 0;
        int[] count = new int[26];
        for (int i = 0; i < p.length(); i++) {
        	if (count[p.charAt(i) - 'a'] == 0) {
        		result++;
        		count[p.charAt(i) - 'a']++;
        	}
        }
        
        Set<String> set = new HashSet<String>();
        int i = 0, j = 0;
        while (i < p.length()) {
        	j = i + 1;
        	while (j < p.length() && (p.charAt(j) - p.charAt(j - 1) == 1 
        			|| p.charAt(j - 1) - p.charAt(j) == 25)) {
        		j++;
        	}
        	if (j - i > 1) {
        		for (int k = 2; k <= j - i; k++) {
        			for (int q = i; q <= j - k; q++) {
        				String s = p.substring(q, q + k);
        				if (set.contains(s)) continue;
        				set.add(s);
        				result++;
        			}
        		}
        	}
        	
        	i = j;
        }
        
        return result;
    }
	public static void main(String[] args) {
		System.out.println(findSubstringInWraproundString("a"));
		System.out.println(findSubstringInWraproundString("cac"));
		System.out.println(findSubstringInWraproundString("zab"));
		System.out.println(findSubstringInWraproundString("abaab")); // 3
		System.out.println(findSubstringInWraproundString("defkefg")); // 10
		System.out.println(findSubstringInWraproundString("abcdefghijklmnopqrstuvwxyz")); // 3
	}

}
