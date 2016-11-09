package leetcode;
import java.util.*;

public class A421_Maximum_XOR_of_Two_Numbers_in_an_Array {
    public int findMaximumXOR(int[] nums) {
        int max = 0, mask = 0;
        for (int i = 30; i >= 0; i--) {
            mask |= (1 << i);
            Set<Integer> prefixes = new HashSet<>();
            for (int num : nums) {
                prefixes.add(num & mask);
            }
            
            int nextMax = max | (1 << i);
            for (int prefix : prefixes) {
                if (prefixes.contains(nextMax ^ prefix)) {
                    max = nextMax;
                }
            }
        }
        return max;
    }
}
