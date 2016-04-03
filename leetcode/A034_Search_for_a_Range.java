package leetcode;

public class A034_Search_for_a_Range {
	
	public int[] searchRange(int[] A, int target) {
        int[] result = {-1, -1};
        if (A == null || A.length == 0) return result;
        
        int start = 0, end = A.length - 1;
        // Search the left boundary
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] >= target) {
                end = mid;
            }
            else if (A[mid] < target) {
                start = mid;
            }
        }
        if (A[start] == target) {
            result[0] = start;
        }
        else if (A[end] == target) {
            result[0] = end;
        }
        else {
            return result;
        }
        
        start = 0; end = A.length - 1;
        // Search the right boundary
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] <= target) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        if (A[end] == target) {
            result[1] = end;
        }
        else if (A[start] == target) {
            result[1] = start;
        }
        
        return result;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
