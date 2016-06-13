package leetcode;

public class A006_ZigZag_Conversion {
    public String convert(String s, int numRows) {
        if (s == null) return null;
        if (numRows == 1) return s;
        int len = s.length();
        if (len < 2) return s;
        
        String[] a = new String[numRows];
        for (int i = 0; i < numRows; i++)
            a[i] = "";
            
        int idx = -1, d = 1;
        for (int i = 0; i < len; i++) {
            idx += d;
            if (d == 1 && idx == numRows) {
                idx -= 2;
                d = -1;
            }
            if (d == -1 && idx == -1) {
                idx += 2;
                d = 1;
            }
            a[idx] += s.substring(i, i+1);
        }
        
        String result = "";
        for (int i = 0; i < numRows; i++)
            result += a[i];
            
        return result;
        
    }
}
