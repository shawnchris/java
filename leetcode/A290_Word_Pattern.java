package leetcode;
import java.util.*;

public class A290_Word_Pattern {
    public boolean wordPattern(String pattern, String str) {
        if (pattern == "" && str == "") return true;
        if (pattern == "" || str == "") return false;
        
        int plen = pattern.length();
        String[] split = str.split("\\s+");
        int slen = split.length;
        if (plen != slen) return false;
        
        Map<Character, String> ptos = new HashMap<>();
        Map<String, Character> stop = new HashMap<>();
        
        for (int i = 0; i < plen; i++) {
            char c = pattern.charAt(i);
            String s = split[i];
            if (ptos.containsKey(c) && !ptos.get(c).equals(s))
                return false;
            if (stop.containsKey(s) && stop.get(s) != c)
                return false;
            ptos.put(c, s);
            stop.put(s, c);
        }
        
        return true;
    }
}
