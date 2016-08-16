package leetcode;
import java.util.*;

public class A065_Valid_Number {
    public boolean isNumber(String s) {
        if (s == null) return false;
        s = s.trim();
        int len = s.length();
        if (len == 0) return false;
        
        boolean hasDot = false, hasE = false, hasN1 = false, hasN2 = false;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < 10; i++)
            set.add((char)('0' + i));
        
        char c = s.charAt(0);
        if (!set.contains(c) && c != '+' && c != '-' && c != '.')
            return false;
        
        if (set.contains(c)) hasN1 = true;
        if (c == '.') hasDot = true;
        
        for (int i = 1; i < len; i++) {
            c = s.charAt(i);
            if (set.contains(c)){
                if (hasE)
                    hasN2 = true;
                else
                    hasN1 = true;
                continue;
            }
            if (c == '.') {
                if (hasDot || hasE) return false;
                hasDot = true;
                continue;
            }
            if (c == 'e') {
                if (hasE) return false;
                hasE = true;
                if (i + 1 < len && (s.charAt(i + 1) == '+' || s.charAt(i + 1) == '-'))
                    i++;
                continue;
            }
            return false;
        }
        
        if (!(hasN1 || hasN2) && (hasDot || hasE)) return false;
        if (hasE && !(hasN1 && hasN2)) return false;
        
        return true;
    }
}
