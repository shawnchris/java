package leetcode;
import java.util.*;

public class A003_Longest_Substring_Without_Repeating_Characters {
    public int lengthOfLongestSubstring(String s) {
        if (s == null) return -1;
        int len = s.length();
        if (len == 0) return 0;
        
        Set<Character> set = new HashSet<>();
        int max = Integer.MIN_VALUE, j = 0;
        for (int i = 0; i < len; i++) {
            while (j < len && !set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                j++;
                max = Math.max(max, j - i);
            }
            
            if (j >= len)
                break;
            
            set.remove(s.charAt(i));    
        }
        
        return max;
    }
}
