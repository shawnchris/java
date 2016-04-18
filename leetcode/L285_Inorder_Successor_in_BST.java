package leetcode;
/*
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * Note: If the given node has no in-order successor in the tree, return null.
 */
public class L285_Inorder_Successor_in_BST {
	// O(n) recursive solution
	public TreeNode search1(TreeNode root, TreeNode predecessor) {
		//base case
		if (root == null || predecessor == null)
			return null;
		
		//traverse left
		TreeNode left = search1(root.left, predecessor);
		if (left != null)
			return left;
		//do sth with root
		if (root.val > predecessor.val) return root;
		//traverse right
		return search1(root.right, predecessor);
	}
	
	// O(lgn) recursive solution
	public TreeNode search2(TreeNode root, TreeNode predecessor) {
		//base case
		if (root == null || predecessor == null)
			return null;
		
		if (root.val > predecessor.val) { //successor in left subtree or itself
			TreeNode left = search2(root.left, predecessor);
			if (left != null)
				return left;
			else
				return root;
		}
		else { //otherwise successor in right subtree or doesn't exit
			return search2(root.right, predecessor);
		}
	}
	
	// O(lgn) iterative solution
	public TreeNode search3(TreeNode root, TreeNode predecessor) {
		if (root == null || predecessor == null)
			return null;
		
		TreeNode successor = null;
		while (root != null) {
			if (root.val > predecessor.val) {
				successor = root;
				root = root.left;
			}
			else {
				root = root.right;
			}
		}
		
		return successor;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
