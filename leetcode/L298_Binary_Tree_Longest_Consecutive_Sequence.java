package leetcode;
/*
Given a binary tree, find the length of the longest consecutive sequence path.
The path refers to any sequence of nodes from some starting node to any node in
the tree along the parent-child connections. The longest consecutive path need 
to be from parent to child (cannot be the reverse).
For example,
   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.
   2
    \
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
 */
public class L298_Binary_Tree_Longest_Consecutive_Sequence {
	int max = 0;
	private void traverse(TreeNode root, int count) {
		//traverse left
		if (root.left != null) {
			int leftcount = root.left.val - root.val == 1 ? count + 1 : 1;
			traverse(root.left, leftcount);
		}
		//do sth with root
		if (count > max) max = count;
		//traverse right
		if (root.right != null) {
			int rightcount = root.right.val - root.val == 1 ? count + 1 : 1;
			traverse(root.right, rightcount);
		}
	}
	
	public int longestConsecutive(TreeNode root) {
		if (root == null) return 0;
		traverse(root, 1);
		return max;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
