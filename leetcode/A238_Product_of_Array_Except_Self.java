package leetcode;

public class A238_Product_of_Array_Except_Self {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];
        
        int acc = 1;
        for (int i = 0; i < len; i++) {
            result[i] = acc;
            acc *= nums[i];
        }
        acc = 1;
        for (int i = len-1; i >= 0; i--) {
            result[i] *= acc;
            acc *= nums[i];
        }
        
        return result;
    }
}
