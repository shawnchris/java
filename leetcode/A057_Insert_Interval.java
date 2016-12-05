package leetcode;
import java.util.*;

public class A057_Insert_Interval {
	private int coverage = 0;
    private TreeMap<Integer, Integer> map = new TreeMap<>();
    
    private void insert(Interval itv) {
        coverage += (itv.end - itv.start);
        
        Integer floorKey = map.floorKey(itv.start);
        if (floorKey != null) {
            int prevEnd = map.get(floorKey);
            if (prevEnd >= itv.start) {
                if (prevEnd >= itv.end) {
                    coverage -= (itv.end - itv.start);
                }
                else {
                    coverage -= (prevEnd - itv.start);
                }
                itv.start = floorKey;
                itv.end = Math.max(itv.end, prevEnd);
                map.remove(floorKey);
            }
        }
        
        while (true) {
            Integer ceilingKey = map.ceilingKey(itv.start);
            if (ceilingKey == null || ceilingKey > itv.end) break;
            int nextEnd = map.get(ceilingKey);
            if (nextEnd <= itv.end) {
                coverage -= (nextEnd - ceilingKey);
            }
            else {
                coverage -= (itv.end - ceilingKey);
            }
            itv.end = Math.max(itv.end, nextEnd);
            map.remove(ceilingKey);
        }
        
        map.put(itv.start, itv.end);
    }
    
    private int getCoverage() {
        return coverage;
    }
    
    private List<Interval> getIntervals() {
        List<Interval> result = new ArrayList<>();
        for (int start : map.keySet()) {
            result.add(new Interval(start, map.get(start)));
        }
        return result;
    }
    
    public List<Interval> insert2(List<Interval> intervals, Interval newInterval) {
        for (Interval itv : intervals) {
            insert(itv);
            System.out.println(getCoverage());
        }
        insert(newInterval);
        System.out.println(getCoverage());
        
        return getIntervals();
    }
	
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
