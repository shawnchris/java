package interview.linkedin;
import java.util.*;

public class MultiDimensionalArray {
	List<MultiDimensionalArray> children;
	Integer value;
	boolean isLeaf = true;
	
	public MultiDimensionalArray() {
		
	}
	
	public int getValue(Integer ... dimension) {
		return 0;
	}
	
	public int getSum(MultiDimensionalArray mda, List<Integer> dimensions) {
		if (mda.isLeaf) {
			return getValue(dimensions.toArray(new Integer[dimensions.size()]));
		}
		
		int sum = 0, index = 0;
		for (MultiDimensionalArray child : mda.children) {
			dimensions.add(index);
			sum += getSum(child, dimensions);
			dimensions.remove(dimensions.size() - 1);
			index++;
		}
		
		return sum;
	}
	
	public static void main(String[] args) {
		MultiDimensionalArray mda = new MultiDimensionalArray();
		mda.getSum(mda, new ArrayList<Integer>());

	}

}
