package leetcode;

public class A058_Length_of_Last_Word {
    public int lengthOfLastWord(String s) {
        if (s == null) return 0;
        int len = s.length();
        if (len == 0) return 0;
        
        int i = len - 1;
        while (i >= 0 && s.charAt(i) == ' ')
            i--;
        
        if (i < 0) return 0;
        
        int j = i - 1;
        while (j >= 0 && s.charAt(j) != ' ')
            j--;
        
        return i - j;
    }
}
