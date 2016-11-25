package leetcode;

public class A413_Arithmetic_Slices {
	// O(n) solution
    public int numberOfArithmeticSlices2(int[] A) {
    	if (A == null || A.length < 3) return 0;
    	
    	int acc = 0, sum = 0;
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                acc += 1;
                sum += acc;
            } else {
                acc = 0;
            }
        }
        return sum;
    }
	
    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length < 3) return 0;
        
        int sum = 0;
        for (int i = 2; i < A.length; i++) {
            int count = 0, j = i - 1, delta = A[i] - A[j];
            while (j > 0 && A[j] - A[j - 1] == delta) {
                count++;
                j--;
            }
            sum += count;
        }
        
        return sum;
    }
}
