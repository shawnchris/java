package leetcode;

public class A457_Circular_Array_Loop {
	public boolean circularArrayLoop(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) continue;
            
            int slow = i, fast = i;
            do {
            	slow = walk(nums, slow);
            	if (slow == -1) break;
            	fast = walk(nums, fast);
            	if (fast == -1) break;
            	fast = walk(nums, fast);
            	if (fast == -1) break;
            } while (slow != fast);
            
            if (slow != fast) {
            	nums[i] = 0;
            }
            else {
            	return true;
            }
        }
        return false;
    }
	
	private int walk(int[] nums, int start) {
        int next = (start + nums[start]) % nums.length;
        if (next < 0) next += nums.length;
        if (next == start) return -1; // avoid single-jump-loop
        if (nums[next] == 0) return -1; // approved to be no way
        if (nums[next] * nums[start] < 0) return -1; // changed direction
        return next;
	}
    
	public static void main(String[] args) {
		A457_Circular_Array_Loop cal = new A457_Circular_Array_Loop();
		System.out.println(cal.circularArrayLoop(new int[] {2, -1, -1, 1, 4})); //true
		System.out.println(cal.circularArrayLoop(new int[] {2, -1, 1, 2, 2})); //true
		System.out.println(cal.circularArrayLoop(new int[] {-1, 2})); //false
		System.out.println(cal.circularArrayLoop(new int[] {1, 2, -1, 2})); //true
	}

}
