package leetcode;

public class A114_Flatten_Binary_Tree_to_Linked_List {
    private TreeNode findTail(TreeNode root) {
        while (root.right != null)
            root = root.right;
        return root;
    }
    
    public void flatten(TreeNode root) {
        if (root == null) return;
        
        flatten(root.left);
        flatten(root.right);
        
        if (root.left != null) {
            TreeNode temp = root.right;
            TreeNode leftTail = findTail(root.left);
            root.right = root.left;
            leftTail.right = temp;
            root.left = null;
        }
    }
}
