package leetcode;

public class A449_Serialize_and_Deserialize_BST {
	static class Codec {

	    // Encodes a tree to a single string.
	    public String serialize(TreeNode root) {
	        if (root == null) return "";
	        StringBuilder sb = new StringBuilder();
	        traverse(root, sb);
	        System.out.println(sb.toString().substring(1));
	        return sb.toString().substring(1);
	    }
	    
	    private void traverse(TreeNode root, StringBuilder sb) {
	        if (root == null) return;
	        sb.append("," + root.val);
	        traverse(root.left, sb);
	        traverse(root.right, sb);
	    }

	    // Decodes your encoded data to tree.
	    public TreeNode deserialize(String data) {
	        if (data == null || data.length() == 0) return null;
	        String[] sa = data.split(",");
	        int[] ia = new int[sa.length];
	        for (int i = 0; i < sa.length; i++) {
	            ia[i] = Integer.parseInt(sa[i]);
	        }
	        return restore(ia, 0, ia.length - 1);
	    }
	    
	    private TreeNode restore(int[] array, int start, int end) {
	        if (start > end) return null;
	        TreeNode root = new TreeNode(array[start]);
	        int p = start + 1;
	        while (p <= end) {
	            if (array[p] < array[start]) p++;
	            else break;
	        }
	        root.left = restore(array, start + 1, p - 1);
	        root.right = restore(array, p, end);
	        return root;
	    }
	}
	
	public static void main(String[] args) {
		Codec c = new Codec();
		c.deserialize("2,1,3");
	}

}
