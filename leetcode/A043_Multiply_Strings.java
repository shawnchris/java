package leetcode;

public class A043_Multiply_Strings {
	public String multiply(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();
        if (len1 == 0 || len2 == 0) return "0";
        
        int[] res = new int[len1 + len2];
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int t = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                t += res[i + j + 1];
                res[i + j + 1] = t % 10;
                res[i + j] = res[i + j] + t / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++)
            if(sb.length() == 0 && res[i] == 0)
                continue;
            else
                sb.append(res[i]);
        
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
