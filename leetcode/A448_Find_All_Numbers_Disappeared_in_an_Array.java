package leetcode;
import java.util.*;

public class A448_Find_All_Numbers_Disappeared_in_an_Array {
	public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        
        return result;
    }
}
