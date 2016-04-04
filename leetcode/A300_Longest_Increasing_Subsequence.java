package leetcode;

/*
 * Binary Search Solution. Time O(nlgn), Space O(n)
 * Assume for nums[i] there are len integer satisfy and stored in array S with ascending order.
 * If nums[i] is greater than S[len - 1], that means we find one more integer. Just append nums[i] to the end and increase len.
 * Otherwise, find a position j in S that S[j] >= nums[i], replace S[j] with nums[i]. S[0] to S[j] potentially form another IS.
 * At last, len is the LIS of nums.
 */
public class A300_Longest_Increasing_Subsequence {

	private int findPositionToReplace(int[] a, int low, int high, int x) {
	    while (low + 1 < high) {
	        int mid = low + (high - low) / 2;
	        if (a[mid] < x) {
	            low = mid;
	        }
	        else {
	            high = mid;
	        }
	    }
	    if (a[low] >= x) {
	        return low;
	    }
	    return high;
	}

	public int lengthOfLIS(int[] nums) {
	    if (nums == null | nums.length == 0)
	        return 0;
	    int n = nums.length, len = 1;
	    int[] increasingSequence = new int[n];
	    increasingSequence[0] = nums[0];
	    
	    for (int i = 1; i < n; i++) {
	        if (nums[i] > increasingSequence[len - 1]) {
	            increasingSequence[len] = nums[i];
	            len++;
	        }
	        else {
	            int position = findPositionToReplace(increasingSequence, 0, len - 1, nums[i]);
	            increasingSequence[position] = nums[i];
	        }
	    }
	    return len;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
