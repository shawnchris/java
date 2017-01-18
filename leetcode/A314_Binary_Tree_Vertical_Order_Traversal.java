package leetcode;
import java.util.*;

public class A314_Binary_Tree_Vertical_Order_Traversal {
    public List<List<Integer>> verticalOrder(TreeNode root) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;
        
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        Queue<TreeNode> qNode = new LinkedList<>();
        Queue<Integer> qIndex = new LinkedList<>();
        qNode.add(root);
        qIndex.add(0);
        
        while (!qNode.isEmpty()) {
            TreeNode node = qNode.poll();
            int index = qIndex.poll();
            
            List<Integer> list = map.get(index);
            if (list == null) list = new ArrayList<Integer>();
            list.add(node.val);
            map.put(index, list);
            
            if (node.left != null) {
                qNode.add(node.left);
                qIndex.add(index - 1);
            }
            if (node.right != null) {
                qNode.add(node.right);
                qIndex.add(index + 1);
            }
        }
        
        List<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        for (int key : keys) {
            result.add(map.get(key));
        }
        
        return result;
    }
}
