package leetcode;

public class A255_Verify_Preorder_Sequence_in_BST {
	public boolean verifyPreorder(int[] preorder) {
        return verify(preorder, 0, preorder.length - 1);
    }
    
    private boolean verify(int[] arr, int start, int end) {
        if (start >= end) return true;
        
        int root = arr[start];
        int i = start;
        for (; i <= end; i++) {
            if (arr[i] > root) break;
        }
        boolean right = true;
        if (i <= end) { // has right sub-tree
            int j = i + 1;
            for (; j <= end; j++) {
                if (arr[j] <= root) return false;
            }
            right = verify(arr, i, end);
        }
        if (right) {
            if (verify(arr, start + 1, i - 1)) {
                return true;
            }
        }
        return false;
    }
}
