package leetcode;

//maxProfit(i) = leftMax(i) + rightMax(i);
//leftMax(i) = prices[j] - lowest (j = 0...i);
//rightMax(i) = highest - prices[j] (j = len - 1...i)
public class A123_Best_Time_to_Buy_and_Sell_StockIII {
    public int maxProfit(int[] prices) {
        int len = prices.length;
		if (len<= 1) return 0;
		
        int maxProfit = 0;
        int lowest = prices[0], highest = prices[len - 1];
        int[] leftMax = new int[len];
        
        for (int i = 0; i < len; i++) {
            lowest = Math.min(lowest, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - lowest);
            leftMax[i] = maxProfit;
        }
        
        for (int i = len - 1; i >= 0; i--) {
            highest = Math.max(highest, prices[i]);
            maxProfit = Math.max(maxProfit, highest - prices[i] + leftMax[i]);
        }
        
        return maxProfit;
    }
}
