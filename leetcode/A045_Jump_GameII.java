package leetcode;

public class A045_Jump_GameII {
	//Greedy solution
	//Let's say the range of the current jump is [curBegin, curEnd], 
	//curFarthest is the farthest point that all points in [curBegin, curEnd] can reach. 
	//Once the current point exceeds curEnd, then trigger another jump, 
	//and set the new curEnd with curFarthest, repeat the above steps.

    public int jump(int[] nums) {
        int jumps = 0, curEnd = 0, curFarthest = 0;
        int len = nums.length;
        
        for (int i = 0; i < len - 1; i++) {
            curFarthest = Math.max(curFarthest, i + nums[i]);
            if (i >= curEnd) {
                jumps++;
                curEnd = curFarthest;
                if (curEnd >= len - 1)
                    break;
            }
        }
        
        return jumps;
    }
}
