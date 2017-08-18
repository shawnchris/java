package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class A630_Course_Schedule_III {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> (a[1] - b[1]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int n = courses.length;
        int day = 0;
        for (int[] c : courses){
            day += c[0];
            pq.add(-c[0]);
            while (day > c[1]) day += pq.poll();
        }
        return pq.size();
    }
}
