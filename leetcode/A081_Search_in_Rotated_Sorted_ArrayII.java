package leetcode;

public class A081_Search_in_Rotated_Sorted_ArrayII {

	public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int start = 0, end = nums.length - 1, mid = 0;
        
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] < nums[start]) { // Right part sorted
                if (nums[mid] <= target && nums[end] >= target) {
                    start = mid;
                }
                else {
                    end = mid;
                }
            }
            else if (nums[mid] > nums[start]) { // Left part sorted
                if (nums[start] <= target && nums[mid] >= target) {
                    end = mid;
                }
                else {
                    start = mid;
                }
            }
            else {
                start++;
            }
        }
        
        if (nums[start] == target || nums[end] == target)
            return true;
        return false;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
