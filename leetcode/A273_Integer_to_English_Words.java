package leetcode;

public class A273_Integer_to_English_Words {
    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        
        String[] thousands = {"Thousand", "Million", "Billion"};
        String[] tens = {"Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        String[] teens = {"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] digits = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        
        StringBuilder sb = new StringBuilder();
        int thousand = -1, i = 1;
        for (; num > 0; num /= 10) {
            if (i == 4) { // 1 more thousand 
                thousand++;
                i = 1;
                if (num % 10 != 0 || num %100 != 0 || num % 1000 != 0) // corner case 1000000
                    sb.insert(0, thousands[thousand] + " ");
            }
            int j = num % 10;
            if (i == 1 && j > 0) {
                if (num > 10 && num / 10 % 10 == 1) {
                    sb.insert(0, teens[j - 1] + " ");
                    i++;
                    num /= 10;
                }
                else {
                    sb.insert(0, digits[j - 1] + " ");
                }
            }
            else if (i == 2 && j > 0) {
                sb.insert(0, tens[j - 1] + " ");
            }
            else if (i == 3 && j > 0) {
                sb.insert(0, digits[j - 1] + " Hundred ");
            }
            i++;
        }
        
        return sb.toString().trim();
    }
	public static void main(String[] args) {
		A273_Integer_to_English_Words ite = new A273_Integer_to_English_Words();
		System.out.println(ite.numberToWords(Integer.MAX_VALUE));

	}

}
