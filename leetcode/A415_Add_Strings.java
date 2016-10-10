package leetcode;

public class A415_Add_Strings {
    public static String addStrings(String num1, String num2) {
        if (num1 == null || num1.length() == 0) return num2;
        if (num2 == null || num2.length() == 0) return num1;
        int i1 = num1.length() - 1, i2 = num2.length() - 1;
        int a = 0, b = 0, c = 0, d = 0;
        StringBuilder sb = new StringBuilder();
        
        while (i1 >= 0 || i2 >= 0 || c > 0) {
            a = i1 >= 0 ? Integer.parseInt(num1.charAt(i1) + "") : 0;
            b = i2 >= 0 ? Integer.parseInt(num2.charAt(i2) + "") : 0;
            d = a + b + c;
            sb.insert(0, d % 10);
            c = d / 10;
            i1--;
            i2--;
        }
        
        return sb.toString();
    }
    
	public static void main(String[] args) {
		System.out.println(addStrings("0", "0"));

	}

}
