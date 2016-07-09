package hackerrank;

public class MirroredNumbers {
	public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x < 10) return true;
        int y = x;
        int tens = 0;
        while (y >= 10) {
            tens++;
            y = y / 10;
        }
        y = x;
        int len = tens/2;
        for (int i = 0; i <= len; i++) {
            int a = x % 10;
            int b = y;
            for (int j = 0; j < tens; j++)
                b = b / 10;
            if (a != b)
                return false;
            x = x / 10;
            for (int j = 0; j < tens; j++)
                b = b * 10;
            y = y - b;
            tens--;
        }
        
        return true;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 999; i > 900; i--)
			for (int j = 999; j >= i; j--)
				if (isPalindrome(i * j))
					System.out.println(i + " * " + j + " = " + i * j);

	}

}
