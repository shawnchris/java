package leetcode;
/*
Number of binary search trees = 
(Number of ways root can be choosen) * (Number of Left binary search sub-trees) * (Number of Right binary search sub-trees)

We can find the number of BSTs recursively as :
choose 1 as root, no element  on the left sub-tree. n-1 elements on the right sub-tree.
Choose 2 as root, 1 element  on the left sub-tree. n-2 elements on the right sub-tree.
Choose 3 as root, 2 element on the left sub-tree. n-3 elements on the right sub-tree.

dp(i) = dp(0)dp(i-1) + dp(1)dp(i-2) + ... + dp(j-1)dp(i-j) + ... + dp(i-1)dp(0)
dp(i) = sum(j=1..i, dp(j−1) * dp(i−j))
init: dp(0) = 1

*/
public class A096_Unique_Binary_Search_Trees {
	public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j <= i; j++) {
                sum += dp[j - 1] * dp[i - j];
            }
            dp[i] = sum;
        }
        
        return dp[n];
    }
}
