package hackerrank;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a string of only integer characters str, separate the string into subsets such that each subset has the same
 * single digit total sum. Use an underscore _ to separate subsets in the input string. Return all such subset creations
 * in a result array, and then sort that array lexicographically before return it.
 */
public class SubsetSeparation {
    public static List<String> subsetSeparation(String str) {
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            sum += str.charAt(i) - '0';
        }

        List<String> results = new LinkedList<>();
        for (int i = 1; i <= str.length(); i++) {
            if (sum % i != 0) {
                continue;
            }
            int targetSum = sum / i;
            findSubsets(str, "", targetSum, 0, i, results);
        }

        results.sort(String::compareTo);
        return results;
    }

    // Recursive function to find all subsets that have a sum of targetSum
    private static void findSubsets(String str, String curr, int targetSum, int index, int left, List<String> results) {
        if (index >= str.length()) {
            if (left <= 0) {
                results.add(curr.substring(1));
            }
            return;
        }

        int sum = 0;
        for (int i = index; i < str.length(); i++) {
            sum += str.charAt(i) - '0';
            if (sum == targetSum) {
                findSubsets(str, curr + '_' + str.substring(index, i + 1), targetSum, i + 1, left - 1, results);
            }
            if (sum > targetSum) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        String str = "16253443";
        List<String> result = subsetSeparation(str);
        System.out.println(result); // prints [16253443, 1625_3443, 16_25_34_43]
    }
}
