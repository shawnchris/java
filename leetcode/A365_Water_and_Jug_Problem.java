package leetcode;

public class A365_Water_and_Jug_Problem {
	private int gcd(int a, int b) {
		while (b != 0) {
			int t = b;
			b = a % b;
			a = t;
		}
		return a;
	}
	
	public boolean canMeasureWater(int x, int y, int z) {
        // Corner cases
        if (x + y < z) return false;
        if (x == z || y == z || x + y == z) return true;
        
        // https://en.wikipedia.org/wiki/B%C3%A9zout%27s_identity
        return z % gcd(x, y) == 0;
    }

}
