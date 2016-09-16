package leetcode;

/*
	For each number x in range[i~j]
	we do: result_when_pick_x = x + max{DP([i~x-1]), DP([x+1, j])}
	the max means whenever you choose a number, the feedback is
	always bad and therefore leads you to a worse branch.
	then we get DP([i~j]) = min{xi, ... ,xj}
	this min makes sure that you are minimizing your cost.
*/
public class A375_Guess_Number_Higher_or_Lower_II {
    public int getMoneyAmount(int n) {
        int[][] table = new int[n+1][n+1];
        return search(table, 1, n);
    }
    
    int search(int[][] t, int s, int e) {
        // base case
        if(s >= e) return 0;
        
        // memorized search
        if(t[s][e] != 0) return t[s][e];
        
        int res = Integer.MAX_VALUE;
        
        for(int x = s; x <= e; x++) {
            int tmp = x + Math.max(search(t, s, x - 1), search(t, x + 1, e));
            res = Math.min(res, tmp);
        }
        
        t[s][e] = res;
        
        return res;
    }
}
