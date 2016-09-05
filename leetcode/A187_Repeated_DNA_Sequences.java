package leetcode;
import java.util.*;

public class A187_Repeated_DNA_Sequences {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        int len = s.length();
        if (len < 10) return result;
        
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i <= len - 10; i++) {
            String ss = s.substring(i, i + 10);
            Integer count = map.get(ss);
            if (count == null)
                count = 1;
            else
                count += 1;
            map.put(ss, count);
        }
        
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1)
                result.add(entry.getKey());
        }
        
        return result;
    }
}
