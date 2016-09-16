package leetcode;
import java.util.*;

public class A354_Russian_Doll_Envelopes {
	class EnvComparator implements Comparator<Integer> {
		private int[][] envelopes;
		
		public EnvComparator(int[][] envelopes) {
			this.envelopes = envelopes;
		}
		
		public int compare(Integer e1, Integer e2) {
			return envelopes[e1][0] - envelopes[e2][0];
		}
		
		public Integer[] createIndexArray() {
			Integer[] indexes = new Integer[envelopes.length];
			for (int i = 0; i < envelopes.length; i++) {
				indexes[i] = i;
			}
			return indexes;
		}
	}
	
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;
        int n = envelopes.length;
        int[] dp = new int[n];
        
        EnvComparator ec = new EnvComparator(envelopes);
        Integer[] indexes = ec.createIndexArray();
        Arrays.sort(indexes, ec);
        /*
        for (int i = 0; i < indexes.length; i++)
        	System.out.print(indexes[i] + " ");
        System.out.println();
        */
        int max = 0;
        for (int i = 1; i < indexes.length; i++) {
        	int idx1 = indexes[i];
        	for (int j = i - 1; j >= 0; j--) {
        		int idx2 = indexes[j];
        		if (envelopes[idx1][0] > envelopes[idx2][0] && envelopes[idx1][1] > envelopes[idx2][1]) {
        			dp[i] = Math.max(dp[i], dp[j] + 1);
        			if (dp[i] > max) {
        				max = dp[i];
        				break;
        			}
        		}
        	}
        }
        
        return max + 1;
    }
    
	public static void main(String[] args) {
		A354_Russian_Doll_Envelopes rde = new A354_Russian_Doll_Envelopes();
		int[][] e1 = {{5,4},{6,4},{6,7},{2,3}};
		System.out.println(rde.maxEnvelopes(e1)); //3
	}

}
