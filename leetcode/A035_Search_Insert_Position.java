package leetcode;

public class A035_Search_Insert_Position {
	public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int start = 0, end = nums.length - 1, mid = 0;
        
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        
        if (nums[start] >= target)
            return start;
        if (nums[end] >= target)
            return end;
        return end + 1;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
