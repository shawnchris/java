package leetcode;
import java.util.*;

public class A522_Longest_Uncommon_Subsequence_II {
	public int findLUSlength(String[] strs) {
	    Map<String, Integer> subseqFreq = new HashMap<>();
	    for (String s : strs) 
	        for (String subSeq : getSubseqs(s))
	            subseqFreq.put(subSeq, subseqFreq.getOrDefault(subSeq, 0) + 1);
	    int longest = -1;
	    for (Map.Entry<String, Integer> entry : subseqFreq.entrySet()) 
	        if (entry.getValue() == 1) longest = Math.max(longest, entry.getKey().length());
	    return longest;
	}

	public static Set<String> getSubseqs(String s) {
	    Set<String> res = new HashSet<>();
	    if (s.length() == 0) {
	         res.add("");
	         return res;
	    }
	    Set<String> subRes = getSubseqs(s.substring(1));
	    res.addAll(subRes);
	    for (String seq : subRes) res.add(s.charAt(0) + seq);
	    return res;
	}
}
