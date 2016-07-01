package leetcode;
import java.util.*;

public class A015_3Sum {
	public List<List<Integer>> threeSum(int[] num) {
		Arrays.sort(num);
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        for(int i = 0; i < num.length-2; ++i) {
            if(i > 0 && num[i] == num[i-1])
                continue;

            int j = i + 1, k = num.length-1;

            while(j < k) {
                int candidate = num[i] + num[j] + num[k];
                if (candidate == 0) {
                    result.add(Arrays.asList(num[i], num[j], num[k]));
                    do {j++;} while (j < k && num[j] == num[j-1]);
                    do {k--;} while (k > j && num[k] == num[k+1]);
                }
                else if(candidate < 0) {
                    do {j++;} while (j < k && num[j] == num[j-1]);
                }
                else {
                    do {k--;} while (k > j && num[k] == num[k+1]);
                }
            }
        }

        return result;
    }
}
