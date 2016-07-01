package leetcode;

public class A014_Longest_Common_Prefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null) return null;
        int len = strs.length;
        if (len == 0) return "";
        if (len == 1) return strs[0];
        
        int min = strs[0].length();
        for (int i = 1; i < len; i++)
            min = Math.min(min, strs[i].length());
        
        for (int i = 0; i < min; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < len; j++) {
                if (strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        
        return strs[0].substring(0, min);
    }
}
