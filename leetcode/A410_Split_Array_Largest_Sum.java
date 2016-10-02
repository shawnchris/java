package leetcode;

public class A410_Split_Array_Largest_Sum {
    public int splitArray(int[] nums, int m) {
        long max = 0;
        long sum = 0;
        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }
        long left = max, right = sum;
        while (left < right) {
            long mid = (left + right) / 2;
            if (can(nums, m, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }

        }
        return (int) left;
    }

    private static boolean can(int[] nums, int m, long target) {
        int count = 1;
        long current = 0;
        for (int num : nums) {
            if (num > target) {
                return false;
            }
            if (current + num > target) {
                current = 0;
                count++;
            }
            current += num;
        }
        return count <= m;

    }
	public static void main(String[] args) {
		A410_Split_Array_Largest_Sum sa = new A410_Split_Array_Largest_Sum();
		int[] nums1 = {1, 2, 3, 4, 5};
		System.out.println(sa.splitArray(nums1, 2));  //9
		int[] nums2 = {5,10,21,20};
		System.out.println(sa.splitArray(nums2, 2)); //36
		int[] nums3 = {1, 2147483646};
		System.out.println(sa.splitArray(nums3, 1)); //2147483647
		int[] nums4 = {1,4,4};
		System.out.println(sa.splitArray(nums4, 3)); //4*/
		int[] nums5 = {2,3,1,2,4,3};
		System.out.println(sa.splitArray(nums5, 5)); //4
	}

}
