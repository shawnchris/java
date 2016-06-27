package leetcode;

public class A011_Container_With_Most_Water {
	public int maxArea(int[] height) {
        int l = 0, r = height.length - 1, result = 0;
        while (l < r) {
            if (height[l] < height[r]) {
                 result =  Math.max(result, height[l] * (r - l));
                 int pivot = height[l++];
                 while (l < r && height[l] <= pivot) ++l;
            }
            else {
                 result = Math.max(result, height[r] * (r - l));
                 int pivot = height[r--];
                 while(l < r && height[r] <= pivot) --r;
            }
        }
        return result;
    }
}
