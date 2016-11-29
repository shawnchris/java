package leetcode;

public class A285_Inorder_Successor_in_BST {
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p)  {
        return successor(root, p);
    }
    
    private TreeNode successor(TreeNode root, TreeNode p) {
        if (root == null) return null;
    
        if (root.val <= p.val) {
            return successor(root.right, p);
        }
        else {
            TreeNode left = successor(root.left, p);
            return (left != null) ? left : root;
        }
    }
    
    
    private TreeNode predecessor(TreeNode root, TreeNode p) {
        if (root == null) return null;
        
        if (root.val >= p.val) {
            return predecessor(root.left, p);
        }
        else {
            TreeNode right = predecessor(root.right, p);
            return (right != null) ? right : root;
        }
    }
}
