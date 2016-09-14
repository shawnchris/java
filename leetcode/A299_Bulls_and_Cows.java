package leetcode;

public class A299_Bulls_and_Cows {
    public String getHint(String secret, String guess) {
        int len = secret.length();
        if (len == 0) return "0A0B";
        
        int[] map = new int[10];
        for (int i = 0; i < len; i++) {
            int j = secret.charAt(i) - '0';
            map[j]++;
        }
        
        int a = 0, b = 0;
        for (int i = 0; i < len; i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';
            if (s == g) {
                if (map[s] > 0) {
                    a++;
                    map[s]--;
                }
                else { // counted more b
                    a++;
                    b--;
                }
            }
            else {
                if (map[g] > 0) {
                    b++;
                    map[g]--;
                }
            }
        }
        
        return a + "A" + b + "B";
    }
}
