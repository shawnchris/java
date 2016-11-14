package leetcode;

public class A441_Arranging_Coins {
    public static int arrangeCoins(int n) {
        return (int)Math.floor(Math.sqrt(0.25 + 2 * Double.valueOf(n)) - 0.5);
    }
    
	public static void main(String[] args) {
		System.out.println(arrangeCoins(1804289383));

	}

}
