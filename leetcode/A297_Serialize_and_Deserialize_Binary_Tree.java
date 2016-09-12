package leetcode;
import java.util.*;

public class A297_Serialize_and_Deserialize_Binary_Tree {
	public class Codec {

	    // Encodes a tree to a single string.
	    public String serialize(TreeNode root) {
	        if (root == null) return null;
	        StringBuilder data = new StringBuilder();
	        
	        Queue<TreeNode> queue = new LinkedList<TreeNode>();
	        queue.add(root);
	        while (!queue.isEmpty()) {
	            int size = queue.size();
	            for (int i = 0; i < size; i++) {
	                TreeNode parent = queue.poll();
	                if (parent != null) {
	                    data.append("," + parent.val);
	                    queue.add(parent.left);
	                    queue.add(parent.right);
	                }
	                else {
	                    data.append(",#");
	                }
	            }
	        }
	        
	        return data.substring(1);
	    }

	    // Decodes your encoded data to tree.
	    public TreeNode deserialize(String data) {
	        if (data == null || data.length() == 0) return null;
	        String[] nodes = data.split(",");
			int len = nodes.length;
			if (len == 0) return null;
			if (nodes[0].equals("#")) return null;
			
			Queue<TreeNode> queue = new LinkedList<TreeNode>();
			TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
			queue.add(root);
			int p = 1;
			while (!queue.isEmpty()) {
				int size = queue.size();
				for (int i = 0; i < size; i++) {
					TreeNode parent = queue.poll();
					if (p < len) {
						String s = nodes[p];
						p++;
						if (!s.equals("#")) {
							TreeNode left = new TreeNode(Integer.parseInt(s));
							parent.left = left;
							queue.add(left);
						}
					}
					if (p < len) {
						String s = nodes[p];
						p++;
						if (!s.equals("#")) {
							TreeNode right = new TreeNode(Integer.parseInt(s));
							parent.right = right;
							queue.add(right);
						}
					}
				}
			}
			
			return root;
	    }
	}
}
