package leetcode;
/* Two possibilities: Node 1 and Node 2 are adjacent; are not.
 * 1. 1, 2, 3, 4, 5, 6 -> 1, 3, 2, 4, 5, 6
 * 2. 1, 2, 3, 4, 5, 6 -> 1, 5, 3, 4, 2, 6
*/

public class A099_Recover_Binary_Search_Tree {
	// Two pointers to record wrong nodes
    TreeNode first = null, second = null;
    // Pointer to record previously traversed node
    TreeNode previous = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {
    	// Traverse the tree IN-ORDER to find the wrong nodes
        traverse(root);
        
        // Swap values of the wrong nodes
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void traverse(TreeNode root) {
    	if (root == null) return;
        
        traverse(root.left);
        
        if (previous.val > root.val) { // Find wrong node
            if (first == null) first = previous;
            if (first != null) second = root;
        }
        previous = root;
        
        traverse(root.right);
    }

}
