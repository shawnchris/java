package leetcode;

public class A477_Total_Hamming_Distance {
    public static int totalHammingDistance(int[] nums) {
        int n = 32;
        int len = nums.length;
        int[] countOfOnes = new int[n];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < n; j++) {
                countOfOnes[j] += (nums[i] >> j) & 1;
            }
        }
        int sum = 0;
        for (int count: countOfOnes) {
            sum += count * (len - count);
        }
        return sum;
    }
    
	public static void main(String[] args) {
		System.out.println(totalHammingDistance(new int[] {4, 14, 2}));
		
	}

}
