package com.whatsapp;

// On-site interview:
// 1. Phone book design
// 2. Convert sorted array to binary tree
// 3. Android list view pre-loading.
// 4. Browser to Server. DNS load balance. Remove element by O(1). Swap two number w/o using the third variable.

// Brain Acton phone interview
// 1. Java: abstract class vs interface; Why create Generic; Unchecked exception;
// 2. Find median of 100 billion messages.

public class Solution {
	public TreeNode sortedArrayToBST(int[] num) {
		if (num.length == 0) return null;
		return helper(num, 0, num.length-1);
	}
	public TreeNode helper(int[] num, int low, int high) {
		// base case
		if (low > high) return null;
		
		// divide and conquer
		int mid = low + (high - low) / 2;
		TreeNode node = new TreeNode(num[mid]);
		node.left = helper(num, low, mid - 1);
		node.right = helper(num, mid + 1, high);
		
		return node;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
