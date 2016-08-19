package leetcode;
import java.util.*;

public class A076_Minimum_Window_Substring {
    public String minWindow(String source, String target) {
        if (source == null || source.length() == 0) return "";
        
        Map<Character, Integer> map = new HashMap<>();
        
        for (int i = 0; i < target.length(); i++) {
            int count = map.getOrDefault(target.charAt(i), 0);
            map.put(target.charAt(i), count + 1);
        }
        
        int j = 0, min = Integer.MAX_VALUE, left = target.length();
        String result = "";
        for (int i = 0; i < source.length(); i++) {
            while (j < source.length() && left > 0) {
                if (map.containsKey(source.charAt(j))) {
                    int count = map.get(source.charAt(j)) - 1;
                    if (count >= 0) left--;
                    map.put(source.charAt(j), count);
                }
                j++;
            }
            
            if (left == 0) {
                if (j - i < min) {
                    min = j - i;
                    result = source.substring(i, j);
                }
            }
            
            if (map.containsKey(source.charAt(i))) {
                int count = map.get(source.charAt(i)) + 1;
                if (count > 0) left++;
                map.put(source.charAt(i), count);
            }
        }
        
        return result;
    }
}
