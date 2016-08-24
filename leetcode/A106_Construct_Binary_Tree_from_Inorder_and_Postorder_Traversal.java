package leetcode;

public class A106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(inorder, postorder, 0, inorder.length - 1, postorder.length - 1);
    }
    
    private TreeNode helper(int[] inorder, int[] postorder, int inStart, int inEnd, int postEnd) {
        if (inStart > inEnd) return null;
        
        int inRoot = 0;
        for (int i = inStart; i <= inEnd; i++)
            if (inorder[i] == postorder[postEnd]) {
                inRoot = i;
                break;
            }
        
        TreeNode root = new TreeNode(postorder[postEnd]);
        root.left = helper(inorder, postorder, inStart, inRoot - 1, postEnd - (inEnd - inRoot) - 1);
        root.right = helper(inorder, postorder, inRoot + 1, inEnd, postEnd - 1);
        
        return root;
    }
}
