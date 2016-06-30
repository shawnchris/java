package leetcode;
import java.util.*;

public class A018_4Sum {
	public List<List<Integer>> fourSum(int[] num, int target) {
        List<List<Integer>> results = new LinkedList<List<Integer>>();
        if (num == null || num.length < 4)
            return results;
        Arrays.sort(num);

        for (int s = 0; s < num.length - 3; s++) {
            if (s > 0 && num[s] == num[s - 1])  continue;


            for (int e = num.length - 1; e >= s + 3; e--) {
                if (e < num.length - 1 && num[e] == num[e + 1]) continue;

                int local = target - num[s] - num[e];
                int j = s + 1;
                int k = e - 1;
                while (j < k) {
                    if (num[j] + num[k] == local) {
                        results.add(Arrays.asList(num[s], num[j], num[k], num[e]));
                        j++;
                        while (j < k && num[j] == num[j - 1])
                            j++;
                        k--;
                        while (j < k && num[k] == num[k + 1])
                            k--;
                    }
                    else if ((num[j] + num[k]) < local) {
                        j++;
                        while (j < k && num[j] == num[j - 1])
                            j++;
                    }
                    else {
                        k--;
                        while (j < k && num[k] == num[k + 1])
                            k--;
                    }
                }
            }
        }
        return results;
    }
}
