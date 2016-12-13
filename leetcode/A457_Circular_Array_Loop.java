package leetcode;

public class A457_Circular_Array_Loop {
	public boolean circularArrayLoop(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) continue;
            if (hasLoop(nums, i)) {
                return true;
            }
            else {
                nums[i] = 0;
            }
        }
        return false;
    }
    
    private boolean hasLoop(int[] nums, int start) {
        int steps = 0, next = -1;
        while (next != start) {
            steps++;
            if (steps == 1) next = start;
            next = (next + nums[next]) % nums.length;
            if (next < 0) next += nums.length;
            if (next == start) {
                if (steps == 1) return false;
                else return true;
            }
            if (nums[next] == 0) return false;
            if (nums[next] * nums[start] < 0) return false;
        }
        return true;
    }
    
	public static void main(String[] args) {
		A457_Circular_Array_Loop cal = new A457_Circular_Array_Loop();
		System.out.println(cal.circularArrayLoop(new int[] {2, -1, -1, 1, 4})); //true
		System.out.println(cal.circularArrayLoop(new int[] {2, -1, 1, 2, 2})); //true
		System.out.println(cal.circularArrayLoop(new int[] {-1, 2})); //false
	}

}
