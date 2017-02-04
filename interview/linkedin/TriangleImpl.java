package interview.linkedin;

import java.util.Arrays;

public class TriangleImpl implements Triangle {
	public int threeSumSmaller(int[] nums, int target) {
        int count = 0;
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] < target) {
                    count += k - j;
                    j++;
                }
                else {
                    k--;
                }
            }
        }
        
        return count;
    }
	
	public int getTotalCount(int[] segments) {
        int count = 0;
        Arrays.sort(segments);
        
        for (int i = 0; i < segments.length - 2; i++) {
            int j = i + 1, k = segments.length - 1;
            while (j < k) {
                if (segments[i] + segments[j] > segments[k]) {
                    count += k - j;
                    j++;
                }
                else {
                    k--;
                }
            }
        }
        
        return count;
    }
	
	public int[] getTriangleSides(int[] segments) {
		int i, j , k, n = segments.length;
		int[] ret = new int[0]; 
		if (n < 3) return ret; 
		Arrays.sort(segments); 
		for (k = n - 1; k - 2 >= 0; k--) {
			if ( k != n - 1 && segments[k] == segments[k+1]) continue; 
			j = k - 1;
			i = k - 2; 
			if (segments[i] + segments[j] > segments[k]) { 
				ret = new int[] {segments[i], segments[j], segments[k]}; 
				break; 
			} 
		}
		return ret;
	} 
}