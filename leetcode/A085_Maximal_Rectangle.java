package leetcode;
import java.util.*;

public class A085_Maximal_Rectangle {
    private int largestRectangleArea(int[] height) {
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
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null) return 0;
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        if (n == 0) return 0;
        
        
        int[] height = new int[n];
        int max = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1')
                    height[j] += 1;
                else
                    height[j] = 0;
            }
            max = Math.max(max, largestRectangleArea(height));
        }
        
        return max;
    }
}
