package leetcode;

public class A009_Palindrome_Number {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x < 10) return true;
        int y = x, c = 0, z = 0;
        while (y > 0) {
            c++;
            y = y / 10;
        }
        for (int i = 0; i < c / 2; i++) {
            z = z * 10 + x % 10;
            x = x / 10;
        }
        if (c % 2 == 1) {
            x = x / 10;
        }
        return x == z;
    }
}
