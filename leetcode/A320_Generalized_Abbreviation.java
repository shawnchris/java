package leetcode;
import java.util.*;

public class A320_Generalized_Abbreviation {
    public static List<String> generateAbbreviations(String word) {
        List<String> result = new ArrayList<String>();
        helper(word, 0, result);
        return result;
    }
    
    private static void helper(String word, int start, List<String> result) {
        result.add(word);
        for (int i = start; i < word.length(); i++) {
            int j = i - 1, digit = 1;
            if (j >= 0 && Character.isDigit(word.charAt(j))) {
                digit += Integer.valueOf(word.substring(j, j + 1));
                j--;
            }
            j++;
            String newWord = "";
            if (j > 0) newWord = word.substring(0, j);
            newWord += digit + word.substring(i + 1);
            helper(newWord, i - (word.length() - newWord.length()) + 1, result);
        }
    }
	public static void main(String[] args) {
		System.out.println(generateAbbreviations("word"));

	}

}
