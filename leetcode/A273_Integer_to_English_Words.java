package leetcode;

public class A273_Integer_to_English_Words {
    private static String[] digits = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    
    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        return parse(num).trim();
    }
    
    private String parse(int num) {
        String result = "";
        if (num < 10) result = digits[num];
        else if (num < 20) result = teens[num - 10];
        else if (num < 100) result = tens[num / 10] + " " + parse(num % 10);
        else if (num < 1000) result = parse(num / 100) + " Hundred " + parse(num % 100);
        else if (num < 1000000) result = parse(num / 1000) + " Thousand " + parse(num % 1000);
        else if (num < 1000000000) result = parse(num / 1000000) + " Million " + parse(num % 1000000);
        else result = parse(num / 1000000000) + " Billion " + parse(num % 1000000000);
        return result.trim();
    }
    
	public static void main(String[] args) {
		A273_Integer_to_English_Words ite = new A273_Integer_to_English_Words();
		System.out.println(ite.numberToWords(Integer.MAX_VALUE));

	}

}
