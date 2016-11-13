package leetcode;
import java.util.*;

public class A437_Path_Sum_III {
    int result = 0;
    
    public int pathSum(TreeNode root, int sum) {
        traverse(root, new ArrayList<Integer>(), sum);
        return result;
    }
    
    private void traverse(TreeNode root, ArrayList<Integer> path, int target) {
        if (root == null) return;
        
        int sum = root.val;
        if (sum == target) result++;
        
        for (int i = path.size() - 1; i >= 0; i--) {
            sum += path.get(i);
            if (sum == target) result ++;
        }
        
        path.add(root.val);
        traverse(root.left, path, target);
        traverse(root.right, path, target);
        path.remove(path.size() - 1);
    }
}
