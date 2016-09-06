package leetcode;

public class A233_Number_of_Digit_One {
    public int countDigitOne(int n) {
        if(n <= 0) return 0;
        int x = 1, y=n;
        for(; y>=10; x *= 10, y /= 10);
        return countDigitOne(n, x);
    }
    
    int countDigitOne(int n, int x) {
        if(n == 0) return 0;
        if(n < 10 || x == 1) return 1;
        if(n / x == 1)
            return n - n / x * x + 1 + countDigitOne(n - n / x * x)
                + countDigitOne(x - 1);
        return  x + n / x * countDigitOne(x - 1)
            + countDigitOne(n - n / x * x);
    }
}
