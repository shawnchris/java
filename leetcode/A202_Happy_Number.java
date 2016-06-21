package leetcode;
import java.util.*;

public class A202_Happy_Number {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        boolean happy = true;
        
        while (n != 1) {
            int next = 0;
            while (n > 0) {
                next += (n % 10) * (n % 10);
                n /= 10;
            }
            if (set.contains(next)) {
                happy = false;
                break;
            }
            else {
                set.add(next);
            }
            n = next;
        }
        
        return happy;
    }
}
