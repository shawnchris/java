package leetcode;

public class A171_Excel_Sheet_Column_Number {
    public int titleToNumber(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            sum = sum * 26 + (int)(c - 'A' + 1);
        }
        
        return sum;
    }
}
