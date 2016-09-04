package leetcode;
import java.util.*;

public class A118_Pascals_Triangle {
    public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> newList = new ArrayList<Integer>();
		List<Integer> lastList = null;

		if (numRows <= 0) return result;
		
		for(int i = 1; i <= numRows; i++) {
			newList = new ArrayList<Integer>();
			for (int j = 1; j <= i; j++) {
				if (j == 1 || j == i)
					newList.add(1);
				else
					newList.add(lastList.get(j - 1) + lastList.get(j - 2));
			}
			result.add(newList);
			lastList = newList;
		}
		
		return result;
    }
}
