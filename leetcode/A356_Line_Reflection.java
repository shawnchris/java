package leetcode;
import java.util.*;

public class A356_Line_Reflection {
    public boolean isReflected(int[][] points) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        HashSet<String> set = new HashSet<>();
        for(int[] p : points){
            max = Math.max(max, p[0]);
            min = Math.min(min, p[0]);
            String str = p[0] + "_" + p[1];
            set.add(str);
        }
        
        int sum = max + min;
        for(int[] p:points){
            String str = (sum - p[0]) + "_" + p[1];
            if(!set.contains(str))
                return false;
        }
        return true;
    }
}
