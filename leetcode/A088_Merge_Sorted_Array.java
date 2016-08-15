package leetcode;

public class A088_Merge_Sorted_Array {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m + n - 1;
        int i = m - 1, j = n - 1;
        while (i > -1 && j > -1) {
            if (nums1[i] < nums2[j]) {
                nums1[p] = nums2[j];
                j--;
            }
            else {
                nums1[p] = nums1[i];
                i--;
            }
            p--;
        }
        while (j > -1) {
            nums1[j] = nums2[j];
            j--;
        }
    }
}
