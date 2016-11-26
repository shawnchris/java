package leetcode;

public class A246_Strobogrammatic_Number {
    public boolean isStrobogrammatic(String num) {
        char[] arr = (num + "").toCharArray();
        int i = 0, j = arr.length - 1;
        char[] map = new char[10];
        map[0] = '0';
        map[1] = '1';
        map[6] = '9';
        map[8] = '8';
        map[9] = '6';
        while (i <= j) {
            if (map[arr[i] - '0'] != arr[j]) return false;
            i++;
            j--;
        }
        
        return true;
    }
}
