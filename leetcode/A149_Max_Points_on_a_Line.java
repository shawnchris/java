package leetcode;
import java.util.*;
/*
* Given two points: (x1, y1) and (x2, y2), the corresponding normal vector is (A, B): A = y2 - y1, B = x1 - x2.
* If another point (x3, y3) is in the same line, then A * x3 + B * y3 = 0.
*/
public class A149_Max_Points_on_a_Line {
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
