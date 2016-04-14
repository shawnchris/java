package leetcode;

import java.util.*;

public class A095_Unique_Binary_Search_TreesII {
    private List<TreeNode> helper(int start, int end) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        
        if (start > end) {
            res.add(null);
            return res;
        }
        
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = helper(start, i - 1);
            List<TreeNode> right = helper(i + 1, end);
            for (TreeNode l : left)
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    res.add(root);
                }
        }
        
        return res;
    }
    
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<TreeNode>();
        return helper(1, n);
    }
}
