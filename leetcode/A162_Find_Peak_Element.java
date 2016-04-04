package leetcode;

public class A162_Find_Peak_Element {

	public int findPeakElement(int[] nums) {
        int start = 0, end = nums.length - 1, mid = 0;
        
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] > nums[mid - 1]) // increasing
                start = mid;
            else
                end = mid;
        }
        
        return (nums[start] > nums[end]) ? start : end;
    }
	
	public static void main(String[] args) {
		A162_Find_Peak_Element fpe =  new A162_Find_Peak_Element();
		int[] test1 = {1, 2, 3};
		System.out.println(fpe.findPeakElement(test1));
	}

}
