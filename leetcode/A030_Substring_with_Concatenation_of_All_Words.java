package leetcode;
import java.util.*;

public class A030_Substring_with_Concatenation_of_All_Words {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<Integer>();
        if (s == null) return null;
        int slen = s.length();
        if (words == null) return null;
        int wlen = words.length;
        if (wlen == 0 && slen == 0) {
            result.add(0);
            return result;
        }
        if (wlen == 0 || slen == 0) {
            return result;
        }
        
        Map<String, Integer> m = new HashMap<String, Integer>();
        for (int k = 0; k < wlen; k++) {
            Integer v = m.get(words[k]);
            if (v == null) v = 0;
            m.put(words[k], ++v);
        }
        int tlen = words[0].length();
        for (int i = 0; i <= slen - (tlen  * wlen); i++) {
            Map<String, Integer> map = new HashMap<String, Integer>(m);
            int j = i;
            for (int k = 0; k < wlen; k++) {
                String str = s.substring(j, j + tlen);
                if (!map.containsKey(str)) {
                    break;
                }
                else {
                    Integer v = map.get(str);
                    if (v == 1)
                        map.remove(str);
                    else
                        map.put(str, --v);
                    j = j + tlen;
                }
            }
            
            if (map.size() == 0) { // find a result;
                result.add(i);
            }
        }
        
        return result;
    }
}
