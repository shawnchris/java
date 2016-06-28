package leetcode;

public class A307_Range_Sum_Query_Mutable {
	class SegmentTreeNode {
		public int start, end, sum;
		public SegmentTreeNode left, right;

		public SegmentTreeNode(int start, int end) {
			this.start = start;
			this.end = end;
			this.sum = 0;
			this.left = this.right = null;
		}
	}
	private SegmentTreeNode root = null;

    public A307_Range_Sum_Query_Mutable(int[] nums) {
        if (nums == null || nums.length == 0)
			return;
		root = build(nums, 0, nums.length - 1);
    }

    public void update(int i, int val) {
        if (root == null)
            return;
        modify(root, i, val);
    }

    public int sumRange(int i, int j) {
        if (root == null)
            return -1;
        return query(root, i, j);
    }
    
    private SegmentTreeNode build(int[] arr, int start, int end) {
		if (start > end)
			return null;

		SegmentTreeNode root = new SegmentTreeNode(start, end);

		if (start == end) {
			root.sum = arr[start];
		} else {
			int mid = start + (end - start) / 2;
			root.left = build(arr, start, mid);
			root.right = build(arr, mid + 1, end);
			root.sum = root.left.sum + root.right.sum;
		}

		return root;
	}
	
	private void modify(SegmentTreeNode root, int index, int value) {
		if (index < root.start || index > root.end)
			return;
		if (index == root.start && index == root.end) {
			root.sum = value;
			return;
		}

		int mid = root.start + (root.end - root.start) / 2;
		if (index >= root.start && index <= mid)
			modify(root.left, index, value);
		else
			modify(root.right, index, value);

		root.sum = root.left.sum + root.right.sum;
	}
	
	public int query(SegmentTreeNode root, int start, int end) {
		if (root == null)
			return 0;
		if (root.start == start && root.end == end)
			return root.sum;

		int mid = root.start + (root.end - root.start) / 2;

		// 3 cases: inside left, inside right, across left and right
		if (start >= root.start && end <= mid)
			return query(root.left, start, end);
		else if (start > mid && end <= root.end)
			return query(root.right, start, end);
		else
			return query(root.left, start, mid) + query(root.right, mid + 1, end);
	}
}
