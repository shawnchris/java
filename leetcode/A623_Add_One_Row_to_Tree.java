package leetcode;

public class A623_Add_One_Row_to_Tree {
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        return preOrder(root, v, d, 1, 0);
    }

    private TreeNode preOrder(TreeNode root, int v, int d, int level, int direction) {
        if (d == level) {
            TreeNode newNode = new TreeNode(v);
            if (direction == 0) {
                newNode.left = preOrder(root, v, d, level + 1, 0);
            }
            else {
                newNode.right = preOrder(root, v, d, level + 1, 1);
            }
            return newNode;
        }

        if (root == null) return null;

        root.left = preOrder(root.left, v, d, level + 1, 0);
        root.right = preOrder(root.right, v, d, level + 1, 1);

        return root;
    }
}
