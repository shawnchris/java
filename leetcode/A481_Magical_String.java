package leetcode;

public class A481_Magical_String {
    public static int magicalString(int n) {
        if (n <= 0) return 0;
        if (n <= 3) return 1;
        
        int[] a = new int[n + 1];
        a[0] = 1; a[1] = 2; a[2] = 2;
        int head = 2, tail = 3, num = 1;
        
        while (tail < n) {
            for (int i = 0; i < a[head]; i++) {
                a[tail] = num;
                tail++;
            }
            num = num ^ 3;
            head++;
        }
        
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == 1) result++;
        }
        
        return result;
    }
    
	public static void main(String[] args) {
		for (int i = 1; i < 10; i++) {
			System.out.println(i + " : " + magicalString(i));
		}

	}

}
