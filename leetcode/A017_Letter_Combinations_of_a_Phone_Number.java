package leetcode;
import java.util.*;

public class A017_Letter_Combinations_of_a_Phone_Number {
    private static String[] dict = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;
        
        helper(result, digits.toCharArray(), new char[digits.length()], 0);
        return result;
    }
    
    private void helper(List<String> result, char[] digits, char[] current, int index) {
        if (index == digits.length) {
            result.add(new String(current));
            return;
        }
        
        String word = dict[digits[index] - '0'];
        for (int i = 0; i < word.length(); i++) {
            current[index] = word.charAt(i);
            helper(result, digits, current, index + 1);
        }
    }
    
}
