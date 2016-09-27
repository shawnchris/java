package leetcode;

public class A390_Elimination_Game {
    public int lastRemaining(int n) {
    	int head = 1, step = 1;
    	boolean fromLeft = true;
        
    	while (n > 1) {
    		if (fromLeft || n % 2 == 1) {
    			// move head
    			head += step;
    		}
    		fromLeft = !fromLeft;
    		step *= 2;
    		n /= 2;
    	}
    	
    	return head;
    }
	public static void main(String[] args) {
		A390_Elimination_Game eg = new A390_Elimination_Game();
		for (int i = 1; i < 20; i++) {
			System.out.println(eg.lastRemaining(i));
		}
	}

}
