package leetcode;

public class A287_Find_the_Duplicate_Number {

	public int findDuplicate(int[] nums) {
        int n = nums.length;
        int slow = n;
        int fast = n;
        
        do {
            slow = nums[slow - 1];
            fast = nums[nums[fast - 1] - 1];
        } while (slow != fast);

        fast = n;
        while (fast != slow) {
            fast = nums[fast - 1];
            slow = nums[slow - 1];
        }
        
        return slow;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
