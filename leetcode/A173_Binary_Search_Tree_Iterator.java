package leetcode;
import java.util.*;

//Iterative traverse + stack. Less space usage
public class A173_Binary_Search_Tree_Iterator {
    Stack<TreeNode> stack = new Stack<TreeNode>();
    
    public A173_Binary_Search_Tree_Iterator(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        if (stack.isEmpty())
            throw new NoSuchElementException();
        else {
            TreeNode result = stack.pop();
            TreeNode root = result.right;
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            return result.val;
        }
    }
}
