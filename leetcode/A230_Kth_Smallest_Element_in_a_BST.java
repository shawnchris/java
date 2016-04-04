package leetcode;
/*
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
 * How would you optimize the kthSmallest routine?
 * What if you could modify the BST node's structure?
 * 
 * Modify BST node's structure to include node count. Then every time select one route from left or right.
 * 
 * The optimal runtime complexity is O(height of BST).
 */
public class A230_Kth_Smallest_Element_in_a_BST {
	
	int sk = 0;
	public int kthSmallest(TreeNode root, int k) {
        if (root == null) return -1;

        int left = kthSmallest(root.left, k);
        if (left != -1) return left;
        
        sk++;
        if (sk == k) return root.val;
        
        return kthSmallest(root.right, k);
    }
	
	public int kthSmallest2(TreeCountNode root, int k) {
		int leftCount = 0;
		if (root.left != null)
			leftCount = root.left.count;
		
		if (leftCount >= k)
			return kthSmallest2(root.left, k);
		
		if (leftCount == k - 1)
			return root.val;
		
		return kthSmallest2(root.right, k - leftCount - 1);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
