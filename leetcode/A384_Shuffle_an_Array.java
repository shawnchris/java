package leetcode;

import java.util.*;

public class A384_Shuffle_an_Array {
	int[] nums;
	Random rand;

	public A384_Shuffle_an_Array(int[] nums) {
		this.nums = nums;
		rand = new Random();
	}

	/** Resets the array to its original configuration and return it. */
	public int[] reset() {
		return nums;
	}

	/** Returns a random shuffling of the array. */
	public int[] shuffle() {
		int[] result = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			result[i] = nums[i];
		}

		for (int i = 1; i < result.length; i++) {
			int j = rand.nextInt(i + 1);
			int temp = result[i];
			result[i] = result[j];
			result[j] = temp;
		}

		return result;
	}
}
