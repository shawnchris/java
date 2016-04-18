package leetcode;
import java.util.*;
/*
 * Follow up of Two Sum but Input array is sorted.
 * Assume there is no duplicate number.
 * O(nlgn) solution.
 */
public class L167_Two_SumII {
	private Integer binarySearch(int[] a, int start, int end, int target) {
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (target < a[mid])
				end = mid;
			else
				start = mid;
		}
		if (a[start] == target)
			return start;
		else if (a[end] == target)
			return end;
		return null;
	}

	public List<List<Integer>> twoSum(int[] a, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for (int i = 0; i < a.length; i++) {
			if (a[i] > target) break;
			Integer j = binarySearch(a, i + 1, a.length - 1, target - a[i]);
			if (j != null) {
				List<Integer> res = new ArrayList<>();
				res.add(a[i]);
				res.add(a[j]);
				result.add(res);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		L167_Two_SumII ts = new L167_Two_SumII();
		int n = 100, low = -100, high = 500;
		int target = 100;
		Random rand = new Random();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = rand.nextInt(high - low) + low;
			System.out.print(a[i] + " ");
		}
		Arrays.sort(a);
		System.out.println("\n" + ts.twoSum(a, target));
		
	}

}
