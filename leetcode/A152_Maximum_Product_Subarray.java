package leetcode;

public class A152_Maximum_Product_Subarray {
    public int maxProduct(int[] A) {
        int result = A[0];
        int imax = result, imin = result, temp = 0;
        
        for (int i = 1; i < A.length; i++) {
            //negtive number makes a bigger number smaller and a smaller number bigger.
            if (A[i] < 0) { 
                temp = imax;
                imax = imin;
                imin = temp;
            }
            //max/min product for current number is either the current number or 
            //the max/min by the previous number times the current one.
            imax = Math.max(A[i], imax * A[i]);
            imin = Math.min(A[i], imin * A[i]);
            
            result = Math.max(imax, result);
        }
        
        return result;
    }
    
    public static int maxProduct2(int[] nums) {
    	int max = nums[0], min = nums[0], result = max;
        
        for (int i = 1; i < nums.length; i++) {
            
            if (nums[i] >= 0) {
                max = Math.max(nums[i], max * nums[i]);
                min = Math.min(nums[i], min * nums[i]);
            }
            else {
                int maxTemp, minTemp;
                maxTemp = Math.max(nums[i], min * nums[i]);
                minTemp = Math.min(nums[i], max * nums[i]);
                max = maxTemp;
                min = minTemp;
            }
            
            result = Math.max(result, max);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
    	int[] input1 = {-4, -3, -2};
    	System.out.println(maxProduct2(input1));
    }
}
