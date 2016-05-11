package leetcode;

public class A121_Best_Time_to_Buy_and_Sell_Stock {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len <= 1) return 0;
        
        int lowest = prices[0];
        int maxProfit = 0;
        
        for (int i = 1; i < len; i++) {
            lowest = Math.min(lowest, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - lowest);
        }
        
        return maxProfit;
    }
}
