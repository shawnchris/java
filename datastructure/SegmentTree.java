package datastructure;

public class SegmentTree {
	class SegmentTreeNode {
		int start, end;
		int sum;
		SegmentTreeNode left, right;
		public SegmentTreeNode(int start, int end) {
			this.start = start; this.end = end;
			this.left = this.right = null;
		}
	}
	
	SegmentTreeNode root;
	
	public SegmentTreeNode build(int[] nums, int start, int end) {
		if (start > end) return null;
		
        SegmentTreeNode root = new SegmentTreeNode(start, end);
        if(start == end) {
        	root.sum = nums[start];
            return root;
        }
        
        int mid = (start + end) / 2;
        root.left = build(nums, start, mid);
        root.right = build(nums, mid + 1, end);
           
        root.sum = root.left.sum + root.right.sum;
        return root;
    }
	
	public int query(SegmentTreeNode root, int start, int end) {
		if (root.start == start && root.end == end) {
			return root.sum;
		}
		
		int mid = (root.start + root.end) / 2;
		if (root.start <= start && mid >= end) {
			return query(root.left, start, mid);
		}
		else if (mid + 1 <= start && root.end >= end) {
			return query(root.right, mid + 1, end);
		}
		else {
			return query(root.left, start, mid) + query(root.right, mid + 1, end);
		}
	}
	
	public void modify(SegmentTreeNode root, int idx, int val) {
		if (root.start == idx && root.end == idx) {
			root.sum = val;
			return;
		}
		
		int mid = (root.start + root.end) / 2;
		if (idx <= mid) {
			modify(root.left, idx, val);
		}
		else {
			modify(root.right, idx, val);
		}
		
		root.sum = root.left.sum + root.right.sum;
		
	}

	
	public static void main(String[] args) {
		SegmentTree st = new SegmentTree();
		int[] nums = {1, 3, 5};
		SegmentTreeNode root = st.build(nums, 0, 2);
		System.out.println(st.query(root, 0, 2));
		st.modify(root, 1, 2);
		System.out.println(st.query(root, 0, 2));
	}

}
