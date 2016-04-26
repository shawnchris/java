package leetcode;

public class A342_Power_of_Four {
	public boolean isPowerOfFour(int num) {
        return num > 0 && (num & (num - 1)) == 0 && (num - 1) % 3 == 0;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
