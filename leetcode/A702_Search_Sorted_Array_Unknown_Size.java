package leetcode;

/**
 * You have a sorted array of unique elements and an unknown size. You do not have access to the array but you can use
 * the ArrayReader interface to access it. You can call ArrayReader.get(i) that:
 * - returns the value at the ith index (0-indexed) of the secret array (i.e., secret[i]), or
 * - returns 2^31 - 1 if the i is out of the boundary of the array.
 * You are also given an integer target.
 * Return the index k of the hidden array where secret[k] == target or return -1 otherwise.
 * You must write an algorithm with O(log n) runtime complexity.
 */

interface ArrayReader {
    int get(int index);
}

// Split into Two Subproblems
// The array is sorted, i.e. one could try to fit into a logarithmic time complexity. That means two subproblems, and
// both should be done in a logarithmic time:
// - Define search limits, i.e. left and right boundaries for the search.
// - Perform binary search in the defined boundaries.
public class A702_Search_Sorted_Array_Unknown_Size {
    public int search(ArrayReader reader, int target) {
        if (reader.get(0) == target) return 0;

        // search boundaries
        int left = 0, right = 1;
        while (reader.get(right) < target) {
            left = right;
            right <<= 1;
        }

        // binary search
        int pivot, num;
        while (left <= right) {
            pivot = left + ((right - left) >> 1);
            num = reader.get(pivot);

            if (num == target) return pivot;
            if (num > target) right = pivot - 1;
            else left = pivot + 1;
        }

        // there is no target element
        return -1;
    }
}
