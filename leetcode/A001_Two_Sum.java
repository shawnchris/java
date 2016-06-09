package leetcode;
import java.util.*;

public class A001_Two_Sum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]) + 1;
                result[1] = i + 1;
                break;
            }
            else {
                map.put(nums[i], i);
            }
        }
        return result;
    }
}
