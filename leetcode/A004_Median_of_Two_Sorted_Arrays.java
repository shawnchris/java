package leetcode;
/*
 * Binary Search solution.
 * Median of two sorted arrays: len = len1 + len2; odd: len/2 th; even: (len/2 th + len/2 + 1 th) / 2.0
 * Find Kth in two sorted arrays: Try to eliminate K/2 every time by comparing K/2 th elements in each array.
 */
public class A004_Median_of_Two_Sorted_Arrays {
	private int findKth(int[] nums1, int start1, int[] nums2, int start2, int k) {
		// Base case 1
		if (start1 >= nums1.length)
			return nums2[start2 + k - 1];
		if (start2 >= nums2.length)
			return nums1[start1 + k - 1];
		// Base case 2
		if (k == 1)
			return Math.min(nums1[start1], nums2[start2]);
		
		int key1 = start1 + k/2 - 1 < nums1.length ?
				nums1[start1 + k/2 - 1] : Integer.MAX_VALUE;
		int key2 = start2 + k/2 - 1 < nums2.length ?
				nums2[start2 + k/2 - 1] : Integer.MAX_VALUE;	
		
		if (key1 < key2)
			return findKth(nums1, start1 + k/2, nums2, start2, k - k/2);
		else
			return findKth(nums1, start1, nums2, start2 + k/2, k - k/2);
		
	}
	
    public double findMedianSortedArrays (int[] nums1, int[] nums2) {
		int len = nums1.length + nums2.length;
		if (len % 2 == 0) {
			return (findKth(nums1, 0, nums2, 0, len / 2) +
					findKth(nums1, 0, nums2, 0, len / 2 + 1)) / 2.0;
		}
		else {
			return findKth(nums1, 0, nums2, 0, len / 2 + 1);
		}
    }
    
	public static void main(String[] args) {


	}

}
