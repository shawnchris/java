package leetcode;

public class A404_Sum_of_Left_Leaves {
    int sum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return sum;
        traverse(root, false);
        return sum;
    }
    private void traverse(TreeNode root, boolean isLeft) {
        if (isLeft && root.left == null && root.right == null) {
            sum += root.val;
            return;
        }
        if (root.left != null) {
            traverse(root.left, true);
        }
        if (root.right != null) {
            traverse(root.right, false);
        }
    }
}
