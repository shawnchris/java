package leetcode;

public class A109_Convert_Sorted_List_to_Binary_Search_Tree {
    // If traverse a tree InOrder, that will the same sequence with the list.
    ListNode list = null;
	public TreeNode sortedListToBST(ListNode head) {
		if (head == null) return null;
		list = head;
		ListNode counter = head;
		int size = 0;
		while (counter != null) {
			size++;
			counter = counter.next;
		}
		return helper(size);
	}
	public TreeNode helper(int size) {
		if (size == 0) return null;
		
		TreeNode left = helper(size / 2);
		TreeNode root = new TreeNode(list.val);
		list = list.next;
		TreeNode right = helper(size - size / 2 - 1);
		root.left = left;
		root.right = right;
		
		return root;
	}
}
