package leetcode;
import java.util.*;

//O(n) solution
//Use a stack to store the index of increasing members till meet one (index i) less than previous members
//Pop the stack (index j). Now every memeber with its right bound i, and left bound stack.peek() (index k).
//The largest RectArea with height(j) is height(j) * (i - k - 1)
public class A084_Largest_Rectangle_in_Histogram {
    public int largestRectangleArea(int[] height) {
        if (height == null) return -1;
        int len = height.length;
        if (len == 0) return 0;
        
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i <= len; i++) {
            int curr = (i == len) ? -1 : height[i];
            while (!stack.isEmpty() && height[stack.peek()] > curr) {
                int h = height[stack.pop()];
                int w = stack.isEmpty() ? i : (i - stack.peek() - 1);
                max = Math.max(max, h * w);
            }
            stack.push(i);
        }
        
        return max;
    }
}
