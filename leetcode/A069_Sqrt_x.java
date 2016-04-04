package leetcode;

public class A069_Sqrt_x {
	
	public int mySqrt(int x) {
        int res = 0;
        for (int n = 1 << 15; n > 0; n >>= 1) {
            int next = res | n;
            if (next <= x / next)
                res = next;
        }
        return res;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
