package leetcode;
import java.util.*;

public class A257_Binary_Tree_Paths {
    private List<Integer> route;
    private List<String> result;
    
    public List<String> binaryTreePaths(TreeNode root) {
        route = new ArrayList<Integer>();
        result = new ArrayList<String>();
        if (root == null) return result;
        helper(root);
        return result;
    }
    
    public void helper(TreeNode root) {
        if (root.left == null && root.right == null) {
            StringBuilder r = new StringBuilder();
            for (Integer node : route) {
                r.append(node);
                r.append("->");
            }
            r.append(root.val);
            result.add(r.toString());
            return;
        }

        route.add(root.val);
        if (root.left != null) helper(root.left);
        if (root.right != null) helper(root.right);
        route.remove(route.size()-1);

    }
}
