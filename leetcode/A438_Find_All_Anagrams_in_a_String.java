package leetcode;
import java.util.*;

public class A438_Find_All_Anagrams_in_a_String {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0) return result;
        if (s.length() < p.length()) return result;
        
        int[] count = new int[26];
        for (int i = 0; i < p.length(); i++) {
            count[p.charAt(i) - 'a']++;
            count[s.charAt(i) - 'a']--;
        }
        if (allZero(count)) result.add(0);
        
        for (int i = p.length(); i < s.length(); i++) {
            count[s.charAt(i) - 'a']--;
            count[s.charAt(i - p.length()) - 'a']++;
            if (allZero(count)) result.add(i - p.length() + 1);
        }
        
        return result;
    }
    
    private boolean allZero(int[] count) {
        for (int i : count) {
            if (i != 0) return false;
        }
        return true;
    }
}
