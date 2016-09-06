package leetcode;
import java.util.*;

public class A205_Isomorphic_Strings {
    static HashMap<Character, Character> from = new HashMap<Character, Character>();
	static HashMap<Character, Character> to = new HashMap<Character, Character>();
	
	public boolean isIsomorphic(String s, String t) {
        int len = s.length();
        if (len <= 0) return true;
        
        from.clear();
        to.clear();
        
        for (int i = 0; i < len; i++) {
        	char c1 = s.charAt(i);
        	char c2 = t.charAt(i);
        	if (from.containsKey(c1)) {
        		if (from.get(c1) != c2)
        			return false;
        	}
        	if (to.containsKey(c2)) {
        		if (to.get(c2) != c1)
        			return false;
        	}
    		from.put(c1, c2);
    		to.put(c2, c1);
        }
        
        return true;
    }
}
