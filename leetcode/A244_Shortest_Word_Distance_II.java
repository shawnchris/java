package leetcode;
import java.util.*;

public class A244_Shortest_Word_Distance_II {
    Map<String, List<Integer>> map = null;
    
    public A244_Shortest_Word_Distance_II(String[] words) {
        map = new HashMap<String, List<Integer>>();
        
        for (int i = 0; i < words.length; i++) {
            List<Integer> list = map.get(words[i]);
            if (list == null) {
                list = new ArrayList<Integer>();
            }
            list.add(i);
            map.put(words[i], list);
        }
        
    }

    public int shortest(String word1, String word2) {
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        int index1 = 0, index2 = 0, min = Integer.MAX_VALUE;
        int i = 0, j = 0;
        while (i < list1.size() && j < list2.size()) {
            index1 = list1.get(i);
            index2 = list2.get(j);
            min = Math.min(min, Math.abs(index1 - index2));
            if (min == 1) return 1;
            if (index1 < index2) {
                i++;
            }
            else {
                j++;
            }
        }
        return min;
    }
}
