package leetcode;

public class A108_Convert_Sorted_Array_to_Binary_Search_Tree {
    public TreeNode sortedArrayToBST(int[] num) {
		if (num.length == 0) return null;
		return helper(num, 0, num.length-1);
	}
	public TreeNode helper(int[] num, int low, int high) {
		// base case
		if (low > high) return null;
		
		// divide and conquer
		int mid = low + (high - low) / 2;
		TreeNode node = new TreeNode(num[mid]);
		node.left = helper(num, low, mid - 1);
		node.right = helper(num, mid + 1, high);
		
		return node;
	}
}
