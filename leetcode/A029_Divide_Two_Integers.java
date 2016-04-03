package leetcode;

// Bit Manipulation Solution
public class A029_Divide_Two_Integers {
	public int divide(int dividend, int divisor) {
        // Corner case
        if(divisor == 1)
            return dividend;
        if(dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;
    
        int sign = (dividend > 0 ^ divisor > 0) ? -1 : 1;
    
        int ans = 0;
        long end = Math.abs((long)dividend);
        long sor = Math.abs((long)divisor);
    
        while(end >= sor) {
            long temp  = sor;
            int power = 1;
            while((temp << 1) < end) {
                power <<= 1;
                temp  <<= 1;
            }
            ans += power;
            end -= temp;
        }
        return sign * ans;
    }
	
	public static void main(String[] args) {

	}

}
