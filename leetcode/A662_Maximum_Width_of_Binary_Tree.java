package leetcode;

import java.util.HashMap;
import java.util.Map;

public class A662_Maximum_Width_of_Binary_Tree {
    Map<Integer, int[]> map = new HashMap<>();

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        findMax(root, 0, 0);

        int res = 1;
        for (int[] rec : map.values()) {
            res = Math.max(res, rec[1] - rec[0] + 1);
        }

        return res;
    }

    private void findMax(TreeNode root, int level, int pos) {
        if (root == null) return;

        int[] rec = map.get(level);
        if (rec == null) {
            rec = new int[2];
            rec[0] = Integer.MAX_VALUE;
            rec[1] = Integer.MIN_VALUE;
        }

        rec[0] = Math.min(rec[0], pos);
        rec[1] = Math.max(rec[1], pos);
        map.put(level, rec);

        findMax(root.left, level + 1, 2 * pos);
        findMax(root.right, level + 1, 2 * pos + 1);
    }
}
