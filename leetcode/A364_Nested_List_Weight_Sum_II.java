package leetcode;
import java.util.*;

public class A364_Nested_List_Weight_Sum_II {
	static class NestedInteger {
		private int value;
		private List<NestedInteger> list;
		private boolean isInteger;
		public NestedInteger() {
			this.isInteger = false;
			this.list = new ArrayList<NestedInteger>();
		};
		public NestedInteger(int value) {
			this.isInteger = true;
			this.value = value;
		};
		public boolean isInteger() {
			return isInteger;
		};
		public Integer getInteger() {
			return value;
		};
		public void setInteger(int value) {
			this.value = value;
		};
		public void add(NestedInteger ni) {
			list.add(ni);
		};
		public List<NestedInteger> getList() {
			return list;
		};
	}
	
	public static int depthSumInverse(List<NestedInteger> nestedList) {
	    int unweighted = 0, weighted = 0;
	    while (!nestedList.isEmpty()) {
	        List<NestedInteger> nextLevel = new ArrayList<>();
	        for (NestedInteger ni : nestedList) {
	            if (ni.isInteger())
	                unweighted += ni.getInteger();
	            else
	                nextLevel.addAll(ni.getList());
	        }
	        weighted += unweighted;
	        nestedList = nextLevel;
	    }
	    return weighted;
	}
	
	public static void main(String[] args) {
		NestedInteger ni1 = new NestedInteger(1);
		NestedInteger ni2 = new NestedInteger(1);
		NestedInteger ni3 = new NestedInteger(2);
		NestedInteger ni4 = new NestedInteger(1);
		NestedInteger ni5 = new NestedInteger(1);
		NestedInteger ni6 = new NestedInteger();
		NestedInteger ni7 = new NestedInteger();
		List<NestedInteger> list = new ArrayList<>();
		
		ni6.add(ni1); ni6.add(ni2);
		ni7.add(ni4); ni7.add(ni5);
		list.add(ni6); list.add(ni3); list.add(ni7);
		
		System.out.println(depthSumInverse(list));

	}

}
