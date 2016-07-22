package leetcode;

public class A042_Trapping_Rain_Water {
	public int trap(int[] height) {
        int len = height.length;
        if (len <= 2) return 0;
        
        int sum = 0;
        int s = 0, e = len - 1, top = 0;
        
        while (s < e) {
            if (height[s] < height[e]) {
                top = Math.max(top, height[s]);
                sum += top - height[s];
                s++;
            }
            else {
                top = Math.max(top, height[e]);
                sum += top - height[e];
                e--;
            }
        }
        
        return sum;
    }
}
