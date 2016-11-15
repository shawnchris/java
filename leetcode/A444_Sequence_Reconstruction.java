package leetcode;
import java.util.*;

public class A444_Sequence_Reconstruction {
    public static boolean sequenceReconstruction(int[] org, int[][] seqs) {
        // 1. Every sequence in seqs should be a subsequence in org.
    	// 2. Every 2 consecutive elements in org should be consecutive elements in some sequence from seqs.
    	
    	// Corner cases
    	if (org == null || org.length == 0 || seqs == null || seqs.length == 0) return false;
    	
    	// Generate a hash map for org <Element, Position>
    	Map<Integer, Integer> orgIndex = new HashMap<>();
    	for (int i = 0; i < org.length; i++) {
    		orgIndex.put(org[i], i);
    	}
    	
    	// Validate rule 1, create a hash set for any adjacent pairs from seqs
    	Set<String> seqsPair = new HashSet<>();
    	for (int[] seq : seqs) {
    		for (int i = 0; i < seq.length; i++) {
    			// Validation
    			if (!orgIndex.containsKey(seq[i])) return false;
    			if (i > 0) {
    				if (orgIndex.get(seq[i - 1]) > orgIndex.get(seq[i])) return false;
    			}
    			// Creation
    			if (i > 0) {
    				seqsPair.add(seq[i - 1] + "_" + seq[i]);
    			}
    		}
    		
    	}
    	
    	// Validate rule 2.
    	for (int i = 1; i < org.length; i++) {
    		if (!seqsPair.contains(org[i - 1] + "_" + org[i])) return false;
    	}
    	
    	return true;
    }
    
	public static void main(String[] args) {
		int[] org1 = {1,2,3};
		int[][] seqs1 = {{1,2},{1,3}};
		System.out.println(sequenceReconstruction(org1, seqs1));
		
		int[] org2 = {1,2,3};
		int[][] seqs2 = {{1,2}};
		System.out.println(sequenceReconstruction(org2, seqs2));
		
		int[] org3 = {1,2,3};
		int[][] seqs3 = {{1,2},{1,3},{2,3}};
		System.out.println(sequenceReconstruction(org3, seqs3));
		
		int[] org4 = {4,1,5,2,6,3};
		int[][] seqs4 = {{5,2,6,3},{4,1,5,2}};
		System.out.println(sequenceReconstruction(org4, seqs4));
	}

}
