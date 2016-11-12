package leetcode;
import java.util.*;

public class A436_Find_Right_Interval {
    public int[] findRightInterval(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return new int[0];
        int len = intervals.length;
        int[] result = new int[len];
        
        TreeMap<Integer, Integer> map = new TreeMap<>();
        
        for (int i = 0; i < len; i++) {
            map.put(intervals[i].start, i);
        }
        
        for (int i = 0; i < len; i++) {
            Integer rightKey = map.ceilingKey(intervals[i].end);
            if (rightKey == null) {
                result[i] = -1;
            }
            else {
                result[i] = map.get(rightKey);
            }
        }
        
        return result;
    }
}
