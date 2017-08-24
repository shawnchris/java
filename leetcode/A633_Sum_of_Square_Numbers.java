package leetcode;

public class A633_Sum_of_Square_Numbers {
    public boolean judgeSquareSum(int c) {
        if (c == 0) return true;

        for (int i = 0; i * i < c; i++) {
            int d = c - i * i;
            int e = (int)Math.sqrt(d);
            if (e * e == d) return true;
        }

        return false;
    }
}
