package leetcode;

public class A405_Convert_a_Number_to_Hexadecimal {
    public String toHex(int num) {
        if (num == 0) return "0";
        
        char[] hexArray = "0123456789abcdef".toCharArray();
        String result = "";
        while (num != 0) {
            char hex = hexArray[num & 0x0F];
            result = hex + result;
            num >>>= 4;
        }
        
        return result;
    }
}
