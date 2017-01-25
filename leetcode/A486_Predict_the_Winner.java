package leetcode;
import java.util.*;

public class A486_Predict_the_Winner {
    
    public boolean helper(int[] nums, int left, int right, int sum1, int sum2) {
        if (left > right) {
        	if (sum1 > sum2) return true;
        	if (sum1 == sum2 && (nums.length - (right - left + 1)) % 2 == 0) return true;
        	return false;
        }
        
        if (!helper(nums, left + 1, right, sum2, sum1 + nums[left])
        	|| !helper(nums, left, right - 1, sum2, sum1 + nums[right]))
        	return true;
        
        return false;
    }
   
    // transfer boolean[] to an Integer 
    public int format(boolean[] used){
        int num = 0;
        for(boolean b: used){
            num <<= 1;
            if(b) num |= 1;
        }
        return num;
    }
    public boolean PredictTheWinner(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        
    	return helper(nums, 0, nums.length - 1, 0, 0);
    }
	public static void main(String[] args) {
		A486_Predict_the_Winner pw = new A486_Predict_the_Winner();
		int[] nums1 = {1, 5, 2}; // false
		System.out.println(pw.PredictTheWinner(nums1));
		int[] nums2 = {1, 5, 233, 7}; // true
		System.out.println(pw.PredictTheWinner(nums2));
		int[] nums3 = {1, 1}; // true
		System.out.println(pw.PredictTheWinner(nums3));
		int[] nums4 = {1, 3, 1}; // false
		System.out.println(pw.PredictTheWinner(nums4));
		int[] nums5 = {0}; // true
		System.out.println(pw.PredictTheWinner(nums5));
	}

}
