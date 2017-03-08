package leetcode;
import java.util.*;

public class A524_Longest_Word_in_Dictionary_through_Deleting {
    public String findLongestWord(String s, List<String> d) {
        if (s.length() == 0 || d.size() == 0) return "";
        
        Collections.sort(d, (a, b) -> {
           if (a.length() != b.length()) return b.length() - a.length();
           return a.compareTo(b);
        });
        
        for (String p : d) {
            if (s.length() < p.length()) continue;
            if (isSubSeq(s, p)) return p;
        }
        
        return "";
    }
    
    private boolean isSubSeq(String s, String p) {
        int i = 0, j = 0;
        while (i < s.length() && j < p.length()) {
            if (s.charAt(i) == p.charAt(j)) {
                i++; j++;
            }
            else {
                i++;
            }
        }
        return j == p.length();
    }
}
