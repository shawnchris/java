package leetcode;

public class A461_Hamming_Distance {
	public static int hammingDistance(int x, int y) {
        int result = 0;
        /*
        while (x > 0 || y > 0) {
        	if ((x & 1) != (y & 1)) result++;
        	x >>= 1;
        	y >>= 1;
        }
        */
        
        return Integer.bitCount(x ^ y);
        
        //return result;
    }
	
	public static void main(String[] args) {
		System.out.println(hammingDistance(1, 4)); // 2

	}

}
