package leetcode;

public class A188_Best_Time_to_Buy_and_Sell_Stock_IV {
    public int maxProfit(int k, int[] prices) {
        if (k >= prices.length / 2) {
            int result = 0;
            for (int i = 1; i < prices.length; i++) {
                int profit = prices[i] - prices[i - 1];
                if (profit > 0) result += profit;
            }
            return result;
        }
        else {
            int[][] profit = new int[k + 1][prices.length];
            
            for (int i = 1; i <= k; i++) {
                int cost = -prices[0];
                for (int j = 1; j < prices.length; j++) {
                    //Sell at this point or not?
                    profit[i][j] = Math.max(profit[i][j - 1], cost + prices[j]);
                    //Buy at this point or not?
                    cost = Math.max(cost, profit[i - 1][j - 1] - prices[j]);
                }
            }
            
            return profit[k][prices.length - 1];
        }
    }
    
    public static void main(String[] args) {
    	A188_Best_Time_to_Buy_and_Sell_Stock_IV best = new A188_Best_Time_to_Buy_and_Sell_Stock_IV();
    	int[] prices = {3,	8,	7,	6,	9,	15,	10};
    	System.out.print(best.maxProfit(2, prices));
    }
}
