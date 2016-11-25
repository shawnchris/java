package leetcode;
import java.util.*;

public class A314_Binary_Tree_Vertical_Order_Traversal {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;
        
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        Queue<TreeNode> qNode = new LinkedList<>();
        Queue<Integer> qLevel = new LinkedList<>();
        qNode.add(root);
        qLevel.add(0);
        
        // BFS
        while (!qNode.isEmpty()) {
            TreeNode node = qNode.poll();
            int level = qLevel.poll();
            List<Integer> list = map.get(level);
            if (list == null) {
                list = new ArrayList<Integer>();
            }
            list.add(node.val);
            map.put(level, list);
            
            if (node.left != null) {
                qNode.add(node.left);
                qLevel.add(level - 1);
            }
            if (node.right != null) {
                qNode.add(node.right);
                qLevel.add(level + 1);
            }
        }
        
        Integer[] levels = map.keySet().toArray(new Integer[map.keySet().size()]);
        Arrays.sort(levels);
        for (int level : levels) {
            result.add(map.get(level));
        }
        
        return result;
    }
}
