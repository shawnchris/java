package leetcode;

public class A440_Kth_Smallest_in_Lexicographical_Order {
    public int findKthNumber(int n, int k) {
        int currNode = 1;
        int stepLeft = k - 1;
        while (stepLeft > 0) {
            int stepNeed = walk(currNode, currNode + 1, n);
            if (stepLeft >= stepNeed) {
                stepLeft -= stepNeed;
                currNode += 1;
            }
            else {
                stepLeft -= 1;
                currNode *= 10;
            }
        }
        return currNode;
    }
    
    private int walk(long currNode, long nextNode, int n) {
        int stepNeed = 0;
        while (currNode <= n) {
            if (nextNode <= n) {
                stepNeed += nextNode - currNode;
            }
            else {
                stepNeed += n - currNode + 1;
            }
            currNode *= 10;
            nextNode *= 10;
        }
        return stepNeed;
    }
}
