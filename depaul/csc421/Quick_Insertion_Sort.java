package depaul.csc421;
import java.util.*;

/**
 * Quick + Insertion Sort
 * Upon calling quicksort on a subarray with fewer than k elements, let it simply return without
 * sorting the subarray. After the top-level call to quicksort returns, run insertion sort on the 
 * entire array to finish the sorting process.
 * 
 * @author Shan Gao
 *
 */
public class Quick_Insertion_Sort {

	static final int LEN = 9000000;
	static final int K = 1000;

	void quicksort(int A[], int p, int r) {
	    if (p < r - 1) {
	        int q = partition(A, p, r);
	        quicksort(A, p, q);
	        quicksort(A, q + 1, r);
	    }
	}
	
	void quick_insertion_sort(int A[], int p, int r, int treshold) {
	    if (r - p > treshold) {
	        int q = partition(A, p, r);
	        quick_insertion_sort(A, p, q, treshold);
	        quick_insertion_sort(A, q + 1, r, treshold);
	    }
	}

	int partition(int A[], int p, int r) {
	    int x, i, j, tmp;

	    x = A[r - 1];
	    i = p;

	    for (j = p; j < r - 1; j++) {
	        if (A[j] <= x) {
	            tmp = A[i];
	            A[i] = A[j];
	            A[j] = tmp;
	            i++;
	        }
	    }

	    tmp = A[i];
	    A[i] = A[r - 1];
	    A[r - 1] = tmp;

	    return i;
	}

	void insertion_sort(int A[], int p, int r) {
	    int i, j, key;

	    for (j = p + 1; j < r; j++) {
	        key = A[j];
	        for (i = j - 1; i >= p && A[i] > key; i--) {
	            A[i + 1] = A[i];
	        }
	        A[i + 1] = key;
	    }
	}
	
	public static void main(String[] args) {
		int A1[] = new int[LEN];
		int A2[] = new int[LEN];
		
		Random rand = new Random();
		for (int i=0; i<LEN; i++) {
			A1[i] = rand.nextInt(10000);
			A2[i] = A1[i];
		}
		Quick_Insertion_Sort qis = new Quick_Insertion_Sort();
		
		long startTime, endTime;
		System.out.println("Sorting array of length="+LEN+" with k="+(K));
		
		startTime= System.currentTimeMillis();
		qis.quick_insertion_sort(A1, 0, LEN, K);
		qis.insertion_sort(A1, 0, LEN);
		endTime= System.currentTimeMillis();
		System.out.println("Quick Insertion Sort time: " + (endTime-startTime) + "ms");
		
		startTime= System.currentTimeMillis();
		qis.quicksort(A2, 0, LEN);
		endTime= System.currentTimeMillis();
		System.out.println("Quick Sort time: " + (endTime-startTime) + "ms");
		
	}

}
