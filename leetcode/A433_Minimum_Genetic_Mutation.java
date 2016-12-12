package leetcode;
import java.util.*;

public class A433_Minimum_Genetic_Mutation {
	public int minMutation(String start, String end, String[] bank) {
        if (start.equals(end)) return 0;
        Set<String> dict = new HashSet<>(Arrays.asList(bank));
        if (!dict.contains(end)) return -1;
        
        char[] genes = {'A', 'C', 'G', 'T'};
        Queue<String> queue = new LinkedList<>();
        Set<String> used = new HashSet<>();
        
        queue.offer(start);
        used.add(start);
        int step = 0;
        
        while (!queue.isEmpty()) {
        	step++;
        	int size = queue.size();
        	for (int i = 0; i < size; i++) {
        		String current = queue.poll();
        		// Permutate next possible gene
        		for (int j = 0; j < current.length(); j++) {
        			char[] ca = current.toCharArray();
        			for (int k = 0; k < 4; k++) {
        				ca[j] = genes[k];
        				String next = new String(ca);
        				if (dict.contains(next) && !used.contains(next)) {
        					if (next.equals(end)) {
            					return step + 1;
            				}
        					used.add(next);
        					queue.add(next);
        				}
        			}
        		}
        	}
        }
        
        return -1;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
