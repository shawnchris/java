package leetcode;
import java.util.*;

public class A527_Word_Abbreviation {
    public List<String> wordsAbbreviation(List<String> dict) {
        Map<String, String> wordToAbbr = new HashMap<>();
        Map<Integer, List<String>> groups = new HashMap<>();
        
        // Try to group words by their length. Because no point to compare words with different length.
        // Also no point to look at words with length < 4.
        for (String word : dict) {
            int len = word.length();
            if (len < 4) {
                wordToAbbr.put(word, word);
            }
            else {
                List<String> g = groups.getOrDefault(len, new ArrayList<String>());
                g.add(word);
                groups.put(len, g);
            }
        }
        
        // For each group of words with same length, generate a result HashMap.
        for (int len : groups.keySet()) {
            Map<String, String> res = getAbbr(groups.get(len));
            for (String word : res.keySet()) {
                wordToAbbr.put(word, res.get(word));
            }
        }
        
        // Generate the result list
        List<String> result = new ArrayList<>();
        for (String word : dict) {
            result.add(wordToAbbr.get(word));
        }
        
        return result;
    }
    
    private Map<String, String> getAbbr(List<String> words) {
        Map<String, String> res = new HashMap<>();
        int len = words.get(0).length();
        
        // Try to abbreviate a word from index 1 to len - 2 
        for (int i = 1; i < len - 2; i++) {
            Map<String, String> abbrToWord = new HashMap<>();
            for (String s : words) {
                if (res.containsKey(s)) continue;
                // Generate the current abbreviation
                String abbr = s.substring(0, i) + (len - 1 - i) + s.charAt(len - 1);
                // Tick: use reversed abbreviation to word map to check if there is any duplicated abbreviation
                if (!abbrToWord.containsKey(abbr)) {
                    abbrToWord.put(abbr, s);
                }
                else {
                    abbrToWord.put(abbr, "");
                }
            }

            // Add unique abbreviations find during this round to result HashMap
            for (String abbr : abbrToWord.keySet()) {
                String s = abbrToWord.get(abbr);
                // Not a unique abbreviation
                if (s.length() == 0) continue;
                res.put(s, abbr);
            }
        }
        
        // Add all words that can't be shortened.
        for (String s : words) {
            if (!res.containsKey(s)) {
                res.put(s, s);
            }
        }
        
        return res;
    }
}
