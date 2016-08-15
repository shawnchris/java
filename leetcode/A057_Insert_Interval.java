package leetcode;
import java.util.*;

public class A057_Insert_Interval {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        if (intervals == null) {
            result.add(newInterval);
            return result;
        }
        if (intervals.size() == 0) {
            result.add(newInterval);
            return result;
        }
        
        boolean overlap = false;
        int i = 0;
        while (i < intervals.size()) {
            Interval itv = intervals.get(i);
            if (itv.end < newInterval.start || itv.start > newInterval.end) {
                result.add(itv);
                i++;
            }
            else {
                overlap = true;
                int start = Math.min(itv.start, newInterval.start);
                int end = Math.max(itv.end, newInterval.end);
                i++;
                while (i < intervals.size()) {
                    itv = intervals.get(i);
                    if (itv.end < newInterval.start || itv.start > newInterval.end)
                        break;
                    start = Math.min(itv.start, start);
                    end = Math.max(itv.end, end);
                    i++;
                }
                Interval comb = new Interval(start, end);
                result.add(comb);
            }
        }
        if (!overlap) {
            for (int j = 0; j < result.size(); j++)
                if (result.get(j).start > newInterval.start) {
                    result.add(j, newInterval);
                    break;
                }
            if (result.size() == intervals.size())
                result.add(newInterval);
        }
        
        return result;
    }
}
