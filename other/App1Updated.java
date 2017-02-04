package other;

import java.util.Deque;
import java.util.LinkedList;


public class App1Updated {
	public static void main(String[] args) {
		Solution test = new Solution();
		System.out.println("-------------------question11-------------------");
		System.out.println("-------------solution11, WITH O(N^2)--------------");
		System.out.println(test.solution11(63432712));
		System.out.println(test.solution11(223336226));
		System.out.println(test.solution11(2211));
		System.out.println(test.solution11(221569));
		System.out.println(test.solution11(11));
		System.out.println(test.solution11(99888777));
		System.out.println(test.solution11(233614));
		System.out.println(test.solution11(123521521));
		System.out.println(test.solution11(123));
		System.out.println("------------solution112, WITH O(N) --------------");
		System.out.println(test.solution112(63432712));
		System.out.println(test.solution112(223336226));
		System.out.println(test.solution112(2211));
		System.out.println(test.solution112(221569));
		System.out.println(test.solution112(11));
		System.out.println(test.solution112(99888777));
		System.out.println(test.solution112(233614));
		System.out.println(test.solution112(123521521));
		System.out.println(test.solution112(123));
		System.out.println("-------------------question12-------------------");
		System.out.println("-------------solution12, WITH O(N^2)--------------");
		System.out.println(test.solution12(223336226));
		System.out.println(test.solution12(2211));
		System.out.println(test.solution12(221569));
		System.out.println(test.solution12(11));
		System.out.println(test.solution12(99888777));
		System.out.println(test.solution12(623315));
		System.out.println(test.solution12(12965));
		System.out.println("-------------solution122, WITH O(N)--------------");
		System.out.println(test.solution122(223336226));
		System.out.println(test.solution122(2211));
		System.out.println(test.solution122(221569));
		System.out.println(test.solution122(11));
		System.out.println(test.solution122(99888777));
		System.out.println(test.solution122(623315));
		System.out.println(test.solution122(12965));
		System.out.println("-------------------question13-------------------");
		System.out.println("-------------solution13, WITH O(N^2)--------------");
		System.out.println(test.solution13(223336226));
		System.out.println(test.solution13(2211));
		System.out.println(test.solution13(221569));
		System.out.println(test.solution13(11));
		System.out.println(test.solution13(99888777));

		System.out.println("-------------solution132, WITH O(N)--------------");
		System.out.println(test.solution132(223336226));
		System.out.println(test.solution132(2211));
		System.out.println(test.solution132(221569));
		System.out.println(test.solution132(11));
		System.out.println(test.solution132(99888777));


	}
}


class Solution {
	public int solution11(int x) {
		if (x < 10) {
			return 0;
		}
		int globalMin = x;
		int[] ary = getArrayFromNumber(x);
		for (int i = 1; i < ary.length; i++) {
			Integer idx = null;
			if (ary[i] > ary[i - 1]) {
				idx = i - 1;
			} else if (ary[i] < ary[i - 1]){
				idx = i;
			}
			if (idx != null) {
				int newNumber = getNewNumber(ary, idx);
				globalMin = Math.min(newNumber, globalMin);
			}
		}
		return globalMin;
	}

	// solution 122 is an o(n) solution to questinon 12
	/* My idea:
	 * go from 0 to end of the ary , find the first element ary[i] such that ary[i - 1] > ary[i]
stop and get the newNumber1

go from end of the array to 0, find the first element ary[i] such that ary[i + 1] > ary[i]
stop and get the newNumber2

compare newNumber1 and newNumber2 get the min
	 * */
	int solution112(int x) {
		if (x < 10) {
			return x;
		}
		int[] ary = getArrayFromNumber(x);
		int slow1 = -1;
		for (int i = 1; i < ary.length; i++) {
			if (ary[i - 1] > ary[i]) {
				slow1 = i;
				break;
			}
		}
		int newNum1 = slow1 == -1 ? x : getNewNumber(ary, slow1); // get the newNumber by deleting ary[slow1]
		int slow2 = -1;
		for (int i = ary.length - 2; i >= 0; i--) {
			if (ary[i] < ary[i + 1]) {
				slow2 = i;
				break;
			}
		}
		int newNum2 = slow2 == -1 ? x : getNewNumber(ary, slow2);
		return Math.min(newNum1, newNum2);
	}



	public int solution12(int x) {
		if (x < 10) {
			return 0;
		}
		int globalMax = 0;
		int[] ary = getArrayFromNumber(x);
		for (int i = 1; i < ary.length; i++) {
			int temp = ary[i];
			int small = Math.min(ary[i], ary[i - 1]);
			int diff = Math.abs(ary[i] - ary[i - 1]); // change the ary[i] to average round up one
			if(diff % 2 == 0) {
				ary[i] = small + diff / 2;
			} else {
				ary[i] = small + diff / 2 + 1;
			}
			int newNumber = getNewNumber(ary, i - 1);
			globalMax = Math.max(newNumber, globalMax);
			ary[i] = temp; // restore the ary[i] to the original value
		}
		return globalMax;
	}

