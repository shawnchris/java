package tips;

import java.util.ArrayList;
import java.util.List;

public class Palindrome {

    // Judge if sub string from i to j of s is palindrome
    boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++; j--;
            }
            else return false;
        }
        return true;
    }

    // Judge if integer x is palindrome
    boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) return false;

        int rev = 0;
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x = x / 10;
        }

        return (x == rev || x == rev / 10);
    }

    // Find longest palindrome sub string by extending
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;

        int[] max = new int[2];
        for (int i = 0; i < s.length(); i++) { // i is the mid point
            extendPalindrome(s, i, i, max); // odd length;
            extendPalindrome(s, i, i + 1, max); // even length
        }

        return s.substring(max[0] + 1, max[1]);
    }
    private void extendPalindrome(String s, int left, int right, int[] max) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--; right++;
        }
        if (right - left > max[1] - max[0]) {
            max[0] = left; max[1] = right;
        }
    }

    // Find all possible palindrome partitions of string s
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        partitionHelper(0,s,new ArrayList<String>(), res);
        return res;
    }
    private void partitionHelper(int start, String s, ArrayList<String> list, List<List<String>> res) {
        if (start == s.length()) {
            res.add(new ArrayList<String>(list));
            return ;
        }
        for (int i = start + 1; i <= s.length(); i++) {
            String temp = s.substring(start,i);
            if (isPalindrome(temp, 0, temp.length() - 1)) {
                list.add(temp);
                partitionHelper(i, s, list, res);
                list.remove(list.size() - 1);
            }
        }
    }

    // Minimum cuts to cut s to palindrome sub strings
    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // preparation
        boolean[][] isPalindrome = getIsPalindrome(s);

        // initialize
        int[] f = new int[s.length() + 1];
        for (int i = 0; i <= s.length(); i++) {
            f[i] = i - 1;
        }

        // main
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (isPalindrome[j][i - 1]) {
                    f[i] = Math.min(f[i], f[j] + 1);
                }
            }
        }

        return f[s.length()];
    }

    private boolean[][] getIsPalindrome(String s) {
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }

        for (int length = 2; length < s.length(); length++) {
            for (int start = 0; start + length < s.length(); start++) {
                isPalindrome[start][start + length]
                        = isPalindrome[start + 1][start + length - 1] && s.charAt(start) == s.charAt(start + length);
            }
        }

        return isPalindrome;
    }

}
