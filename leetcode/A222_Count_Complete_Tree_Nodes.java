package leetcode;

public class A222_Count_Complete_Tree_Nodes {

	int getLeftHeight(TreeNode root) {
        int height = 0;
        while (root != null) { 
            root = root.left;
            height++;
        }
        return height;
    }
    
    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        int left_height = getLeftHeight(root.left);
        int right_height = getLeftHeight(root.right);

        if(left_height == right_height)
       		return (1 << left_height) + countNodes(root.right);

        return (1 << right_height) + countNodes(root.left);
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
