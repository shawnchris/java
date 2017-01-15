package leetcode;
import java.util.*;

public class A487_Max_Consecutive_Ones_II {
	/*
	 * The idea is to keep a window [l, h] that contains at most k zero.
	 * The first solution does not handle follow-up, because nums[l] will 
	 * need to access previous input stream.
	 * Time: O(n) Space: O(1)
	*/
    public int findMaxConsecutiveOnes1(int[] nums) {
        int max = 0, zero = 0, k = 1; // flip at most k zero
        for (int l = 0, h = 0; h < nums.length; h++) {
            if (nums[h] == 0)                                           
                zero++;
            while (zero > k)
                if (nums[l++] == 0)
                    zero--;                                     
            max = Math.max(max, h - l + 1);
        }                                                               
        return max;             
    }
	/*
	 * Now let's deal with follow-up, we need to store k indexes of previous zeros 
	 * so that we know where to move l next when window contains more than k zero
	 * Time: O(n) Space: O(k)
	*/
    public int findMaxConsecutiveOnes2(int[] nums) {                 
        int max = 0, k = 1; // flip at most k zero
        LinkedList<Integer> zeroIndex = new LinkedList<>(); 
        for (int l = 0, h = 0; h < nums.length; h++) {
            if (nums[h] == 0)
                zeroIndex.add(h);
            if (zeroIndex.size() > k)                                   
                l = zeroIndex.removeFirst() + 1;
            max = Math.max(max, h - l + 1);
        }
        return max;                     
    } 
	
    public static int findMaxConsecutiveOnes(int[] nums) {
        int result = 0, n = nums.length, count = 0;
        int[] dp = new int[n];
        
        for (int i = 0; i < n; i++) {
        	dp[i] = count;
        	if (nums[i] == 1) {
        		count++;
        		result = Math.max(count, result);
        	}
        	else count = 0;
        }
        
        count = 0;
        for (int i = n - 1; i >= 0; i--) {
        	dp[i] += count;
        	if (nums[i] == 1) count++;
        	else count = 0;
        }
        
        for (int i = 0; i < n; i++) {
        	if (nums[i] == 0) {
        		result = Math.max(result, dp[i] + 1);
        	}
        }
        
        return result;
    }
    
	public static void main(String[] args) {
		System.out.println(findMaxConsecutiveOnes(new int[] {1,1,0,1,1,1}));
		System.out.println(findMaxConsecutiveOnes(new int[] {}));
		System.out.println(findMaxConsecutiveOnes(new int[] {1}));
		System.out.println(findMaxConsecutiveOnes(new int[] {1,0,1,0,1}));
		System.out.println(findMaxConsecutiveOnes(new int[] {1,0,1,0,1,1}));
	}

}
