package interview.uber;
/*
 * Find insertion position in a rotated sorted array.
 */
public class Insert_Rotated_Sorted_Array {
	public static int findPosition(int[] nums, int target) {
		if (nums == null || nums.length == 0) return 0;
		int start = 0, end = nums.length - 1, mid = 0;
		
		// Corner case, target can be either at head or tail
		if (nums[start] > target && nums[end] < target)
			return nums.length;
		
		while (start + 1 < end) {
			mid = start + (end - start) / 2;
			if (nums[start] < nums[mid]) { // Left part sorted
				if (nums[start] <= target && target <= nums[mid])
					end = mid;
				else
					start = mid;
			}
			else { // Right part sorted
				if (nums[mid] <= target && target <= nums[end])
					start = mid;
				else
					end = mid;
			}
		}
		
		if ((nums[start] - target) * (nums[end] - target) 
				* (nums[end] - nums[start]) < 0)
			return end;
		if (nums[0] > target) return 0;
		else return nums.length;
	}
	public static void main(String[] args) {
		System.out.println(findPosition(new int[] {4,5,8,9,1,2}, 10)); //4
		System.out.println(findPosition(new int[] {4,5,8,9,1,2}, 7)); //2
		System.out.println(findPosition(new int[] {4,5,8,9,1,2}, 3)); //6
		System.out.println(findPosition(new int[] {4,5,8,9,1,3}, 2)); //5
		System.out.println(findPosition(new int[] {4,5,8,9}, 3)); //0
		System.out.println(findPosition(new int[] {4,5,8,9}, 10)); //4
		System.out.println(findPosition(new int[] {4,5,8,9}, 6)); //2
	}

}
