package leetcode;

public class A060_Permutation_Sequence {
    StringBuilder result;
    
    public String getPermutation(int n, int k) {
        if (n == 0) return "";
        result = new StringBuilder();
        int fact = 1;
        for (int i = 2; i <= n; i++)
            fact *= i;
        
        helper(fact, n, k, new boolean[n]);
        
        return result.toString();
    }
    
    private void helper(int total, int subtree, int k, boolean[] used) {
        if (subtree == 0) {
            return;
        }
        
        int width = total / subtree;
        for (int i = 0; i < subtree; i++) { // for each interval
            int start = i * width + 1;
            int end = start + width - 1;
            if (start <= k && end >= k) { // k in ith interval
                int c = -1, j = 0;
                for (; j < used.length; j++) { // find ith unused element
                    if (!used[j]) {
                        c++;
                    }
                    if (c == i) {
                        break;
                    }
                }
                // j + 1 is the next number is the sequence
                result.append(j + 1);
                used[j] = true;
                helper(width, subtree - 1, k - (width) * i, used);
            }
        }
    }
}
