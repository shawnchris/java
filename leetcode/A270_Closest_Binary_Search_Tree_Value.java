package leetcode;
/*
 * Problem Description:
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 * Note:
 * Given target value is a floating point.
 * You are guaranteed to have only one unique value in the BST that is closest to the target.
 */
public class A270_Closest_Binary_Search_Tree_Value {

	public int closestValue(TreeNode root, double target) {
		if (root == null) // Base case
			return Integer.MAX_VALUE;
		
		int left = closestValue(root.left, target);
		int right = closestValue(root.right, target);
		double left_delta = Math.abs(left - target);
		double right_delta = Math.abs(right - target);
		double root_delta = Math.abs(root.val - target);
		
		if (left_delta <= right_delta && left_delta <= root_delta)
			return left;
		if (right_delta <= left_delta && right_delta <= root_delta)
			return right;
		return root.val;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
