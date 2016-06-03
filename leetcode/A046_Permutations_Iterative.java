package leetcode;
import java.util.*;

public class A046_Permutations_Iterative {
    public List<List<Integer>> permute(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) return result;
        int len = num.length;
        
        int[] idx = new int[len];
        int level = 0;
        for (int i = 0; i < len; i++) idx[i] = -1;
        Set<Integer> used = new HashSet<>();
        
        while(level >= 0) {
            idx[level] ++;
            if (idx[level] < len) { //index is in range
                if (used.contains(idx[level])) //this number is used
                    continue;
                else { //not used
                    if (level < len - 1) { //this is not the last level
                        used.add(idx[level]);
                        level++;
                    }
                    else { //last level, find a result
                        List<Integer> res = new ArrayList<Integer>();
                        for (int i = 0; i < len; i++)
                            res.add(num[idx[i]]);
                        result.add(res);
                    }
                }
            }
            else { //index is out of range
                idx[level] = -1; //reset the index
                level--; //backtrack
                if (level >= 0)
                    used.remove(idx[level]);
            }
        }
        
        return result;
    }
}
