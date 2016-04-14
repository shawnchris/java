package leetcode;

public class A099_Recover_Binary_Search_Tree {
	TreeNode firstElement = null;
    TreeNode secondElement = null;
    TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {

        // In order traversal to find the two elements
        traverse(root);

        // Swap the values of the two nodes
        int temp = firstElement.val;
        firstElement.val = secondElement.val;
        secondElement.val = temp;
    }

    private void traverse(TreeNode root) {

        if (root == null)
            return;

        traverse(root.left);
        
        if (prevElement.val > root.val) {
            if (firstElement == null)
                firstElement = prevElement;
            if (firstElement != null)
                secondElement = root;
        }        
        prevElement = root;

        traverse(root.right);
    }
}
