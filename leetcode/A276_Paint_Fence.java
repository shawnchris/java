package leetcode;

public class A276_Paint_Fence {
    public int numWays(int n, int k) {
        if (k <= 0 || n <= 0) return 0;
        if (n == 1) return k;
        if (n == 2) return k * k;
        
        int diff = k * (k - 1), same = k;
        for (int i = 3; i <= n; i++) {
            int temp = diff;
            diff = (diff + same) * (k - 1);
            same = temp;
        }
        
        return diff + same;
    }
}
