package leetcode;
import java.util.*;

public class A113_Path_Sum_II {
    List<List<Integer>> lista = new ArrayList<List<Integer>>();
	List<Integer> listb = new ArrayList<Integer>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root==null) return lista;
        listb.add(root.val);
		if (root.val==sum) {
			if (root.left==null && root.right==null) {
				List<Integer> listc = new ArrayList<Integer>(listb);
				lista.add(listc);
			}
		}
		pathSum(root.left, sum-root.val);
		pathSum(root.right, sum-root.val);
		listb.remove(listb.size()-1);
		return lista;
    }
}
