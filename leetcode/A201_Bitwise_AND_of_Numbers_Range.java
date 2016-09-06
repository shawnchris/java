package leetcode;

public class A201_Bitwise_AND_of_Numbers_Range {
    public int rangeBitwiseAnd(int m, int n) {
        if (m == 0) return 0;
        if (m == n) return m;
        if (n - m >= m) return 0;
        
        int mbits = 0, nbits = 0;
        for (int i = m; i > 0; i = i >> 1)
            mbits++;
        for (int i = n; i > 0; i = i >> 1)
            nbits++;
        if (mbits != nbits) return 0;
        
        long r = m;
        for (long i = m + 1; i <= n; i++) {
            r = r & i;
        }
        
        return (int) r;
    }
}
