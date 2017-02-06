package leetcode;

public class A360_Sort_Transformed_Array {
	public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0, right = n - 1;
        int index = a >= 0 ? n - 1 : 0;
        
        while (left <= right) {
        	int valLeft = func(nums[left], a, b, c);
        	int valRight = func(nums[right], a, b, c);
            if (a >= 0) { // Bigger first, so index move backward.
            	if (valLeft > valRight) {
            		result[index] = valLeft;
            		left++;
            	}
            	else {
            		result[index] = valRight;
            		right--;
            	}
            	index--;
            }
            else { // Smaller first, so index move forward.
            	if (valLeft < valRight) {
            		result[index] = valLeft;
            		left++;
            	}
            	else {
            		result[index] = valRight;
            		right--;
            	}
            	index++;
            }
        }
        
        return result;
    }
    
	private int func(int x, int a, int b, int c) {
		return a * x * x + b * x + c;
	}
	
	public static void main(String[] args) {
		A360_Sort_Transformed_Array st = new A360_Sort_Transformed_Array();
		
		int[] nums1 = {-4, -2, 2, 4};
		int a1 = 1, b1 = 3, c1 = 5;
		print(st.sortTransformedArray(nums1, a1, b1, c1));
		
		int a2 = -1, b2 = 3, c2 = 5;
		print(st.sortTransformedArray(nums1, a2, b2, c2));
	}
	
	private static void print(int[] arr) {
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
