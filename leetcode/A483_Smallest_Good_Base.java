package leetcode;

public class A483_Smallest_Good_Base {
    public String smallestGoodBase(String n) {
        long m = Long.parseLong(n);
        for (long k = 2; k <= Math.sqrt(m); k++) {
        	if (isBase(m, k)) return k + "";
        }
        return (m - 1) + "";
    }
    
    private boolean isBase(long m, long k) {
    	if (m == 1) return true;
    	if (m % k != 1) return false;
    	return isBase(m / k, k);
    }
    
	public static void main(String[] args) {
		A483_Smallest_Good_Base sgb = new A483_Smallest_Good_Base();
		//System.out.println(sgb.smallestGoodBase("821424692950225218"));
		//System.out.println(sgb.smallestGoodBase("470988884881403701"));
		System.out.println(sgb.smallestGoodBase("727004545306745403"));
	}

}
