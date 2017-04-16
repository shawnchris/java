package leetcode;

public class A543_Diameter_of_Binary_Tree {
    public int diameterOfBinaryTree(TreeNode root) {
        int res = 0;
        if(root == null) return res;
        
        int cur = maxDepth(root.left) + maxDepth(root.right);
        int left = diameterOfBinaryTree(root.left);
        int right = diameterOfBinaryTree(root.right);
        
        return Math.max(cur, Math.max(left, right));
    }
    public int maxDepth(TreeNode root){
        if(root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
