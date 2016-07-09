package interview.uber;

public class Binary_Tree_Longest_Distance {
	int distance = 0;
	
	public int maxDepth(TreeNode root) {
		if (root == null) return 0;
		
		int left = maxDepth(root.left);
		int right = maxDepth(root.right);
		
		distance = Math.max(distance, left + right);
		
		return Math.max(left, right) + 1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
