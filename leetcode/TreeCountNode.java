package leetcode;

public class TreeCountNode {
	int val;
	int count;
	TreeCountNode left, right;
	TreeCountNode(int val) {
		this.val = val;
		this.count = 1;
		left = right = null;
	}
}
