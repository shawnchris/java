package other;

public class LuckyNumber {
	public int getNumberOfLuckyBusTickets() {
		int[][] dp = new int[4][30];
		for (int S = 1; S <= 27; S++) {
			dp[0][S] = 0;
		}
		for (int N = 0; N <= 3; N++) {
			dp[N][0] = 1;
		}
		for (int N = 1; N <= 3; N++) {
			for (int S = 1; S <= 27; S++) {
				int lim = Math.min(S, 9);
				dp[N][S] = 0;
				for (int i = 0; i <= lim; i++) {
					dp[N][S] = dp[N][S] + dp[N - 1][S - i];
				}
			}
		}
		
		int result = 0;
		for (int S = 0; S <= 27; S++) {
			result += dp[3][S] * dp[3][S];
		}
		
		return result;
	}

	public static void main(String[] args) {
		LuckyNumber ln = new LuckyNumber();
		System.out.println(ln.getNumberOfLuckyBusTickets());

	}

}
