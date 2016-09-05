package leetcode;

public class A190_Reverse_Bits {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int reversed = 0;
        for(int i = 0; i < 32; i++) {
            reversed <<= 1;
            if ((n & 1) == 1)
                reversed |= 1;
            n >>>= 1;
        }
        
        return reversed;
    }
}
