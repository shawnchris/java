package leetcode;
import java.util.*;

class IntervalComp implements Comparator<Interval> {
    @Override
    public int compare(Interval i1, Interval i2) {
        return i1.start - i2.start;
    }
}

public class A056_Merge_Intervals {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null) return null;
        int size = intervals.size();
        if (size <= 1) return intervals;
        
        Collections.sort(intervals, new IntervalComp());
        List<Interval> result = new ArrayList<>();
        result.add(intervals.get(0));
        for (int j = 1; j < size; j++) {
            Interval i1 = result.get(result.size() - 1);
            Interval i2 = intervals.get(j);
            if (i1.end < i2.start) { // No overlap
                result.add(i2);
            }
            else { // Overlapping
                Interval i3 = new Interval(i1.start,
                    Math.max(i1.end, i2.end));
                result.remove(result.size() - 1);
                result.add(i3);
            }
        }
        
        return result;
    }
}
