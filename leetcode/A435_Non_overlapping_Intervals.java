package leetcode;
import java.util.*;

public class A435_Non_overlapping_Intervals {
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        
        int end = Integer.MIN_VALUE, result = 0;
        for (Interval i : intervals) {
            if (i.start < end) { //Overlapping, remove the bigger "end"
                result++;
                end = Math.min(end, i.end);
            }
            else { //No overlapping, update "end"
                end = i.end;
            }
        }
        
        return result;
    }
}
