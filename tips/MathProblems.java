package tips;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shawngao on 9/22/17.
 */
public class MathProblems {

    // Greatest Common Divisor
    public int gcd(int a, int b) {
        if (a == 0 || b == 0) return a + b; // base case
        return gcd(b,a % b);
    }

    public int gcdIterative(int a, int b) {
        while (a != 0 && b != 0) { // until either one of them is 0
            int c = b;
            b = a % b;
            a = c;
        }
        return a + b; // either one is 0, so return the non-zero value
    }

    // Count how many prime number < n. Sieve of Eratosthenes
    public int countPrimes(int n) {
        if (n < 2) return 0;

        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++)
            isPrime[i] = true;

        for (int i = 2; i * i < n; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrime[i]) count++;

        return count;
    }

    // Return a new integer based on different Radix
    public String newRadix(int n, int radix) {
        return Integer.toString(n, radix);
    }

    // Fraction to Recurring Decimal. Given numerator = 2, denominator = 3, return "0.(6)".
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder fraction = new StringBuilder();
        // If either one is negative (not both)
        if (numerator < 0 ^ denominator < 0) {
            fraction.append("-");
        }
        // Convert to Long or else abs(-2147483648) overflows
        long dividend = Math.abs(Long.valueOf(numerator));
        long divisor = Math.abs(Long.valueOf(denominator));
        fraction.append(String.valueOf(dividend / divisor));
        long remainder = dividend % divisor;
        if (remainder == 0) {
            return fraction.toString();
        }
        fraction.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                fraction.insert(map.get(remainder), "(");
                fraction.append(")");
                break;
            }
            map.put(remainder, fraction.length());
            remainder *= 10;
            fraction.append(String.valueOf(remainder / divisor));
            remainder %= divisor;
        }
        return fraction.toString();
    }

    // Fraction class
    class Fraction {
        private int numerator;
        private int denominator;

        public Fraction(int numerator, int denominator) {
            if (denominator == 0) {
                throw new IllegalArgumentException("Denominator can't be 0");
            }

            this.numerator = numerator;
            this.denominator = denominator;
            shrink();
        }

        public Fraction() {
            this(0, 1);
        }

        public int getNumerator() {
            return numerator;
        }

        public void setNumerator(int numerator) {
            this.numerator = numerator;
            shrink();
        }

        public int getDenominator() {
            return denominator;
        }

        public void setDenominator(int denominator) {
            if (denominator == 0) {
                throw new IllegalArgumentException("Denominator can't be 0");
            }
            this.denominator = denominator;
            shrink();
        }

        private void shrink() {
            int gcd = gcd(this.denominator, this.numerator);
            this.numerator /= gcd;
            this.denominator /= gcd;
        }

        private int gcd(int a, int b) {
            if (a == 0 || b == 0) return a + b; // base case
            return gcd(b, a % b);
        }

        public Fraction add(Fraction that) {
            return new Fraction(this.numerator * that.denominator + this.denominator * that.numerator,
                    this.denominator * that.denominator);
        }

        public Fraction minus(Fraction that) {
            return new Fraction(this.numerator * that.denominator - this.denominator * that.numerator,
                    this.denominator * that.denominator);
        }

        public Fraction multiply(Fraction that) {
            return new Fraction(this.numerator * that.numerator,
                    this.denominator * that.denominator);
        }

        public Fraction divide(Fraction that) {
            return new Fraction(this.numerator * that.denominator,
                    this.denominator * that.numerator);
        }

        public double doubleValue() {
            return (double) numerator / denominator;
        }

        @Override
        public String toString() {
            return String.format("{%d/%d}", this.numerator, this.denominator);
        }

    }
}
