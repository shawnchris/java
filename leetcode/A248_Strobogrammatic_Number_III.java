package leetcode;

public class A248_Strobogrammatic_Number_III {
    int count = 0;
    public int strobogrammaticInRange(String low, String high) {
        for (int i = low.length(); i <= high.length(); i++) {
            helper(i, i, new char[i], low, high);
        }
        return count;
    }
    
    private void helper(int max, int left, char[] arr, String low, String high) {
        // Base cases
        if (left == 0) {
            String res = new String(arr);
            if (!((res.length() == low.length() && res.compareTo(low) < 0) ||
                res.length() == high.length() && res.compareTo(high) > 0)) {
                    count++;
                }
            return;
        }
        
        if (left == 1) {
            arr[max / 2] = '0';
            helper(max, left - 1, arr, low, high);
            arr[max / 2] = '1';
            helper(max, left - 1, arr, low, high);
            arr[max / 2] = '8';
            helper(max, left - 1, arr, low, high);
            return;
        }
        
        int i = (max - left) / 2, j = max - i - 1;
        if (left != max) {
            arr[i] = '0';
            arr[j] = '0';
            helper(max, left - 2, arr, low, high);
        }
        arr[i] = '1';
        arr[j] = '1';
        helper(max, left - 2, arr, low, high);
        arr[i] = '6';
        arr[j] = '9';
        helper(max, left - 2, arr, low, high);
        arr[i] = '8';
        arr[j] = '8';
        helper(max, left - 2, arr, low, high);
        arr[i] = '9';
        arr[j] = '6';
        helper(max, left - 2, arr, low, high);
    }
}
