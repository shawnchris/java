package leetcode;
import java.util.*;
//Iterative solution
public class A145_Binary_Tree_Postorder_Traversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) return result;
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode prev = null;
        
        while (!stack.isEmpty()) {
            TreeNode curr = stack.peek();
            if (prev == null || prev.left == curr || prev.right == curr) {
                //traverse down the tree
                if (curr.left != null)
                    stack.push(curr.left);
                else if (curr.right != null)
                    stack.push(curr.right);
            }
            else if (curr.left == prev) {
                //traverse up from left
                if (curr.right != null)
                    stack.push(curr.right);
            }
            else {
                //traverse up from right
                result.add(curr.val);
                stack.pop();
            }
            prev = curr;
        }
        
        return result;
    }
}
