package leetcode;

public class A680_Valid_Palindrome_II {
    public boolean validPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j && s.charAt(i) == s.charAt(j)) {
            i++; j--;
        }

        if (i >= j) return true;

        if (isPalin(s, i + 1, j) || isPalin(s, i, j - 1)) return true;
        return false;
    }

    private boolean isPalin(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++; j--;
            }
            else return false;
        }
        return true;
    }
}
