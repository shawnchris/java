package leetcode;

import java.util.ArrayList;
import java.util.List;

public class A592_Fraction_Addition_and_Subtraction {
    public String fractionAddition(String expression) {
        List<String> nums = new ArrayList<>();
        int i = 0, j = 0;
        while (j <= expression.length()) {
            if (j == expression.length() || j != i && (expression.charAt(j) == '+' || expression.charAt(j) == '-')) {
                if (expression.charAt(i) == '+') {
                    nums.add(expression.substring(i + 1, j));
                }
                else {
                    nums.add(expression.substring(i, j));
                }

                i = j;
            }
            j++;
        }

        String result = "0/1";

        for (String num : nums) {
            result = add(result, num);
        }

        return result;
    }

    private String add(String s1, String s2) {
        String[] sa1 = s1.split("/");
        String[] sa2 = s2.split("/");
        int n1 = Integer.parseInt(sa1[0]);
        int d1 = Integer.parseInt(sa1[1]);
        int n2 = Integer.parseInt(sa2[0]);
        int d2 = Integer.parseInt(sa2[1]);

        int n = n1 * d2 + n2 * d1;
        int d = d1 * d2;

        if (n == 0) return "0/1";

        boolean isNegative = n * d < 0;
        n = Math.abs(n);
        d = Math.abs(d);
        int gcd = getGCD(n, d);

        return (isNegative ? "-" : "") + (n / gcd) + "/" + (d / gcd);
    }

    private int getGCD(int a, int b) {
        if (a == 0 || b == 0) return a + b; // base case
        return getGCD(b, a % b);
    }
}
