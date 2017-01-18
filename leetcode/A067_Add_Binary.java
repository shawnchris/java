package leetcode;

public class A067_Add_Binary {
    public String addBinary(String a, String b) {
    	if (a == null || a.length() == 0) return b;
        if (b == null || b.length() == 0) return a;
        
        int i = a.length() - 1, j = b.length() - 1, sum = 0;
        StringBuilder sb = new StringBuilder();
        
        while (i >= 0 || j >= 0 || sum > 0) {
            if (i >= 0) {
                sum += Integer.parseInt(a.substring(i, i + 1));
                i--;
            }
            if (j >= 0) {
                sum += Integer.parseInt(b.substring(j, j + 1));
                j--;
            }
            sb.append(sum % 2);
            sum = sum / 2;
        }
        
        return sb.reverse().toString();
    }
}
