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
    
    // use tree-map
    public static List<Interval> merge2(List<Interval> intervals) {
    	if (intervals == null) return null;
        int size = intervals.size();
        if (size <= 1) return intervals;
        
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        
        for (Interval it : intervals) {
        	Integer floorKey = tm.floorKey(it.start);
        	Integer ceilingKey = tm.ceilingKey(it.start);
        	// case 1, no overlapping
        	if ((floorKey == null || tm.get(floorKey) < it.start) &&
        			(ceilingKey == null || ceilingKey > it.end)) {
        		tm.put(it.start, it.end);
        		continue;
        	}
        	// case 2, included in existing interval
        	if (floorKey != null && floorKey <= it.start && tm.get(floorKey) >= it.end) {
        		continue;
        	}
        	// case 3, 0 or 1 left overlapping, 0 or n right overlapping.
        	int start = floorKey == null ? it.start : (tm.get(floorKey) >= it.start ? floorKey : it.start);
        	int end = it.end;
        	while (ceilingKey != null) {
        		if (end >= ceilingKey) {
        			end = Math.max(end, tm.get(ceilingKey));
        			tm.remove(ceilingKey);
        			ceilingKey = tm.ceilingKey(start);
        		}
        		else {
        			ceilingKey = null;
        		}
        	}
        	tm.put(start, end);
        }
        
        List<Interval> result = new ArrayList<>();
        for (Integer key : tm.keySet())
        	result.add(new Interval(key, tm.get(key)));
        return result;
    }
    
    private static List<Interval> genInterval(int[][] input) {
    	List<Interval> result = new ArrayList<>();
    	for (int[] a : input) {
    		result.add(new Interval(a[0], a[1]));
    	}
    	return result;
    }
    
    private static void print(List<Interval> input) {
    	for (Interval i : input) {
    		System.out.print("(" + i.start + "," + i.end + ") ");
    	}
    	System.out.println();
    }
    
    public static void main(String[] args) {
    	int[][] input1 = {{1, 4}, {1, 4}};
    	int[][] input2 = {{2,3},{4,5},{6,7},{8,9},{1,10}};
    	int[][] input3 = {{1,4},{2,3}};
    	int[][] input4 = {{2,3},{2,2},{3,3},{1,3},{5,7},{2,2},{4,6}};
    	//print(merge2(genInterval(input1)));
    	//print(merge2(genInterval(input2)));
    	//print(merge2(genInterval(input3)));
    	print(merge2(genInterval(input4)));
    }
}
