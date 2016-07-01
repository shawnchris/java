package leetcode;
import java.util.*;

public class A016_3Sum_Closest {
	public int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        if (len == 3) return nums[0] + nums[1] + nums[2];
        
        Arrays.sort(nums);
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length - 2; i++) {
            if (min == 0) break;
            if(i > 0 && nums[i] == nums[i-1]) continue;

            int j = i + 1, k = nums.length-1;
            while(j < k) {
                int candidate = nums[i] + nums[j] + nums[k];
                if (Math.abs(candidate - target) < min) {
                    min = Math.abs(candidate - target);
                    sum = candidate;
                }
                if (candidate == target) {
                    break;
                }
                else if (candidate < target) {
                    j++;
                    while (j < k && nums[j] == nums[j-1])
                        j++;
                }
                else {
                    k--;
                    while (k > j && nums[k] == nums[k+1])
                        k--;
                }
            }
        }

        return sum;
    }
}
