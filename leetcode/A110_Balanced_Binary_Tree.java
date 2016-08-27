package leetcode;

public class A110_Balanced_Binary_Tree {
	// Leverage maxDepth function handily
    public boolean isBalanced(TreeNode root) {
        return maxDepth(root) != -1;
    }

    private int maxDepth(TreeNode n){
        if(n==null) return 0;
        int leftDepth = maxDepth(n.left);
        int rightDepth = maxDepth(n.right);
        if (leftDepth == -1 || rightDepth == -1 || Math.abs(leftDepth - rightDepth) > 1)
            return -1;
        return Math.max(leftDepth, rightDepth) + 1;
   }
}
