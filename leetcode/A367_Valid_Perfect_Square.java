package leetcode;

public class A367_Valid_Perfect_Square {
	
    public static boolean isPerfectSquare(int num) {
        if (num <= 0) return false;
        int start = 1, end = num / 2;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            // Avoid overflow
            if (mid <= num / mid) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        
        if (start * start == num || end * end == num) {
            return true;
        }
        else {
            return false;
        }
    }
	public static void main(String[] args) {
		System.out.println(isPerfectSquare(808201));

	}

}
