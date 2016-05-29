package leetcode;
import java.util.*;

public class A017_Letter_Combinations_of_a_Phone_Number {
    private void helper(String digits, String current, int level, List<String> result, String[] map) {
        if (level == digits.length()) {
            result.add(current);
            return;
        }
        
        int c = digits.charAt(level) - '0';
        for (int i = 0; i < map[c].length(); i++) {
            helper(digits, current + map[c].substring(i, i + 1), level + 1, result, map);
        }
    }
    
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<String>();
        if (digits==null) return null;
        int len = digits.length();
        if (len==0) return result;
        
		String[] map = new String[]
				{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		
		helper(digits, "", 0, result, map);
		
		return result;
    }
}
