package leetcode;
import java.util.*;

public class A266_Palindrome_Permutation {
    public boolean canPermutePalindrome(String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                set.remove(c);
            }
            else {
                set.add(c);
            }
        }
        return set.size() <= 1;
    }
}
