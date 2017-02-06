package leetcode;

import java.util.*;

public class A341_Flatten_Nested_List_Iterator {
	interface NestedInteger {

		// @return true if this NestedInteger holds a single integer, rather
		// than a nested list.
		public boolean isInteger();

		// @return the single integer that this NestedInteger holds, if it holds
		// a single integer
		// Return null if this NestedInteger holds a nested list
		public Integer getInteger();

		// @return the nested list that this NestedInteger holds, if it holds a
		// nested list
		// Return null if this NestedInteger holds a single integer
		public List<NestedInteger> getList();
	}
	
	// Stack solution
	public class NestedIterator implements Iterator<Integer> {
	    Stack<NestedInteger> stack;
	    
	    public NestedIterator(List<NestedInteger> nestedList) {
	        stack = new Stack<NestedInteger>();
	        for (int i = nestedList.size() - 1; i >= 0; i--) {
	            stack.push(nestedList.get(i));
	        }
	    }

	    @Override
	    public Integer next() {
	        return stack.pop().getInteger();
	    }

	    @Override
	    public boolean hasNext() {
	        while (!stack.isEmpty() && !stack.peek().isInteger()) {
	            List<NestedInteger> nestedList = stack.pop().getList();
	            for (int i = nestedList.size() - 1; i >= 0; i--) {
	                stack.push(nestedList.get(i));
	            }
	        }
	        return !stack.isEmpty();
	    }
	}
	
	// O(n) memory solution
	public class NestedIterator1 implements Iterator<Integer> {
		private List<Integer> list;
		private int total, current;

		private void traverse(NestedInteger ni) {
			if (ni.isInteger()) {
				list.add(ni.getInteger());
				return;
			}
			for (NestedInteger n : ni.getList()) {
				traverse(n);
			}
		}

		public NestedIterator1(List<NestedInteger> nestedList) {
			list = new ArrayList<Integer>();
			current = 0;
			for (NestedInteger ni : nestedList) {
				traverse(ni);
			}
			total = list.size();
		}

		@Override
		public Integer next() {
			current++;
			return list.get(current - 1);
		}

		@Override
		public boolean hasNext() {
			return current < total;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
