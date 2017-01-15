package leetcode;

public class A485_Max_Consecutive_Ones {
    public static int findMaxConsecutiveOnes(int[] nums) {
        int result = 0;
        if (nums == null || nums.length == 0) return 0;
        
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
        	if (nums[i] == 1) {
        		count++;
        		result = Math.max(count, result);
        	}
        	else
        		count = 0;
        }
        return result;
    }
    
	public static void main(String[] args) {
		System.out.println(findMaxConsecutiveOnes(new int[] {1,1,0,1,1,1}));
		System.out.println(findMaxConsecutiveOnes(new int[] {}));
		System.out.println(findMaxConsecutiveOnes(new int[] {1}));
		System.out.println(findMaxConsecutiveOnes(new int[] {1,0,1,0,1}));
		System.out.println(findMaxConsecutiveOnes(new int[] {1,0,1,0,1}));
	}

}
