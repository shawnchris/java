package interview.glassdoor;

public class CoinChange {

	int countRec(int S[], int m, int n) {
		// If n is 0 then there is 1 solution (do not include any coin)
		if (n == 0)
			return 1;

		// If n is less than 0 then no solution exists
		if (n < 0)
			return 0;

		// If there are no coins and n is greater than 0, then no solution exist
		if (m <= 0 && n >= 1)
			return 0;

		// count is sum of solutions (i) including S[m-1] (ii) excluding S[m-1]
		return countRec(S, m - 1, n) + countRec(S, m, n - S[m - 1]);
	}

	int countDP(int S[], int m, int n) {
		int i, j, x, y;

		// We need n+1 rows as the table is constructed in bottom up manner
		// using
		// the base case 0 value case (n = 0)
		int[][] table = new int[n + 1][m];

		// Fill the enteries for 0 value case (n = 0)
		for (i = 0; i < m; i++)
			table[0][i] = 1;

		// Fill rest of the table enteries in bottom up manner
		for (i = 1; i < n + 1; i++) {
			for (j = 0; j < m; j++) {
				// Count of solutions including S[j]
				x = (i - S[j] >= 0) ? table[i - S[j]][j] : 0;

				// Count of solutions excluding S[j]
				y = (j >= 1) ? table[i][j - 1] : 0;

				// total count
				table[i][j] = x + y;
			}
		}
		
		for (i = 0; i < n + 1; i++) {
			for (j = 0; j < m; j++)
				System.out.print(table[i][j] + " ");
			System.out.println();
		}
		return table[n][m - 1];
		
	}
	
	int countDP2( int S[], int m, int n ){
	    // table[i] will be storing the number of solutions for
	    // value i. We need n+1 rows as the table is constructed
	    // in bottom up manner using the base case (n = 0)
	    int [] table = new int[n+1];
	 
	    // Base case (If given value is 0)
	    table[0] = 1;
	 
	    // Pick all coins one by one and update the table[] values
	    // after the index greater than or equal to the value of the
	    // picked coin
	    for(int i = 0; i < m; i++)
	        for(int j = S[i]; j <= n; j++)
	            table[j] += table[j - S[i]];

	    return table[n];
	}
	
	int findMinCoins(int[] coins, int sum) {
	    int[] dp = new int[sum+1];
	    for(int i = 1; i <= sum; i++){
	    	dp[i] = Integer.MAX_VALUE;
	    }
	    dp[0] = 0; //sum 0 , can be made with 0 coins
	    for(int i = 1; i <= sum; i++){
	        for(int j = 0; j < coins.length; j++){
	            //if(i >= coins[j] && i - coins[j] >= 0 && dp[i-coins[j]] + 1 < dp[i]){
	            if(i - coins[j] >= 0 && dp[i-coins[j]] + 1 < dp[i]){
	            	dp[i] = dp[i-coins[j]] + 1;
	            }
	        }
	    }
	    return dp[sum];
	}

	public static void main(String[] args) {
		CoinChange cc = new CoinChange();
		int[] S1 = { 1, 2, 5 };
		//System.out.println(cc.countRec(S1, 3, 7));
		System.out.println(cc.countDP(S1, 3, 7));
		System.out.println(cc.findMinCoins(S1, 7));
	}
}
