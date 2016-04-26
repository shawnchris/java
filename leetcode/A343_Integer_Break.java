package leetcode;

public class A343_Integer_Break {
/*
Why the max product of any n > 4 must contain a factor of 3? 
1. It can't contain any factor x that is >= 5, o.w., we can further increase
the max product by decomposing x, as the decomposed x when x >= 5 is strictly
greater than x;
2. Out of 1, 2, 3, 4, we know 1 won't be a factor of n when n > 4, if n is an
odd number, 3 must be there as a factor(2 and 4 can't add up to an odd number);
3. Now say n is an even number (n > 4) and only has factor of 2 and 4, we can
always split a 6 to 3 X 3, which is better than 2 X 2 X 2. Therefore, the max
product of any n (n > 4) must contain a factor of 3.
The recurrence relation holds.
*/
    public int integerBreak(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;
        
        return 3 * helper(n - 3);
    }
    
    private int helper(int n) {
        if (n < 5) return n;
        return 3 * helper(n - 3);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
