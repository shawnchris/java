package leetcode;

public class A459_Repeated_Substring_Pattern {
    public static boolean repeatedSubstringPattern(String str) {
        if (str == null || str.length() == 0 || str.length() == 1)
        	return true;
        
        for (int end = 1; end <= str.length() / 2; end++) {
        	if ((str.length() - end) % end != 0) continue;
        	int i = 0;
        	boolean match = true;
        	for (int j = end; j < str.length(); j++) {
        		if (str.charAt(i) != str.charAt(j)) {
        			match = false;
        			break;
        		}
        		i++;
        		if (i >= end) i = 0;
        	}
        	if (match) return true;
        }
        
        return false;
    }
	public static void main(String[] args) {
		System.out.println(repeatedSubstringPattern("abab"));
		System.out.println(repeatedSubstringPattern("aba"));
		System.out.println(repeatedSubstringPattern("abcabcabcabc"));

	}

}
