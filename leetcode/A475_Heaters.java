package leetcode;

import java.util.Arrays;

public class A475_Heaters {
	public static int findRadius(int[] houses, int[] heaters) {
		Arrays.sort(heaters);
		int result = Integer.MIN_VALUE;

		for (int house : houses) {
			int index = Arrays.binarySearch(heaters, house);
			if (index < 0) {
				index = -(index + 1);
			}
			int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
			int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;

			result = Math.max(result, Math.min(dist1, dist2));
		}

		return result;
	}

	public static void main(String[] args) {
		int[] ho1 = { 1, 2, 3 };
		int[] he1 = { 2 };
		System.out.println(findRadius(ho1, he1));
		int[] ho2 = { 1, 2, 3, 4 };
		int[] he2 = { 1, 4 };
		System.out.println(findRadius(ho2, he2));
		int[] ho3 = { 1, 2, 3, 4 };
		int[] he3 = { 1 };
		System.out.println(findRadius(ho3, he3));
		int[] ho4 = { 282475249, 622650073, 984943658, 144108930, 470211272, 101027544, 457850878, 458777923 };
		int[] he4 = { 823564440, 115438165, 784484492, 74243042, 114807987, 137522503, 441282327, 16531729, 823378840,
				143542612 };
		System.out.println(findRadius(ho4, he4));
	}

}
