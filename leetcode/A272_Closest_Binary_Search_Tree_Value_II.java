package leetcode;
import java.util.*;

public class A272_Closest_Binary_Search_Tree_Value_II {
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
        double s = target, p = target;
        List<Integer> result = new ArrayList<Integer>();
        
        if (inTree(root, target)) {
            result.add((int)target);
            k--;
        }
        for (int i = 0; i < k; i++) {
            TreeNode sn = successor(root, s);
            TreeNode pn = predecessor(root, p);
            if (sn == null && pn == null) break;
            if (sn != null && pn == null) {
                result.add(sn.val);
                s = sn.val;
            }
            else if (sn == null && pn != null) {
                result.add(pn.val);
                p = pn.val;
            }
            else {
                if (Math.abs(sn.val - target) < Math.abs(pn.val - target)) {
                    result.add(sn.val);
                    s = sn.val;
                }
                else {
                    result.add(pn.val);
                    p = pn.val;
                }
            }
        }
        
        return result;
    }
    
    private boolean inTree(TreeNode root, double target) {
        if (root == null) return false;
        if (root.val == target) return true;
        if (root.val > target) {
            return inTree(root.left, target);
        }
        else {
            return inTree(root.right, target);
        }
    }
    
    private TreeNode successor(TreeNode root, double target) {
        if (root == null) return null;
    
        if (root.val <= target) {
            return successor(root.right, target);
        }
        else {
            TreeNode left = successor(root.left, target);
            return (left != null) ? left : root;
        }
    }
    
    
    private TreeNode predecessor(TreeNode root, double target) {
        if (root == null) return null;
        
        if (root.val >= target) {
            return predecessor(root.left, target);
        }
        else {
            TreeNode right = predecessor(root.right, target);
            return (right != null) ? right : root;
        }
    }
}
