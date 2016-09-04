package leetcode;

public class A357_Count_Numbers_with_Unique_Digits {
	public static int countNumbersWithUniqueDigits(int n) {
		if (n == 0) return 1;
        if (n == 1) return 10;
        
        int result = 0, prev = 10;
        int runningProduct = 1; //to keep the product of the series 9*8*7*....
        
        if (n > 10) n = 10; //there is no unique digits number larger than 10^10
        for(int i = 2; i <= n; i++) {
            runningProduct *= (10 - (i - 1));
            result = prev + 9 * runningProduct;
            prev = result;
        }

        return result;
    }
	public static void main(String[] args) {
		System.out.println(countNumbersWithUniqueDigits(1));
		System.out.println(countNumbersWithUniqueDigits(2));
		System.out.println(countNumbersWithUniqueDigits(3));
	}

}
