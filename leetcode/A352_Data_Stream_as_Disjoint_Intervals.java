package leetcode;

import java.util.*;

public class A352_Data_Stream_as_Disjoint_Intervals {
	/* 
	 * Use TreeMap to easily find the lower and higher keys, the key is the
	 * start of the interval. Merge the lower and higher intervals when
	 * necessary. The time complexity for adding is O(logN) since lowerKey(),
	 * higherKey(), put() and remove() are all O(logN). It would be O(N) if
	 * you use an ArrayList and remove an interval from it.
	*/
	public class SummaryRanges {
		TreeMap<Integer, Interval> map = null;
		public SummaryRanges() {
			map = new TreeMap<Integer, Interval>();
		}
		public void addNum(int val) {
			if(map.containsKey(val)) return;
			Integer l = map.lowerKey(val);
			Integer h = map.higherKey(val);
			// Four different cases
			// Case 1: new value connects two intervals, merge them.
			if (l != null && h != null && map.get(l).end + 1 == val 
					&& val + 1 == h) {
				map.get(l).end = map.get(h).end;
				map.remove(h);
			}
			// Case 2: new value connects lower interval only.
			else if (l != null && map.get(l).end + 1 >= val) {
				map.get(l).end = Math.max(map.get(l).end, val);
			}
			// Case 3: new value connects higher interval only.
			else if (h != null && val + 1 == h) {
				map.put(val, new Interval(val, map.get(h).end));
				map.remove(h);
			}
			// Case 4: new value creates a new interval.
			else {
				map.put(val, new Interval(val, val));
			}
		}
		public List<Interval> getIntervals() {
			return new ArrayList<Interval>(map.values());
		}
	}

	public static void main(String[] args) {
		A352_Data_Stream_as_Disjoint_Intervals ds = 
				new A352_Data_Stream_as_Disjoint_Intervals();
		SummaryRanges sr = ds.new SummaryRanges();
		int[] test1 = {1, 3, 7, 2, 6};
		for (int i : test1) {
			sr.addNum(i);
			for (Interval j : sr.getIntervals()) {
				System.out.print("[" + j.start + ", " + j.end + "], ");
			}
			System.out.println();
		}
	}

}
