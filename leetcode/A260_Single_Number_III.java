package leetcode;

public class A260_Single_Number_III {
    /* Bit Manipulation Solution:
     * The reason why XOR works is, to separate the 2 unique numbers, their bits will differ in at
     * least one position, which would be the 1's in XOR. We take the rightmost such 1 from the XOR.
     * This 1 must have come from either numbers, to identify which, we XOR all numbers into 2 sets,
     * one set which had that bit set and one which didn't. In the end, the duplicate numbers will
     * cancel themselves out leaving only the unique numbers in each set.
     */ 
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int num: nums) {
            xor ^= num;
        }
        
        int bit = xor & ~(xor - 1);
        
        int num1 = 0, num2 = 0;
        
        for (int num: nums) {
            if ((num & bit) == 0) {
                num1 ^= num;
            }
            else {
                num2 ^= num;
            }
        }
        
        return new int[]{num1, num2};
    }
}
