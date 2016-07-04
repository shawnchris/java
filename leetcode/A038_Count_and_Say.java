package leetcode;

public class A038_Count_and_Say {
    public String countAndSay(int n) {
        String r = "1";
        int l = 1;
        while (l < n) {
            String t = "";
            for (int i = 0; i < r.length();) {
                char c = r.charAt(i);
                int j = i + 1;
                for (; j < r.length(); j++)
                    if (r.charAt(j) != c)
                        break;
                t = t + (j - i) + (int)(c - '0');
                i = j;
            }
            r = t;
            l++;
        }
        
        return r;
    }
}
