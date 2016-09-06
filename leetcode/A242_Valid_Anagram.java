package leetcode;
import java.util.*;

public class A242_Valid_Anagram {
    public boolean isAnagram(String s, String t) {
        char[] c1 = s.toCharArray();
        char[] c2 = t.toCharArray();
        int len1 = c1.length, len2 = c2.length;
        if (len1 == 0 && len2 == 0)
            return true;
        if (len1 != len2)
            return false;
        Arrays.sort(c1);
        Arrays.sort(c2);
        
        
        for(int i=0; i < len1; i++)
            if (c1[i] != c2[i])
                return false;
        return true;
    }
}
