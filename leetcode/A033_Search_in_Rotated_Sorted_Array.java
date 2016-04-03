package leetcode;

public class A033_Search_in_Rotated_Sorted_Array {
	public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
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
            else { // Left part sorted
                if (nums[start] <= target && nums[mid] >= target) {
                    end = mid;
                }
                else {
                    start = mid;
                }
            }
        }
        
        if (nums[start] == target)
            return start;
        if (nums[end] == target)
            return end;
        return -1;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
