package leetcode;

public class A053_Maximum_Subarray {
    public int maxSubArray(int[] A) {
		int len = A.length;
		int newsum = 0;
		int oldsum = A[0];
		int max = A[0];
		
		for (int i = 1; i < len; i++) {
			newsum = Math.max(A[i], A[i] + oldsum);
			max = Math.max(max, newsum);
			oldsum = newsum;
		}
		
		return max;
    }
}
