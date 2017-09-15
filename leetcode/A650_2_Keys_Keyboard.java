package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class A650_2_Keys_Keyboard {
    class Stat {
        int currLen;
        int clipLen;
        public Stat (int a, int b) {
            currLen = a; clipLen = b;
        }
    }

    public int minSteps(int n) {
        if (n == 1) return 0;

        int step = 1;
        Queue<Stat> queue = new LinkedList<>();
        queue.add(new Stat(1, 1));
        Set<String> set = new HashSet<>();
        set.add("1,1");

        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Stat s = queue.poll();
                // Copy All
                if (!set.contains(s.currLen + "," + s.currLen)) {
                    queue.add(new Stat(s.currLen, s.currLen));
                    set.add(s.currLen + "," + s.currLen);
                }
                // Paste
                if (s.currLen + s.clipLen == n) return step;
                if (!set.contains((s.currLen + s.clipLen) + "," + s.clipLen) && s.currLen + s.clipLen < n) {
                    queue.add(new Stat(s.currLen + s.clipLen, s.clipLen));
                    set.add((s.currLen + s.clipLen) + "," + s.clipLen);
                }
            }
        }

        return -1;
    }
}
