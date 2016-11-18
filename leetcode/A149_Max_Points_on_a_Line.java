package leetcode;
import java.util.*;
/*
* Given two points: (x1, y1) and (x2, y2), the corresponding normal vector is (A, B): A = y2 - y1, B = x1 - x2.
* If another point (x3, y3) is in the same line, then A * x3 + B * y3 = 0.
*/
public class A149_Max_Points_on_a_Line {
    public int maxPoints2(Point[] points) {
        // Hold one point p[i], iterate through all other points p[j] (i != j) and calc slope[j]
        // If slope[j1] = slope[j2], those two points are in a line.
        // Also need to handle p[j] overlaps with p[i]
        if (points == null) return 0;
        int n = points.length;
        if (n <= 2) return n;
        
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            int overlap = 0, max = 0;
            Map<Long, Integer> map = new HashMap<>();
            for (int j = i + 1; j < n; j++) {
                int x = points[i].x - points[j].x;
                int y = points[i].y - points[j].y;
                // Overlap
                if (x == 0 && y == 0) {
                    overlap++;
                    continue;
                }
                int gcd = calcGCD(x, y);
                x = x / gcd;
                y = y / gcd;
                long key = (long)x << 31 + y;
                int value = map.getOrDefault(key, 0) + 1;
                max = Math.max(max, value);
                map.put(key, value);
            }
            result = Math.max(result, max + overlap + 1);
        }
        
        return result;
    }
    
    private int calcGCD(int a, int b) {
        if (b == 0) return a;
        return calcGCD(b, a % b);
    }
	public int maxPoints(Point[] points) {
        if (points == null) return 0;
        int len = points.length;
        if (len < 3) return len;
        
        int max = 0;
        
        // Find all the dup points. overlap[i][0] is the index of first point.
        // overlap[i][1] is count of how many overlapping points.
        int[][] overlap = new int[len][2];
        for (int i = 0; i < len; i++) {
            if (overlap[i][1] != 0) continue;
            int count = 0;
            ArrayList<Integer> index = new ArrayList<>();
            for (int j = i; j < len; j++) {
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    count++;
                    index.add(j);
                }
            }
            for (int j = 0; j < index.size(); j++) {
                overlap[index.get(j)][0] = i;
                overlap[index.get(j)][1] = count;
            }
            max = Math.max(max, count);
        }
        
        // Assume points[i] points[j] forms a line. Check how many of the rest points
        //   are in the line and also not overlapped with i or j.
        // Then points in this line = overlap[i][1] + overlap[j][1] + inLine
        for (int i = 0; i < len; i++) {
            if (overlap[i][0] != i) continue;
            for (int j = i + 1; j < len; j++) {
                if (overlap[j][0] == i) continue;
                int x = points[j].y - points[i].y;
                int y = points[i].x - points[j].x;
                int inLine = 0;
                for (int k = j + 1; k < len; k++) {
                    if (overlap[k][0] == i || overlap[k][0] == j) continue;
                    int dx = points[k].x - points[i].x;
                    int dy = points[k].y - points[i].y;
                    if (x * dx + y * dy == 0) inLine++;
                }
                max = Math.max(max, overlap[i][1] + overlap[j][1] + inLine);
            }
        }
        
        return max;
    }
}
