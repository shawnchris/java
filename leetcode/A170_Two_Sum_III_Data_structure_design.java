package leetcode;
import java.util.*;

public class A170_Two_Sum_III_Data_structure_design {
    Map<Integer, Integer> map = new HashMap<>();
    
    // Add the number to an internal data structure.
	public void add(int number) {
	    if (map.containsKey(number)) {
	        map.put(number, map.get(number) + 1);
	    }
	    else {
	        map.put(number, 1);
	    }
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
	    for (Integer number : map.keySet()) {
	        if (map.containsKey(value - number)) {
	            if (value - number == number) {
	                if (map.get(number) > 1) {
	                    return true;
	                }
	            }
	            else {
	                return true;
	            }
	        }
	    }
	    return false;
	}
}
