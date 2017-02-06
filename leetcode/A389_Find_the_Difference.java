package leetcode;
import java.util.*;

public class A389_Find_the_Difference {
	// Bit manipulation
    public char findTheDifference(String s, String t) {
        int len = s.length();
        char c = t.charAt(len);
        
        for (int i = 0; i < len; i++) {
        	c ^= s.charAt(i);
        	c ^= t.charAt(i);
        }
        
        return c;
    }
    
    // HashSet
    public char findTheDifference1(String s, String t) {
    	Set<Character> set = new HashSet<>();
        int len = s.length();
        set.add(t.charAt(len));
        
        for (int i = 0; i < len; i++) {
        	if (!set.add(s.charAt(i))) set.remove(s.charAt(i));
        	if (!set.add(t.charAt(i))) set.remove(t.charAt(i));
        }
        
        return set.iterator().next();
    }
	public static void main(String[] args) {
		String s = "abcd";
		String t = "abcde";
		A389_Find_the_Difference fd = new A389_Find_the_Difference();
		System.out.println(fd.findTheDifference(s, t));
		System.out.println(fd.findTheDifference1(s, t));

	}

}
