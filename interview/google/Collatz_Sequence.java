package interview.google;
import java.util.*;

public class Collatz_Sequence {

	/*
	 * N -> N/2 ( if N is even)
	 * N -> 3N + 1 (if N is odd)
	 */
	
	public static int computedNumberStepsToReachOne(int num) {
		int step = 0;
		while (num != 1) {
			step++;
			if (num % 2 == 0) {
				num /= 2;
			}
			else {
				num = num * 3 + 1;
			}
		}
		return step;
	}
	
	public static int getCount(int n) {
		int count = 0;
		Map<Integer, Integer> numToStep = new HashMap<>();
		
		for (int i = 1; i < n; i++) {
			int num = i, step = 0;
			while (num != 1) {
				step++;
				if (num % 2 == 0) {
					num = num / 2;
				}
				else {
					num = num * 3 + 1;
				}
				if (numToStep.containsKey(num)) {
					step += numToStep.get(num);
					break;
				}
			}
			numToStep.put(i, step);
			if (i > step) count++;
		}
		
		return count;
	}
	
	public static void main(String[] args) {
		int n = 111000, count = 0;
		/*
		Timer.start();
		for (int i = 1; i < n; i++) {
			int step = computedNumberStepsToReachOne(i);
			if (i > step) count++;
		}
		System.out.println(n + " : " + count);
		Timer.end();
		*/
		Timer.start();
		System.out.println(n + " : " + getCount(n));
		Timer.end();

	}

}
