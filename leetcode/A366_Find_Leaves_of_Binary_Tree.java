package leetcode;
import java.util.*;

public class A366_Find_Leaves_of_Binary_Tree {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        while (root != null) {
            List<Integer> res = new ArrayList<Integer>();
            root = collect(root, res);
            result.add(res);
        }

        return result;
    }
    private TreeNode collect(TreeNode root, List<Integer> res) {
        // leaf
        if (root.left == null && root.right == null) {
            res.add(root.val);
            return null;
        }
        
        // not leaf
        if (root.left != null) {
            root.left = collect(root.left, res);
        }
        if (root.right != null) {
            root.right = collect(root.right, res);
        }
        return root;
    }
}
