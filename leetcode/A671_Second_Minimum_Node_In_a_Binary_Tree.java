package leetcode;

import java.util.HashSet;
import java.util.Set;

public class A671_Second_Minimum_Node_In_a_Binary_Tree {
    Set<Integer> set = new HashSet<>();
    public int findSecondMinimumValue(TreeNode root) {
        traverse(root);

        if (set.size() < 2) return -1;

        int s1 = Integer.MAX_VALUE, s2 = Integer.MAX_VALUE;
        for (int i : set) {
            if (i < s1) {
                s2 = s1;
                s1 = i;
            }
            else if (i < s2) {
                s2 = i;
            }
        }

        return s2;
    }

    private void traverse(TreeNode root) {
        if (root == null) return;

        set.add(root.val);

        traverse(root.left);
        traverse(root.right);
    }
}
