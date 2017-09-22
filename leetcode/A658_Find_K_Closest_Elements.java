package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class A658_Find_K_Closest_Elements {
    // Binary search to find the crossover point
    int findCrossOver(List<Integer> arr, int low, int high, int x) {
        if (arr.get(high) <= x) return high;
        if (arr.get(low) > x)  return low;

        int mid = low + (high - low)/2;

        if (arr.get(mid) <= x && arr.get(mid+1) > x) return mid;

        if(arr.get(mid) < x) return findCrossOver(arr, mid+1, high, x);
        return findCrossOver(arr, low, mid - 1, x);
    }

    public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
        List<Integer> res = new ArrayList<>();

        int l = findCrossOver(arr, 0, arr.size() - 1, x);
        int r = l + 1;
        int count = 0;

        while (l >= 0 && r < arr.size() && count < k) {
            if (x - arr.get(l) <= arr.get(r) - x) res.add(arr.get(l--));
            else res.add(arr.get(r++));
            count++;
        }

        while (count < k && l >= 0) {
            res.add(arr.get(l--));
            count++;
        }
        while (count < k && r < arr.size()) {
            res.add(arr.get(r++));
            count++;
        }

        Collections.sort(res);
        return res;
    }
}
