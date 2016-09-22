package leetcode;
import java.util.*;

public class A386_Lexicographical_Numbers {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();
        helper(0, n, result);
        return result;
    }
    
    private void helper(int parent, int max, List<Integer> result) {
    	int start = 0;
    	if (parent == 0) start = 1;
    	for (int i = start; i < 10; i++) {
    		int j = parent * 10 + i;
    		if (j <= max) result.add(j);
    		if (j * 10 <= max)
    			helper(j, max, result);
    	}
    }
    
	public static void main(String[] args) {
		A386_Lexicographical_Numbers ln = new A386_Lexicographical_Numbers();
		System.out.println(ln.lexicalOrder(321));
	}

}
