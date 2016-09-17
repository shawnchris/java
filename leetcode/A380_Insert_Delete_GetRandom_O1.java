package leetcode;
import java.util.*;

public class A380_Insert_Delete_GetRandom_O1 {
	public class RandomizedSet {
		private Map<Integer, Integer> map = new HashMap<>();
		private List<Integer> list = new ArrayList<>();
		private Random rand = new Random();
		
	    /** Initialize your data structure here. */
	    public RandomizedSet() {
	        
	    }
	    
	    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
	    public boolean insert(int val) {
	        if (map.containsKey(val)) return false;
	        map.put(val, list.size());
	        list.add(val);
	        return true;
	    }
	    
	    /** Removes a value from the set. Returns true if the set contained the specified element. */
	    public boolean remove(int val) {
	        if (!map.containsKey(val)) return false;
	        int index = map.get(val);
	        //swap this element with the last one then remove the last one to achieve O(1).
	        int last = list.get(list.size() - 1);
	        list.set(index, last);
	        list.remove(list.size() - 1);
	        map.put(last, index);
	        map.remove(val);
	        return true;
	    }
	    
	    /** Get a random element from the set. */
	    public int getRandom() {
	        return list.get(rand.nextInt(list.size()));
	    }
	}
	
	public static void main(String[] args) {
		A380_Insert_Delete_GetRandom_O1 id = new A380_Insert_Delete_GetRandom_O1();
		RandomizedSet rs = id.new RandomizedSet();
		/*
		"insert","remove","insert","getRandom","remove","insert","getRandom"
		[1],[2],[2],[],[1],[2],[]]
		*/
		System.out.println(rs.insert(1));
		System.out.println(rs.remove(2));
		System.out.println(rs.insert(2));
		System.out.println(rs.getRandom());
		System.out.println(rs.remove(1));
		System.out.println(rs.insert(2));
		System.out.println(rs.getRandom());
		System.out.println();
		
	}

}
