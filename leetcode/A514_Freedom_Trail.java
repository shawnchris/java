package leetcode;

public class A514_Freedom_Trail {
    public int findRotateSteps(String ring, String key) {
        int n = ring.length();
        int m = key.length();
        int[][] dp = new int[m + 1][n];
        
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (ring.charAt(k) == key.charAt(i)) {
                        int diff = Math.abs(j - k);
                        int step = Math.min(diff, n - diff);
                        dp[i][j] = Math.min(dp[i][j], step + dp[i + 1][k]);
                    }
                }
            }
        }
        
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j < n; j++) {
            	System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }
        
        return dp[0][0] + m;
    }
    
    public int findRotateSteps2(String ring, String key) {
    	key = ring.charAt(0) + key;
        int n = ring.length();
        int m = key.length();
        int[][] dp = new int[m + 1][n];
        
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (ring.charAt(k) == key.charAt(i)) {
                        int diff = Math.abs(j - k);
                        int step = Math.min(diff, n - diff);
                        dp[i][j] = Math.min(dp[i][j], step + dp[i - 1][k]);
                    }
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        if (m > 1) {
	        for (int j = 0; j < n; j++) {
	        	if (ring.charAt(j) == key.charAt(m - 2)) {
	        		min = Math.min(min, dp[m][j]);
	        	}
	        }
        }
        else {
        	min = dp[m][0];
        }
        
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j < n; j++) {
            	System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }
        
        return min + m;
    }
    
	public static void main(String[] args) {
		A514_Freedom_Trail ft = new A514_Freedom_Trail();
		
		System.out.println(ft.findRotateSteps("godding", "gd"));
		System.out.println(ft.findRotateSteps2("godding", "gd"));
		System.out.println(ft.findRotateSteps("edcba", "abcde"));
		System.out.println(ft.findRotateSteps2("edcba", "abcde"));
	}

}
