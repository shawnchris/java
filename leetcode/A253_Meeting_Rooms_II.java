package leetcode;
import java.util.*;

public class A253_Meeting_Rooms_II {
	public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        
        Arrays.sort(intervals, new Comparator<Interval>() {
           public int compare(Interval i1, Interval i2) {
               return i1.start - i2.start;
           }
        });
        
        PriorityQueue<Interval> queue = new PriorityQueue<>(new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2) {
                return i1.end - i2.end;
            }
        });
        int max = 1;
        for (int i = 0; i < intervals.length; i++) {
            if (queue.isEmpty()) {
                queue.add(intervals[i]);
            }
            else {
                // Remove non-overlapping intervals on the top
                while (!queue.isEmpty() && queue.peek().end <= intervals[i].start) {
                    queue.poll();
                }
                // Now either queue is empty or all remaining intervals overlap with each other
                // Prove: the smallest end is bigger than intervals[i].start, and all starts are no bigger than intervals[i].start
                queue.add(intervals[i]);
                max = Math.max(max, queue.size());
            }
        }
        
        return max;
    }
	
	public int minMeetingRooms2(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        
        Arrays.sort(intervals, new Comparator<Interval>() {
           public int compare(Interval i1, Interval i2) {
               return i1.start - i2.start;
           }
        });
        
        PriorityQueue<Interval> queue = new PriorityQueue<>(new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2) {
                return i1.end - i2.end;
            }
        });
        
        queue.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            Interval itv = queue.poll();
            if (itv.end <= intervals[i].start) {
            	// If interval on the top (earliest end) overlaps with interval[i], merge to one interval
            	itv.end = intervals[i].end;
            }
            else {
            	// Otherwise add interval to queue.
            	queue.add(intervals[i]);
            }
            queue.add(itv);
            // All intervals in queue are overlapping with each other.
        }
        
        return queue.size();
    }
}
