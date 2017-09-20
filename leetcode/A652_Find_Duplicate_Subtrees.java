package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class A652_Find_Duplicate_Subtrees {
    Map<String, TreeNode> map = new HashMap<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        if (root == null) return result;

        traverse(first(root));

        for (TreeNode node : map.values()) {
            if (node != null) {
                result.add(node);
            }
        }

        return result;
    }

    private TreeNode first(TreeNode root) {
        if (root == null) return null;
        if (root.left != null && root.right != null) return root;
        if (root.left != null) return first(root.left);
        return first(root.right);
    }

    private void traverse(TreeNode root) {
        if (root == null) return;

        String s = path(root);
        if (map.containsKey(s)) {
            map.put(s, root);
        }
        else {
            map.put(s, null);
        }

        traverse(root.left);
        traverse(root.right);
    }

    private String path(TreeNode root) {
        if (root == null) return "#";
        return root.val + "," + path(root.left) + "," + path(root.right);
    }
}
