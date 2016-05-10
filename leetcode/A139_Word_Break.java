package leetcode;
import java.util.*;
/*
The idea is pretty similar to other DP solution. 
1) keep all positions which could form substring contained in the set in a linkedlist.
2) Iterate the target string, check substring between current position and stored positions. If new sub string hits the dictionary, add it to the front of linkedlist.
3) After iteration, check if the front element of linkedlist equals to the length of string.

This solution is still a time O(n^2) and space O(n) one. It is better if dictionary contains long words. 
*/
public class A139_Word_Break {
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length()==0) return false;
        else if (dict.contains(s)) return true;
        
        // dp[i] stands for can s.substring(0, i) be presented by dict words
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int end = 1; end <= s.length(); end++) {
            boolean found = false;
            for (int lastend = end - 1; lastend >= 0; lastend--) {
                if (dp[lastend] == false) continue;
                if (dict.contains(s.substring(lastend, end))){
                    found = true;
                    break;
                }
            }
            if (found) dp[end] = true;
        }

        return dp[s.length()];
    }
}
