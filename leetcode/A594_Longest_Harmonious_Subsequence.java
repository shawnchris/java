package leetcode;

import java.util.HashMap;
import java.util.Map;

public class A594_Longest_Harmonious_Subsequence {
    public int findLHS(int[] nums) {
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : map.keySet()) {
            if (map.containsKey(num + 1)) {
                max = Math.max(max, map.get(num) + map.get(num + 1));
            }
        }

        return max;
    }
}
