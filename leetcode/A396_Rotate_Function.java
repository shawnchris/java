package leetcode;

public class A396_Rotate_Function {
	public int maxRotateFunction(int[] A) {
        if (A == null || A.length <= 1) return 0;
        
        // init
        int n = A.length, sum = 0, f = 0;
        for (int i = 0; i < n; i++) {
        	sum += A[i];
        	f += i * A[i];
        }
        int max = f;
        
        // calculate
        for (int i = n - 1; i > 0; i--) {
        	f = f + sum - n * A[i];
        	max = Math.max(max, f);
        	System.out.println(f);
        }
        
        return max;
    }
	public static void main(String[] args) {
		A396_Rotate_Function rf = new A396_Rotate_Function();
		//int[] A1 = {4, 3, 2, 6};
		//System.out.println(rf.maxRotateFunction(A1)); // 26
		int[] A2 = {1,2,3,4,5,6,7,8,9,10};
		System.out.println(rf.maxRotateFunction(A2));
	}

}
