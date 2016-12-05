package leetcode;

public class A333_Largest_BST_Subtree {
    int max = 0;
    public int largestBSTSubtree(TreeNode root) {
        traverse(root);
        return max;
    }
    
    private int[] traverse(TreeNode root) {
        //Base case
        if (root == null) {
            // {size, lower bound, upper bound}
            return new int[] {0, Integer.MAX_VALUE, Integer.MIN_VALUE};
        }
        
        int[] left = traverse(root.left);
        int[] right = traverse(root.right);
        
        if (left[0] == -1 || right[0] == -1 || root.val <= left[2] || root.val >= right[1]) {
            return new int[] {-1, 0, 0};
        }
        
        int[] result = new int[3];
        result[0] = left[0] + right[0] + 1;
        result[1] = Math.min(root.val, left[1]);
        result[2] = Math.max(root.val, right[2]);
        max = Math.max(max, result[0]);
        
        return result;
    }
}
