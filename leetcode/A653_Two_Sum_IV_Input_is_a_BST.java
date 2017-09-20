package leetcode;

import java.util.ArrayList;
import java.util.List;

public class A653_Two_Sum_IV_Input_is_a_BST {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;

        List<Integer> list = new ArrayList<>();
        inOrder(root, list);

        int i = 0, j = list.size() - 1;
        while (i < j) {
            int sum = list.get(i) + list.get(j);
            if (sum == k) return true;
            if (sum < k) {
                i++;
            }
            else {
                j--;
            }
        }

        return false;
    }

    private void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) return;

        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }
}
