package leetcode;
import java.util.*;

public class A049_Group_Anagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<List<String>>();
        if(strs == null || strs.length == 0) return res;
        Map<String, List<String>> map = new HashMap<>();
        Arrays.sort(strs);
        
        for(String s: strs) {
            char[] temp = s.toCharArray();
            Arrays.sort(temp); // Use sorted string as the key!
            String key = new String(temp);
            List<String> value = map.get(key);
            if(value == null)
                value = new ArrayList<String>();
            value.add(s);
            map.put(key, value);
        }
        
        for(String key: map.keySet()) {
            List<String> value = map.get(key);
            res.add(value);
        }
        
        return res;
    }
}
