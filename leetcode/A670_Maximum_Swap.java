package leetcode;

public class A670_Maximum_Swap {
    public int maximumSwap(int num) {
        char[] arr = (num + "").toCharArray();
        for (int i = 0; i < arr.length - 1; i++) {
            int k = -1, idx = -1;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] >= k) {
                    k = arr[j];
                    idx = j;
                }
            }
            if (arr[i] < k) {
                arr[idx] = arr[i];
                arr[i] = (char)k;
                int res = 0;
                for (int l = 0; l < arr.length; l++) {
                    res = res * 10 + arr[l] - '0';
                }
                return res;
            }
        }

        return num;
    }
}
