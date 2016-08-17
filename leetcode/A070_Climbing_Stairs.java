package leetcode;

public class A070_Climbing_Stairs {
    public int climbStairs(int n) {
        if (n == 0) return 1;
        if (n == 1) return 1;
        int res = 0, step2 = 1, step1 = 1;
        for (int i = 2; i <= n; i++) {
            res = step2 + step1;
            step2 = step1;
            step1 = res;
        }
        return res;
    }
}
