package depaul.csc421;
import java.util.*;

/**
 * 
 * Given an array of numeric values, find the contiguous subarray whose sum is the largest.
 * 
 * @author Shan Gao
 *
 */
public class Find_maximum_subarray {
	
	//The object used to hold the return values
	class ret_obj {
		public int low;
		public int high;
		public int sum;
		public ret_obj(int l, int h, int s) {
			low = l;
			high = h;
			sum = s;
		}
	}
	
	//Main recursive method
	private ret_obj find_maximum_subarray(int[] a, int low, int high) {

		if (low == high) // Base case, only one element.
			return new ret_obj(low, high, a[low]); 

		int mid = (low + high)/2;
		ret_obj maxLeftSum = find_maximum_subarray(a, low, mid);
		ret_obj maxRightSum = find_maximum_subarray(a, mid + 1, high);
		ret_obj maxCrossSum = max_cross_subarray(a, low, mid, high);

		return max3(maxLeftSum, maxRightSum, maxCrossSum);
	}

	//Calculate max-crossing-subarray
	private ret_obj max_cross_subarray(int[] a, int low, int mid, int high) {
		int  leftSum = a[mid], rightSum = a[mid+1];
		int  sum=0, l=mid, h=mid+1;
		for (int i = mid; i >= low; i--) {
			sum += a[i];
			if (sum > leftSum) {
				leftSum = sum;
				l = i;
			}
		}
		sum = 0;
		for (int j = mid + 1; j <= high; j++) {
			sum += a[j];
			if (sum > rightSum) {
				rightSum = sum;
				h = j;
			}
		}
		return new ret_obj(l, h, leftSum + rightSum);
	}

	//Compare 3 return_value objects and return the max one
	private ret_obj max3(ret_obj a, ret_obj b, ret_obj c) {
		return a.sum > b.sum ? (a.sum > c.sum ? a : c) : (b.sum > c.sum ? b : c);
	}

	public static void main(String[] args) {
		int[] a = new int[100];
		Find_maximum_subarray fms = new Find_maximum_subarray();
		Random rand = new Random();
		
		System.out.print("A = (");
		for (int i=0; i<100; i++) {
			a[i] = rand.nextInt(100);
			a[i] = (rand.nextInt(2)==0) ? (a[i]) : (-1 * a[i]);
			System.out.print(a[i]+",");
		}
		System.out.println(")");
		
		ret_obj result = fms.find_maximum_subarray(a, 0, a.length-1);
		System.out.println("The maximum subarray A["+result.low + ".." + result.high + "] = " + result.sum);

	}

}
