package leetcode;

public class A098_Validate_Binary_Search_Tree {
	public boolean isValidBST(TreeNode root) {
        return isValidNode(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidNode(TreeNode node, long min, long max) {
        if (node == null)
            return true;
        if (node.val >= max || node.val <= min)
            return false;
        return isValidNode(node.left, min, node.val) &&
            isValidNode(node.right, node.val, max);
    }
}
