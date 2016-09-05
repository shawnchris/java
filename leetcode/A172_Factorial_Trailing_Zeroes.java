package leetcode;
//O(logbase5 n)
public class A172_Factorial_Trailing_Zeroes {
    public int trailingZeroes(int n) {
        int rs = 0;
        for (long i = 5; i <= n; i *= 5)
            rs += n / i;
        return rs;
    }
}
