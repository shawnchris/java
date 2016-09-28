package leetcode;
import java.util.*;

public class A391_Perfect_Rectangle {
    public boolean isRectangleCover(int[][] rectangles) {
        Map<Long, Integer> corners = new HashMap<>();
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE,
        		maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        
        //count
        for (int[] rect : rectangles) {
        	if (!insertCorner(corners, (long)rect[0] << 32 | rect[1] & 0xffffffffL, 1) ||
        		!insertCorner(corners, (long)rect[0] << 32 | rect[3] & 0xffffffffL, 2) ||
        		!insertCorner(corners, (long)rect[2] << 32 | rect[1] & 0xffffffffL, 4) ||
        		!insertCorner(corners, (long)rect[2] << 32 | rect[3] & 0xffffffffL, 8))
        		return false;
        	minX = Math.min(minX, rect[0]);
        	maxX = Math.max(maxX, rect[2]);
        	minY = Math.min(minY, rect[1]);
        	maxY = Math.max(maxY, rect[3]);
        }
        
        //check
        for (Long point : corners.keySet()) {
        	int mask = corners.get(point);
        	if ((mask & (mask - 1)) == 0) { // 1 bit
        		int x = (int)(point >>> 32);
        		int y = point.intValue();
        		if ((x != minX && x != maxX) || (y != minY && y != maxY))
        			return false;
        	}
        	else if (mask != 3 && mask != 5 && mask != 10 && mask != 12 && mask != 15) {
                return false; // 15 Corss; others T-junction, cannot be 0110 or 1001!
            }
        }
    	return true;
    }
    
    private boolean insertCorner(Map<Long, Integer> corners, long point, int bit) {
    	int mask = corners.getOrDefault(point, 0);
    	corners.put(point, mask | bit);
    	return (mask & bit) == 0;
    }
    
	public static void main(String[] args) {
		A391_Perfect_Rectangle pr = new A391_Perfect_Rectangle();
		int[][] rectangles = 
			{{1,1,3,3}, {3,1,4,2}, {3,2,4,4}, {1,3,2,4}, {2,3,3,4}};
		System.out.println(pr.isRectangleCover(rectangles));
	}

}
