package leetcode;

public class A067_Add_Binary {
    public String addBinary(String a, String b) {
    	if (a == null || a.length() == 0) return b;
        if (b == null || b.length() == 0) return a;
        
        int i = a.length() - 1, j = b.length() - 1, sum = 0;
        StringBuilder sb = new StringBuilder();
        
        while (i >= 0 || j >= 0 || sum > 0) {
            if (i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }
            sb.append(sum % 2);
            sum = sum / 2;
        }
        
        return sb.reverse().toString();
    }
}
