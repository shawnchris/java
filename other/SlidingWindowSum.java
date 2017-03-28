package other;

public class SlidingWindowSum {
	public int sumCount (int[] nums, int k, int target) {
		if (nums.length < k) return 0;
		
		int sum = 0;
		for (int i = 0; i < k - 1; i++) {
			sum += nums[i];
		}
		
		int count = 0;
		for (int i = k - 1; i < nums.length; i++) {
			sum += nums[i];
			if (sum == target) {
				count++;
			}
			sum -= nums[i - k + 1];
		}
		
		return count;
	}
	public static void main(String[] args) {
		SlidingWindowSum sws = new SlidingWindowSum();
		System.out.println(sws.sumCount(new int[] {1, 2, 1, 3, 2}, 2, 3));
	}

}
