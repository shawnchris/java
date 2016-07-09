package interview.uber;
import java.util.*;

public class A140_Word_BreakII {
    public List<String> wordBreak(String s, Set<String> wordDict) {
        Map<Integer, List<String>> dp = new HashMap<>();
        int maxLength = 0;
        for (String ss: wordDict) {
            maxLength = Math.max(maxLength, ss.length());
        }
        return helper(s, wordDict, dp, 0, maxLength);
    }
    
    private List<String> helper(String s, Set<String> dict, Map<Integer, List<String>> dp, int start, int max) {
        List<String> words = new ArrayList<>();
        
        //Base case
        if (start == s.length()) {
            words.add("");
            return words;
        }
        
        //DFS
        for (int end = start + 1; end <= s.length() && end <= start + max; end++) {
            String temp = s.substring(start, end);
            if (dict.contains(temp)) {
                List<String> list = null;
                if (dp.containsKey(end)) {
                    list = dp.get(end);
                }
                else {
                    list = helper(s, dict, dp, end, max);
                }
                for (String ss: list) {
                    words.add(temp + (ss.equals("") ? "" : " ") + ss);
                }
            }
        }
        
        dp.put(start, words);
        
        return words;
    }
}
