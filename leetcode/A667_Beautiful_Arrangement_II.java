package leetcode;

public class A667_Beautiful_Arrangement_II {
    public int[] constructArray(int n, int k) {
        int[] res = new int[n];

        if (k == 1) {
            for (int i = 0; i < n; i++) res[i] = i + 1;
            return res;
        }

        int a = 1, b = k + 1, c = 0;
        while (a < b) {
            res[c++] = a;
            res[c++] = b;
            a++; b--;
        }
        if (a == b) res[c++] = a;

        for (int i = k + 2; i <= n; i++) res[c++] = i;

        return res;
    }
}
