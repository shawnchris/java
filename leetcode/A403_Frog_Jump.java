package leetcode;

public class A403_Frog_Jump {
    public boolean canCross(int[] stones) {
    	if (stones[1] != 1) return false;
        return helper(stones, 1, 1);
    }
    
    private boolean helper(int[] stones, int lastJump, int start) {
    	// base case
    	if (start == stones.length - 1) {
    		return true;
    	}
    	
    	for (int i = -1; i < 2; i++) {
    		int nextJump = lastJump + i;
    		if (nextJump <= 0) continue;
    		int nextStone = stones[start] + nextJump;

    		boolean findNextStone = false;
    		int next = start + 1;
    		for (; next < stones.length; next++) {
    			if (stones[next] == nextStone) {
    				findNextStone = true;
    				break;
    			}
    			if (stones[next] > nextStone) break;
    		}
    		if (findNextStone) {
    			if (helper(stones, nextJump, next)) return true;
    		}
    	}
    	
    	return false;
    }
    
	public static void main(String[] args) {
		A403_Frog_Jump fj = new A403_Frog_Jump();
		int[] stones1 = {0,1,3,5,6,8,12,17};
		int[] stones2 = {0,1,2,3,4,8,9,11};
		System.out.println(fj.canCross(stones1));
		System.out.println(fj.canCross(stones2));
	}

}
