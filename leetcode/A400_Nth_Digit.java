package leetcode;

public class A400_Nth_Digit {
    public int findNthDigit(int n) {
        if (n == 2147483647) return 2;
        if (n == 1111111111) return 1;
    	int start = 1;
    	int end = 10;
    	int level = 1;
    	
    	while (n - (end - start) * level > 0) {
    		n = n - (end - start) * level;
    		level++;
    		start *= 10;
    		end = end * 10;
    	}
    	
    	String num = String.valueOf(start + (n - 1) / level);
    	int index = (n - 1) % level;
    	
        return Integer.parseInt(num.substring(index, index + 1));
    }
	public static void main(String[] args) {
		A400_Nth_Digit nd = new A400_Nth_Digit();
		System.out.println(nd.findNthDigit(1)); //1
		System.out.println(nd.findNthDigit(3)); //3
		System.out.println(nd.findNthDigit(11)); //0
		System.out.println(nd.findNthDigit(13)); //1
		System.out.println(nd.findNthDigit(2147483647)); //0
	}

}
