package leetcode;

public class A298_Binary_Tree_Longest_Consecutive_Sequence {
    public int longestConsecutive(TreeNode root) {
        return traverse(root, null, 0);
    }
    
    private int traverse(TreeNode node, TreeNode parent, int parentLen) {
        if (node == null) return 0;
        
        int currentLen = 1;
        if (parent != null && parent.val + 1 == node.val) {
            currentLen = parentLen + 1;
        }
        
        int left = traverse(node.left, node, currentLen);
        int right = traverse(node.right, node, currentLen);
        
        return Math.max(Math.max(left, right), currentLen);
    }
}
