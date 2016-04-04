package leetcode;

// Bit Manipulation solution O(lg(n))
// x^n = ax^1 * bx^2 * cx^4 * ... (a, b, c = 0 or 1 depends on bit)
public class A050_Pow_x_n {
	public double myPow(double x, int n) {
        long ln = n;
        if (ln < 0) {
            ln = -ln;
            x = 1 / x;
        }
        double result = 1;
        for (double base = x; ln > 0; ln = ln >> 1) {
            if ((ln & 1) == 1)
                result *= base;
            base *= base;
        }
        return result;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
