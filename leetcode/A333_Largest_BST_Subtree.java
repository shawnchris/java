package leetcode;
/*
Given a binary tree, find the largest subtree which is a Binary Search Tree (BST),
where largest means subtree with largest number of nodes in it.
Note: A subtree must include all of its descendants.
Here's an example:
    10
    / \
  [5] 15
  / \   \ 
[1] [8]  7
The Largest BST Subtree in this case is the highlighted one. 
The return value is the subtree's size, which is 3.
*/
public class A333_Largest_BST_Subtree {
	public int largestBSTSubtree(TreeNode root) {
		int[] res = { 0 };
		helper(root, res);
		return res[0];
	}

	private Result helper(TreeNode root, int[] res) {
		Result cur = new Result();
		if (root == null) {
			cur.isBST = true;
			return cur;
		}
		Result left = helper(root.left, res);
		Result right = helper(root.right, res);
		if (left.isBST && root.val > left.max && right.isBST
				&& root.val < right.min) {
			cur.isBST = true;
			cur.min = Math.min(root.val, left.min);
			cur.max = Math.max(root.val, right.max);
			cur.size = left.size + right.size + 1;
			if (cur.size > res[0]) {
				res[0] = cur.size;
			}
		}
		return cur;
	}

	class Result {
		boolean isBST;
		int min;
		int max;
		int size;

		public Result() {
			isBST = false;
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			size = 0;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
