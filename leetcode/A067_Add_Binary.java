package leetcode;

public class A067_Add_Binary {
    public String addBinary(String a, String b) {
        if (a == null || a.length() == 0)
            return b;
        if (b == null || b.length() == 0)
            return a;
        char[] aArray = a.toCharArray();
        char[] bArray = b.toCharArray();
        String result = "";

        int i = aArray.length - 1;
        int j = bArray.length - 1;
        int aByte = 0, bByte = 0, carry = 0, res = 0;

        while(i > -1 || j > -1 || carry == 1) {
            if (i > -1) aByte = aArray[i] - '0';
            else aByte = 0;
            if (j > -1) bByte = bArray[j] - '0';
            else bByte = 0;
            res = aByte ^ bByte ^ carry;
            carry = ((aByte + bByte + carry) >= 2) ? 1 : 0;
            result = res + result;
            i--;
            j--;
        }
        return result;
    }
}
