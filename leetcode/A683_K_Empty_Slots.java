package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days. In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.

 Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.

 For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the range from 1 to N.

 Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, and also the number of flowers between them is k and these flowers are not blooming.

 If there isn't such day, output -1.

 Example 1:
 Input:
 flowers: [1,3,2]
 k: 1
 Output: 2
 Explanation: In the second day, the first and the third flower have become blooming.
 Example 2:
 Input:
 flowers: [1,2,3]
 k: 1
 Output: -1
 Note:
 The given array will be in the range [1, 20000].
 */
public class A683_K_Empty_Slots {
    public int kEmptySlots(int[] flowers, int k) {
        int[] days = new int[flowers.length];
        for (int i = 0; i < flowers.length; i++) {
            days[flowers[i] - 1] = i + 1;
        }

        MinQueue<Integer> window = new MinQueue();
        int ans = days.length;

        for (int i = 0; i < days.length; i++) {
            int day = days[i];
            window.addLast(day);
            if (k <= i && i < days.length - 1) {
                window.pollFirst();
                if (k == 0 || days[i-k] < window.min() && days[i+1] < window.min()) {
                    ans = Math.min(ans, Math.max(days[i-k], days[i+1]));
                }
            }
        }

        return ans < days.length ? ans : -1;
    }
}

class MinQueue<E extends Comparable<E>> extends ArrayDeque<E> {
    Deque<E> mins;

    public MinQueue() {
        mins = new ArrayDeque<E>();
    }

    @Override
    public void addLast(E x) {
        super.addLast(x);
        while (mins.peekLast() != null &&
                x.compareTo(mins.peekLast()) < 0) {
            mins.pollLast();
        }
        mins.addLast(x);
    }

    @Override
    public E pollFirst() {
        E x = super.pollFirst();
        if (x == mins.peekFirst()) mins.pollFirst();
        return x;
    }

    public E min() {
        return mins.peekFirst();
    }
}
