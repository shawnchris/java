package leetcode;

public class A309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown {
	public int maxProfit(int[] prices) {
		if (prices.length < 2) return 0;
		if (prices.length == 2) return prices[1] > prices[0] ? prices[1] - prices[0] : 0;
		
        int[] buy = new int[prices.length];
        int[] sell = new int[prices.length];
        
        // init
        buy[0] = -prices[0];
        buy[1] = prices[0] < prices[1] ? -prices[0] : -prices[1];
        sell[0] = 0;
        sell[1] = prices[0] < prices[1] ? prices[1] - prices[0] : 0;
        
        // buy[i] = max{pause[i - 1] - price[i], buy[i - 1]}
        // sell[i] = max{buy[i - 1] + price[i], sell[i - 1]}
        // pause[i] = max{buy[i - 1], sell[i - 1], pause[i - 1]}
        // sell[i] >= buy[i] >= pause[i]
        for (int i = 2; i < prices.length; i++) {
        	buy[i] = Math.max(sell[i - 2] - prices[i], buy[i - 1]);
        	sell[i] = Math.max(buy[i - 1] + prices[i], sell[i - 1]);
        }
        
        return sell[prices.length - 1];
    }
	
	public static void main(String[] args) {
		A309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown best = new A309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown();
		int[] prices1 = {1, 2, 4};
		System.out.println(best.maxProfit(prices1)); //3
		int[] prices2 = {1, 2, 3, 0, 2};
		System.out.println(best.maxProfit(prices2)); //3
	}
}
