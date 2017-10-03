package leetcode;

public class A659_Split_Array_into_Consecutive_Subsequences {
    public boolean isPossible(int[] nums) {
        int i = 0, pre = nums[0], one = 0, two = 0, three = 0;
        while (i < nums.length) {
            int curr = nums[i], count = 0;
            if (curr - pre > 1) {
                if (one > 0 || two > 0) return false;
                else three = 0;
            }
            while (i < nums.length && nums[i] == curr) {
                count++;
                i++;
            }

            System.out.println(one + ", " + two + ", " + three + ", " + count);
            if (count < one + two) return false;
            else if (count >= one + two + three){
                three += two;
                two = one;
                one = count - three - two;
            }
            else {
                three = count - one;
                two = one;
                one = 0;
            }
            pre = curr;
        }
        return one == 0 && two == 0;
    }
}
