package leetcode;

public class A156_Binary_Tree_Upside_Down {
    TreeNode newRoot = null;
    
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null) return null;
        traverse(root);
        return newRoot;
    }
    
    private TreeNode traverse(TreeNode root) {
        if (root.left == null) {
            newRoot = root;
            return root;
        }
        
        TreeNode left = traverse(root.left);
        left.left = root.right;
        left.right = root;
        root.left = null;
        root.right = null;
        return root;
    }
}