	// solution 122 is an o(n) solution to questinon 12
	/* My idea:
	 * 
	 step1: iterate over the array from 0 to end of the array to find the first element in ascending order (ary[i - 1] < ary[i])
  and then replace those two numbers with average -> get the newNumber1

   step2: iterate over the array from end of the array to the first element in ascending order (ary[i] > ary[i + 1])
    and then replace those two numbers with average -> get the newNumber2

    step3: compare the newNumber1 and newNumber2 to get the max.
	 * */

	int solution122(int x) {
		if (x < 10) {
			return x;
		}
		int[] ary = getArrayFromNumber(x);
		int slow1 = -1;
		for (int i = 1; i < ary.length; i++) {
			if (ary[i - 1] == ary[i]) {
				slow1 = i;
			} else if (ary[i - 1] < ary[i]) {
				slow1 = i;
				break;
			}
		}
		int temp = ary[slow1];
		int tempSum = ary[slow1] + ary[slow1 - 1];
		ary[slow1] = tempSum % 2 == 0 ? tempSum / 2 : tempSum / 2 + 1;
		int newNum1 = slow1 == -1 ? x : getNewNumber(ary, slow1 - 1);
		ary[slow1] = temp;
		int slow2 = -1;
		for (int i = ary.length - 2; i>= 0; i--) {
			if (ary[i] == ary[i + 1]) {
				slow2 = i;
			} else if (ary[i] > ary[i + 1]) {
				slow2 = i;
				break;
			}
		}
		temp = ary[slow2];
		tempSum = ary[slow2] + ary[slow2 + 1];
		ary[slow2] = tempSum % 2 == 0 ? tempSum / 2 : tempSum / 2 + 1;
		int newNum2 = slow2 == -1 ? x : getNewNumber(ary, slow2 + 1);
		ary[slow2] = temp;
		return Math.max(newNum1, newNum2);
	}

	/*
	 * choose a group of(at least two) identical adjacent and remove a single digit， return max (e.g.223336226 -> 23336226).
	 *
	 * */
	public int solution13(int x) {
		if (x < 10 && x > -10) {
			return Integer.MIN_VALUE;
		}
		int[] ary = getArrayFromNumber(x);
		int globalMax = Integer.MIN_VALUE;
		for (int i = 1; i < ary.length; i++) {
			if (ary[i] == ary[i - 1]) {
				while (i < ary.length && ary[i] == ary[i - 1]) {
					i++;
				}
				// stops at ary[i] != ary[i - 1]
				i--;
				int newNumber = getNewNumber(ary, i);
				globalMax = globalMax < newNumber ? newNumber : globalMax;
			}
		}
		return globalMax;
	}
	// solution 132 is an o(n) solution to questinon 13
	public int solution132(int x) {
		if (x < 10) {
			return x;
		}
		int[] ary = getArrayFromNumber(x);
		int slow = -1;
		for (int i = 1; i < ary.length; i++) { // i is the fast pointer
			//check if there is the group (at least 2 identical digits)
			if (ary[i] != ary[i - 1]) {
				continue;
			}
			// other wise there is a such group(at least 2 identical digits) then we should go to the last digit in that group
			// and update the slow pointer to the last group.
			while (i < ary.length && ary[i] == ary[i - 1]) {
				i++;
			}
			i--;
			slow = i;
			//then check if that group of digits is smaller than the digit after that group
			if (i == ary.length - 1 || ary[i] < ary[i + 1]) {
				break;
			}

		}
		// now, ary[slow] is the number we wish to remove
		return slow == -1 ? x : getNewNumber(ary, slow);
	}
	//------------------------------------------------------------------------------------
	// The following functions are helper function

	// the getArrayFromNumber() and getNewNumber() are helper functions

	// 2 3 3 3 6 2 2 6
	// while the number is not zero   divide the number by 10 and get the last digit
	// put the digit into a stack
	//6 2 2 6 3 3 3 2   then pop the stack into array
	public int[] getArrayFromNumber(int x) {
		Deque<Integer> stack = new LinkedList<Integer>();
		while (x != 0) {
			int digit = x % 10;
			x = x / 10;
			stack.push(digit);
		}
		int[] array = new int[stack.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = stack.pop();
		}
		return array;
	}

	public int getNewNumber(int[] ary, int idx) {
		int number = 0;
		for (int i = 0; i < ary.length; i++) {
			if (i == idx) {
				continue;
			} else{
				number = number * 10 + ary[i];
			}
		}
		return number;
	}
}
