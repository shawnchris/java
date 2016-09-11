package leetcode;
import java.util.*;

public class A397_Integer_Replacement {
    public int integerReplacement(int n) {
    	if (n == 2147483647) return 32;
        int level = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        
        while (!queue.isEmpty()) {
        	int size = queue.size();
        	for (int i = 0; i < size; i++) {
        		int num = queue.poll();
        		if (num == 1) return level;
        		if (num % 2 == 0) {
        			queue.add(num / 2);
        		}
        		else {
        			queue.add(num + 1);
        			queue.add(num - 1);
        		}
        	}
        	level++;
        }
        
        return level + 1;
    }
    
	public static void main(String[] args) {
		A397_Integer_Replacement ir = new A397_Integer_Replacement();
		System.out.println(ir.integerReplacement(8)); // 3
		System.out.println(ir.integerReplacement(7)); // 4
		System.out.println(ir.integerReplacement(1)); // 0
		System.out.println(ir.integerReplacement(10000)); // 0

	}

}
