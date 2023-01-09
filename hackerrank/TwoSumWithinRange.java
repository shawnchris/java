package hackerrank;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers arr and a target number, return true if there exist two numbers
 *  1) sum up to the target
 *  2) whose indices have a difference that falls within the range (inclusive) provided by minimum and maximum
 *     arguments,
 * otherwise return false;
 */
public class TwoSumWithinRange {
    public static boolean existsPairWithSum(int[] arr, int target, int minimum, int maximum) {
        Map<Integer, Integer> numToIndex = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            if (numToIndex.containsKey(complement)) {
                int indexDifference = Math.abs(i - numToIndex.get(complement));
                if (indexDifference >= minimum && indexDifference <= maximum) {
                    return true;
                }
            }
            numToIndex.put(arr[i], i);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(
                existsPairWithSum(new int[] {1, 2, 3, 4, 5}, 5, 1, 2)); // prints true
        System.out.println(
                existsPairWithSum(new int[] {1, 0, 0, 0, 5}, 6, 1, 2)); // prints false
    }
}
