package leetcode;

import java.util.*;

public class A452_Minimum_Number_of_Arrows_to_Burst_Balloons {
    public static int findMinArrowShots(int[][] points) {
        int result = 0;
        if (points == null || points.length == 0) return result;
        
        Arrays.sort(points, new Comparator<int[]>() {
        	public int compare(int[] p1, int[] p2) {
        		return p1[0] - p2[0];
        	}
        });
        
        int end = points[0][1];
        for (int i = 1; i < points.length; i++) {
        	if (points[i][0] > end) { // new arrow needed
        		result++;
        		end = points[i][1];
        	}
        	else {
        		end = Math.min(end, points[i][1]);
        	}
        }
        return result + 1;
    }
	public static void main(String[] args) {
		int[][] p1 = new int[][] {{10,16}, {2,8}, {1,6}, {7,12}};
		System.out.println(findMinArrowShots(p1));
	}

}
