package leetcode;

public class A376_Wiggle_Subsequence {
	public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int low = nums[0], high = nums[0], lowLen = 1, highLen = 1;
        
        for (int i = 1; i < nums.length; i++) {
        	int n = nums[i];
        	if (n > low && n < high) {
        		if (lowLen > highLen) {
        			high = n;
        			highLen = lowLen + 1;
        		}
        		else {
        			low = n;
        			lowLen = highLen + 1;
        		}
        	}
        	else if (n > low) {
        		high = n;
    			highLen = lowLen + 1;
        	}
        	else if (n < high) {
        		low = n;
    			lowLen = highLen + 1;
        	}
        }
        
		return Math.max(lowLen, highLen);
    }
	
	public static void main(String[] args) {
		A376_Wiggle_Subsequence ws = new A376_Wiggle_Subsequence();
		int[] nums1 = {1,7,4,9,2,5};
		int[] nums2 = {1,17,5,10,13,15,10,5,16,8};
		int[] nums3 = {1,2,3,4,5,6,7,8,9};
		System.out.println(ws.wiggleMaxLength(nums1)); //6
		System.out.println(ws.wiggleMaxLength(nums2)); //7
		System.out.println(ws.wiggleMaxLength(nums3)); //2
	}
}
