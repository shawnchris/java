package com.tableau;

public class Solution {
	// Phone interview
	// Write binary search in Java, C++, C, or C#.// Assume the data is in a sorted array of floats.
	public int binarySearch(float[] array, int start, int end, float target) {
	    if (start > end)
	        return -1;
	    
	    int mid = start + (end - start) / 2;
	    if (array[mid] == target)
	        return mid;
	    
	    if (array[mid] < target) {
	        return binarySearch(array, mid + 1, end, target);
	    }
	    else {
	        return binarySearch(array, start, mid - 1, target);
	    }
	    
	}

	public int binarySearch2(float[] array, int start, int end, float target) {
	    if (start > end)
	        return start + 1;
	    
	    int mid = start + (end - start) / 2;
	    if (array[mid] == target)
	        return mid;
	    
	    if (array[mid] < target) {
	        return binarySearch(array, mid + 1, end, target);
	    }
	    else {
	        return binarySearch(array, start, mid - 1, target);
	    }
	    
	}
	/*
	[2, 4], 3

	[], 5
	[1,2,3,4], 5
	[6,7,8,9,10], 5
	[0,0,0,0,0,0,0,9,9,9,9,9], 5
	[1,1,1,1,1,1,1,1,1,1,....1], 5
	[Float.MIN_VALUE, Float.MAX_VALUE], 5

	Float.NaN  // Not a Number
	*/

	// On-site interview
	// Search 2D matrix + Fill zero 2D matrix
	// Design elevator
	// Maze
	// Code inspection
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
