package interview.uber;

public class A154_Find_Min_in_Rotated_Sorted_ArrayII {

	public int findMin(int[] nums) {
        if (nums.length == 1) return nums[0];
        int start = 0, end = nums.length - 1, mid = 0;
        
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[end] < nums[mid]) { // Min is at right part
                start = mid;
            }
            else if (nums[end] > nums[mid]) { 
                // Min is at left part even no rotate
                end = mid;
            }
            else if (nums[start] == nums[mid]) { // All equal
                start++;
                end--;
            }
            else { // Min is at left part
                end = mid;
            }
        }
        
        if (nums[end] < nums[start])
            return nums[end];
        return nums[start];
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
