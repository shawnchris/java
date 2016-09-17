package leetcode;
import java.util.*;

public class A381_Insert_Delete_GetRandom_O1_Duplicates_allowed {

	public class RandomizedCollection {
		private Map<Integer, Set<Integer>> map = new HashMap<>();
		private List<Integer> list = new ArrayList<>();
		private Random rand = new Random();
		
	    /** Initialize your data structure here. */
	    public RandomizedCollection() {
	        
	    }
	    
	    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
	    public boolean insert(int val) {
	    	boolean result = true;
	    	Set<Integer> set;
	    	
	        if (map.containsKey(val)) {
	        	result = false;
	        	set = map.get(val);
	        }
	        else {
	        	set = new HashSet<Integer>();
	        }
	        set.add(list.size());
	        map.put(val, set);
	        list.add(val);
	        
	        return result;
	    }
	    
	    /** Removes a value from the set. Returns true if the set contained the specified element. */
	    public boolean remove(int val) {
	        if (!map.containsKey(val)) return false;
	        
	        if (list.get(list.size() - 1) == val) {
	        	Set<Integer> set = map.get(val);
	        	set.remove(list.size() - 1);
	        	if (set.isEmpty())
	        		map.remove(val);
	        	else
	        		map.put(val, set);
	        	list.remove(list.size() - 1);
	        }
	        else {
		        int index2 = list.size() - 1;
		        int val2 = list.get(index2);
		        Set<Integer> set2 = map.get(val2);
		        
		        Set<Integer> set1 = map.get(val);
		        int index1 = set1.iterator().next();
		        
		        list.set(index1, val2);
		        list.remove(index2);
		        
		        set1.remove(index1);
		        if (!set1.isEmpty())
		        	map.put(val, set1);
		        else
		        	map.remove(val);
		        
		        set2.remove(index2);
		        set2.add(index1);
		        map.put(val2, set2);
	        }
	        
	        return true;
	    }
	    
	    /** Get a random element from the set. */
	    public int getRandom() {
	        return list.get(rand.nextInt(list.size()));
	    }
	}
	
	public static void main(String[] args) {
		A381_Insert_Delete_GetRandom_O1_Duplicates_allowed id = new A381_Insert_Delete_GetRandom_O1_Duplicates_allowed();
		RandomizedCollection rs = id.new RandomizedCollection();
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
		/*
		"insert","remove","insert","remove","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom"]
		[],[0],[0],[-1],[0],[],[],[],[],[],[],[],[],[],[]]
		 */
		RandomizedCollection rc = id.new RandomizedCollection();
		System.out.println(rc.insert(0));
		System.out.println(rc.remove(0));
		System.out.println(rc.insert(-1));
		System.out.println(rc.remove(0));
		System.out.println(rc.getRandom());
		System.out.println(rc.getRandom());
		System.out.println(rc.getRandom());
		System.out.println(rc.getRandom());
		System.out.println(rc.getRandom());
		System.out.println(rc.getRandom());
	}

}
