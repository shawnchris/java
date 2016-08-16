package leetcode;

public class A066_Plus_One {
    public int[] plusOne(int[] digits) {
        if (digits == null) return null;
        int len = digits.length;
        
        for (int i = len - 1; i >= 0; i--) {
            if (digits[i] == 9)
                digits[i] = 0;
            else {
                digits[i] += 1;
                return digits;
            }
        }
        
        int[] res = new int[len+1];
        res[0] = 1;
        return res;
    }
}
