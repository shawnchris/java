package leetcode;

public class A450_Delete_Node_in_a_BST {
	public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;
        if (root.val == key) {
            // Case 1 - no child
            if (root.left == null && root.right == null) return null;
            // Case 2 - only has left or right child
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            // Case 3 - has both children. either promote right most in left sub-tree or left most in right sub-tree
            TreeNode leftMax = root.left;
            while (leftMax.right != null) {
                leftMax = leftMax.right;
            }
            root.val = leftMax.val;
            root.left = deleteNode(root.left, leftMax.val);
            return root;
        }
        else {
            if (root.val > key) {
                root.left = deleteNode(root.left, key);
            }
            else {
                root.right = deleteNode(root.right, key);
            }
            return root;
        }
    }
}
