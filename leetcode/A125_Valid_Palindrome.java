package leetcode;
import java.util.*;

public class A125_Valid_Palindrome {
    public boolean isPalindrome(String s) {
        if (s == null) return false;
        s = s.toLowerCase();
        int len = s.length();
        if (len <= 1) return true;
        
        int p1 = 0, p2 = len-1;
        Set<Character> dict = new HashSet<Character>();
        for (int i = 0; i < 26; i++)
            dict.add((char)('a' + i));
        for (int i = 0; i < 10; i++)
            dict.add((char)('0' + i));
        while (p1 < p2) {
            while (p1 < p2 && !dict.contains(s.charAt(p1)))
                p1++;
            while (p1 < p2 && !dict.contains(s.charAt(p2)))
                p2--;
            if (s.charAt(p1) != s.charAt(p2))
                return false;
            p1++;
            p2--;
        }
        return true;
    }

}
