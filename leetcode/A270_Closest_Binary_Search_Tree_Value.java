package leetcode;

public class A270_Closest_Binary_Search_Tree_Value {
    public int closestValue(TreeNode root, double target) {
        
        if (target == root.val || root.left == null && root.right == null)
            return root.val;
        
        int closest;
        if (target > root.val) {
            if (root.right == null) {
                return root.val;
            }
            closest = closestValue(root.right, target);
        }
        else {
            if (root.left == null) {
                return root.val;
            }
            closest = closestValue(root.left, target);
        }
        
        if (Math.abs(closest - target) <= Math.abs(root.val - target)) {
            return closest;
        }
        else {
            return root.val;
        }
    }
}
