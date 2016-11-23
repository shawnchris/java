package leetcode;

public class A250_Count_Univalue_Subtrees {
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) return 0;
        return traverse(root)[0];
    }
    
    private int[] traverse(TreeNode root) {
        if (root == null) {
            return new int[] {0, 1};
        }
        int[] left = traverse(root.left);
        int[] right = traverse(root.right);
        if (left[1] == 1 && right[1] == 1 && 
            (root.left == null || root.left.val == root.val) &&
            (root.right == null || root.right.val == root.val)) {
                return new int[] {left[0] + right[0] + 1, 1};
        }
        else {
            return new int[] {left[0] + right[0], 0};
        }
    }
}
