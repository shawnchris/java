package leetcode;
import java.util.*;

public class A383_Ransom_Note {
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        
        for (int i = 0; i < magazine.length(); i++) {
        	char c = magazine.charAt(i);
        	if (map.containsKey(c)) {
        		map.put(c, map.get(c) + 1);
        	}
        	else {
        		map.put(c, 1);
        	}
        }
        
        for (int i = 0; i < ransomNote.length(); i++) {
        	char c = ransomNote.charAt(i);
        	if (!map.containsKey(c)) return false;
        	int t = map.get(c);
        	if (t <= 1) {
        		map.remove(c);
        	}
        	else {
        		map.put(c, t - 1);
        	}
        }
        
        return true;
    }
    
	public static void main(String[] args) {
		A383_Ransom_Note rn =  new A383_Ransom_Note();
		System.out.println(rn.canConstruct("a", "b"));
		System.out.println(rn.canConstruct("aa", "ab"));
		System.out.println(rn.canConstruct("aa", "aab"));
	}

}
