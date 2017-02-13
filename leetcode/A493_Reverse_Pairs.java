package leetcode;

public class A493_Reverse_Pairs {
	public class Solution {
	    class Node {
			int value, count, greater;
			Node left, right;
			Node (int v, int g) {
				value = v; count = 1; greater = g;
			}
		}
	    public int reversePairs(int[] nums) {
	        int result = 0;
	        if (nums == null || nums.length <= 1) return result;
	        
	        int len = nums.length;
		    Node root = new Node(nums[len - 1], 0);
		    
		    for(int i = len - 2; i >= 0; i--) {
		        Node node = successor(root, nums[i] * 2);
		        if (node != null) {
		    	    result += node.count + node.greater;
		        }
		    	insert(root, nums[i], 0);
		    }

		    return result;
	    }
	    
	    private Node insert(Node root, int value, int greater) {
			if (root == null) return new Node(value, greater);
			
			if (root.value == value) {
				root.count++;
				plusOne(root.left);
			}
			else if (root.value > value) {
				greater += root.count;
				root.left = insert(root.left, value, greater);
			}
			else {
				root.right = insert(root.right, value, greater);
			}
			
			return root;
		}
		
		private void plusOne(Node root) {
		    if (root == null) return;
		    root.greater++;
		    plusOne(root.left);
		    plusOne(root.right);
		}
		
		private Node successor(Node root, int value) {
	        if (root == null) return null;
	    
	        if (root.value <= value) {
	            return successor(root.right, value);
	        }
	        else {
	            Node left = successor(root.left, value);
	            return (left != null) ? left : root;
	        }
	    }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
