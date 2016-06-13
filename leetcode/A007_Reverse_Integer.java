package leetcode;

public class A007_Reverse_Integer {
    public int reverse(int x) {
        if (x == 0) return 0;
        long l1, l2 = 0;
        if (x > 0)
            l1 = x;
        else
            l1 = -1 * x;
        
        while (l1 > 0) {
            long t = l1 % 10;
            l2 = l2 * 10 + t;
            l1 = l1 / 10;
        }
        
        if (x > 0 && l2 <= Integer.MAX_VALUE)
            return (int)l2;
        else if (x < 0 && (-1 * l2) >= Integer.MIN_VALUE)
            return (int)(-1 * l2);
        else
            return 0;
    }
}
