package leetcode;
import java.util.*;

public class A097_Interleaving_String {
    private boolean helper(String s1, String s2, String s3, int p1, int p2, HashSet<String> cache) {
        if (p1 + p2 == s3.length())
            return true;
        if (cache.contains(p1 + ":" + p2))
            return false;
        // no need to store actual result.
        // if we found the path, we have already terminated.
        cache.add(p1 + ":" + p2);
        boolean match1 = p1 < s1.length() && s3.charAt(p1 + p2) == s1.charAt(p1);
        boolean match2 = p2 < s2.length() && s3.charAt(p1 + p2) == s2.charAt(p2);
        if (match1 && match2)
            return helper(s1, s2, s3, p1 + 1, p2, cache) ||
                   helper(s1, s2, s3, p1, p2 + 1, cache);
        else if (match1)
            return helper(s1, s2, s3, p1 + 1, p2, cache);
        else if (match2)
            return helper(s1, s2, s3, p1, p2 + 1, cache);
        else
            return false;
    }
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        
        HashSet<String> cache = new HashSet<>();
        
        return helper(s1, s2, s3, 0, 0, cache);
    }
}
