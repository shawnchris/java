package leetcode;

public class A538_Convert_BST_to_Greater_Tree {
    int sum = 0;
    
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        
        convertBST(root.right);
        
        root.val += sum;
        sum = root.val;
        
        convertBST(root.left);
        
        return root;
    }
}
