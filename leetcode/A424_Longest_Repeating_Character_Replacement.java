package leetcode;

public class A424_Longest_Repeating_Character_Replacement {
    public static int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        int max = 0, result = 0, j = -1;
        int[] letter = new int[26];
        
        for (int i = 0; i < s.length(); i++) {
        	do {
        		j++;
        		if (j < s.length()) {
        			max = Math.max(max, ++letter[s.charAt(j) - 'A']);
        		}
        	}
            while (j < s.length() && j - i + 1 - max <= k);
        	
            result = Math.max(result, j - i);
            letter[s.charAt(i) - 'A']--;
        }
        
        return result;
    }
    
	public static void main(String[] args) {
		System.out.println(characterReplacement("AABABBA", 1));
		System.out.println(characterReplacement("AAAA", 0));
	}

}
