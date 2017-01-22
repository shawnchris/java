package leetcode;

public class A482_License_Key_Formatting {
    public static String licenseKeyFormatting(String S, int K) {
        if (S == null || S.length() == 0) return "";
        //if (K >= S.length()) return S;
        
        StringBuilder sb = new StringBuilder();
        int count = K;
        for (int i = S.length() - 1; i >= 0; i--) {
            char c = S.charAt(i);
            if (c == '-') continue;
            sb.append(Character.toUpperCase(c));
            count--;
            if (count == 0) {
                sb.append('-');
                count = K;
            }
        }
        
        sb.reverse();
        if (sb.length() != 0 && sb.charAt(0) == '-') sb.deleteCharAt(0);
        
        return sb.toString();
    }
    
	public static void main(String[] args) {
		// S = "2-4A0r7-4k", K = 4. Output: "24A0-R74K"
		System.out.println(licenseKeyFormatting("2-4A0r7-4k", 4));
		
		// S = "2-4A0r7-4k", K = 3. Output: "24-A0R-74K"
		System.out.println(licenseKeyFormatting("2-4A0r7-4k", 3));
		
		System.out.println(licenseKeyFormatting("abc-abc", 3));
		
		System.out.println(licenseKeyFormatting("a-a-b-c------", 1));
		
		System.out.println(licenseKeyFormatting("r", 1));
		
		System.out.println(licenseKeyFormatting("----------", 1));
	}

}
