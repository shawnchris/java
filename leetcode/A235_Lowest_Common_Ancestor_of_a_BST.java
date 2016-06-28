package leetcode;

public class A235_Lowest_Common_Ancestor_of_a_BST {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;
            
        if ((root.val - p.val) * (root.val - q.val) <= 0)
            return root;
            
        if (root.val > p.val && root.val > q.val)
            return lowestCommonAncestor(root.left, p, q);
        else
            return lowestCommonAncestor(root.right, p, q);
        
    }
}
