package tips;

/**
 * Created by shawngao on 9/22/17.
 */
public class MathProblems {

  // Greatest Common Divisor
  int gcd(int a, int b) {
    if (a == 0 || b == 0) return a + b; // base case
    return gcd(b,a % b);
  }

  int gcdIterative(int a, int b) {
    while (a != 0 && b != 0) { // until either one of them is 0
      int c = b;
      b = a % b;
      a = c;
    }
    return a + b; // either one is 0, so return the non-zero value
  }

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
      return gcd(b,a % b);
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
