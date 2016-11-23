package leetcode;
import java.util.*;

public class A249_Group_Shifted_Strings {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (strings == null || strings.length == 0) return result;
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        
        for (String str : strings) {
            String signature = "";
            for (int i = 0; i < str.length(); i++) {
                int delta = str.charAt(i) - str.charAt(0);
                if (delta < 0) delta += 26;
                signature += delta + "_";
            }
            List<String> list = map.get(signature);
            if (list == null) list = new ArrayList<String>();
            list.add(str);
            map.put(signature, list);
        }
        
        for (String key : map.keySet()) {
            result.add(map.get(key));
        }
        
        return result;
    }
}
