package leetcode;

public class A278_First_Bad_Version {

	private boolean isBadVersion(int version) {
		return false;
	}
	
	public int firstBadVersion(int n) {
        if (isBadVersion(1)) return 1;
        if (!isBadVersion(n)) return n + 1;
        
        int i = 1, j = n;
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (mid - 1 >= 0 && isBadVersion(mid) && !isBadVersion(mid - 1))
                return mid;
            if (isBadVersion(mid))
                j = mid - 1;
            else
                i = mid + 1;
        }
        
        return i;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
