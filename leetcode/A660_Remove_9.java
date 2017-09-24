package leetcode;

public class A660_Remove_9 {
    // This is a radix problem. Just change decimal to 9-based.
    public int newInteger(int n) {
        return Integer.parseInt(Integer.toString(n, 9));
    }

    //Do it yourself.
    public int newInteger2(int n) {
        int ans = 0;
        int base = 1;

        while (n > 0){
            ans += n % 9 * base;
            n /= 9;
            base *= 10;
        }
        return ans;
    }
}
