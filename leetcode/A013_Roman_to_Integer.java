package leetcode;
import java.util.*;

public class A013_Roman_to_Integer {
    Map<Character, Integer> map = new HashMap<>();
    public int romanToInt(String s) {
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        return helper(0, s);
    }
    public int helper(int i, String s) {
        if(i == s.length()) return 0;
        if(i == s.length() - 1) return map.get(s.charAt(i));
        if(map.get(s.charAt(i)) >= map.get(s.charAt(i + 1)))
            return map.get(s.charAt(i)) + helper(i + 1, s);
        else
            return map.get(s.charAt(i + 1)) - map.get(s.charAt(i)) + helper(i + 2, s);   
    }
}
