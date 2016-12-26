package leetcode;
import java.util.*;

class LRS {
    // return the longest common prefix of s and t
    public static String lcp(String s, String t) {
        int n = Math.min(s.length(), t.length());
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i))
                return s.substring(0, i);
        }
        return s.substring(0, n);
    }

    // return the longest repeated string in s
    public static String lrs(String s) {

        // form the N suffixes
        int n  = s.length();
        String[] suffixes = new String[n];
        for (int i = 0; i < n; i++) {
            suffixes[i] = s.substring(i, n);
        }

        // sort them
        Arrays.sort(suffixes);

        // find longest repeated substring by comparing adjacent sorted suffixes
        String lrs = "";
        for (int i = 0; i < n-1; i++) {
            String x = lcp(suffixes[i], suffixes[i+1]);
            if (x.length() > lrs.length())
                lrs = x;
        }
        return lrs;
    }
}

public class A471_Encode_String_with_Shortest_Length {
	Map<String, String> encodeMap;


    public String encode(String s) {
        encodeMap = new HashMap<>();
        return encodeImpl(s);
    }

    private String encodeImpl(String s) {
        if (s.isEmpty()) {
            return "";
        }
        if (encodeMap.containsKey(s)) {
            return encodeMap.get(s);
        }
        String answer = s;
        int n = s.length();
        for (int prefixLen = 1; prefixLen < n; prefixLen++) {
            String prefix = s.substring(0, prefixLen);
            String suffix = s.substring(prefixLen);
            String encodePrefix = encodeImpl(prefix);
            String encodeSuffix = encodeImpl(suffix);
            String result = encodePrefix + encodeSuffix;

            if (result.length() < answer.length()) {
                answer = result;
            }
            String realPrefix = encodePrefix;
            int count = 1;
            while (suffix.startsWith(prefix)) {
                realPrefix += encodePrefix;
                suffix = suffix.substring(prefixLen);
                count++;
                encodeSuffix = encodeImpl(suffix);
                result = realPrefix + encodeSuffix;
                if (result.length() < answer.length()) {
                    answer = result;
                }
                result = count + "[" + encodePrefix + "]" + encodeSuffix;
                if (result.length() < answer.length()) {
                    answer = result;
                }
            }
        }
        encodeMap.put(s, answer);
        return answer;
    }
    
    public String encode2(String s) {
    	int l = s.length();
        String[][] DP = new String[l][l + 1];
        for (int i = 0; i < l; i++) {
        	for (int j = 0; j < l + 1; j++) {
        		DP[i][j] = "";
        	}
        }
        mDP(DP, s, 0, l);
        return DP[0][l];
    }
    
    private void mDP(String[][] DP, String s,int i, int j){
        DP[i][j]=s.substring(i,j);
        //first loop
        for (int k = 1; k < j; k++) {
            if (j % k == 0) {
                boolean fine = true;
                for(int kk = 1; kk < j / k ; kk++){
                    if(!(s.substring(i, k) == s.substring(i + kk * k, k))){
                        fine=false;
                        break;
                    }
                }
                if (fine) {
                    if (DP[i][k].length() == 0) mDP(DP, s, i, k);
                    for (int kk = 1; kk < j / k; kk++) DP[i + kk*k][k] = DP[i][k];
                    if (DP[i][j].length() > String.valueOf(j / k).length() + 2 + DP[i][k].length()) {
                        DP[i][j] = String.valueOf(j / k) + "[" + DP[i][k] + "]";
                    }
                }
            }
        }
        //second loop
        for(int k = 1; k < j; k++) {
            if (DP[i][k].length() == 0) mDP(DP, s, i, k);
            if (DP[i+k][j-k].length() == 0) mDP(DP,s,i+k,j-k);
            if (DP[i][j].length() > DP[i][k].length() + DP[i+k][j-k].length()) {
                DP[i][j] = DP[i][k] + DP[i+k][j-k];
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new A471_Encode_String_with_Shortest_Length().encode("abbbabbbcabbbabbbc"));
        System.out.println(new A471_Encode_String_with_Shortest_Length().encode2("abbbabbbcabbbabbbc"));
    }

}
