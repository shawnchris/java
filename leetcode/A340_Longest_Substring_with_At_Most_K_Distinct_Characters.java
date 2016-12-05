package leetcode;
import java.util.*;

public class A340_Longest_Substring_with_At_Most_K_Distinct_Characters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) return 0;
    	
    	int max = 0, j = 0;
    	Map<Character, Integer> map = new HashMap<>();
    	
    	for (int i = 0; i < s.length(); i++) {
    		while (j < s.length()) {
    			if (map.keySet().size() > k) break;
    			char ch = s.charAt(j);
    			int count = map.getOrDefault(ch, 0) + 1;
    			map.put(ch, count);
    			j++;
    			if (map.keySet().size() <= k)
    				max = Math.max(max, j - i);
    		}
    		
    		if (j >= s.length()) break;
    		
    		char ch = s.charAt(i);
    		int count = map.get(ch) - 1;
    		if (count == 0) {
    			map.remove(ch);
    		}
    		else {
    			map.put(ch, count);
    		}
    	}
        return max;
    }
}
