package leetcode;

public class A476_Number_Complement {
	public static int findComplement(int num) {
        int mask = (Integer.highestOneBit(num) << 1) - 1;
        return ~num & mask;
    }
	public static void main(String[] args) {
		System.out.println(findComplement(5));
		System.out.println(findComplement(1));
		System.out.println(findComplement(-1));
		System.out.println(findComplement(-2));
		System.out.println(findComplement(8));
		System.out.println(findComplement(20161211));
	}

}
