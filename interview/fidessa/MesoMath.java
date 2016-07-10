package interview.fidessa;

import java.util.*;

public class MesoMath {

	static HashMap<String, Integer> map = new HashMap<String, Integer>();
	
	static {
		map.put("A", 1);
		map.put("M", 4);
		map.put("C", 8);
		map.put("G", 25);
		map.put("R", 100);
	}
	
	public int translate(String str) {
		if (str == null) return -1;
		int len = str.length();
		if (len == 0) return 0;
		
		Stack<Integer> stack = new Stack<Integer>(); 
		for (int i = 0; i < len; i++) {
			stack.push(map.get(str.substring(i, i+1)));
		}
		
		boolean increasing = true;
		int pre = stack.pop();
		int sum = pre;
		while (!stack.isEmpty()) {
			int cur = stack.pop();
			if (cur < pre) {
				sum -= cur;
				increasing = false;
			}
			else if (cur > pre) {
				sum += cur;
				increasing = true;
			}
			else {
				if (increasing) {
					sum += cur;
				}
				else {
					sum -= cur;
				}
			}
			pre = cur;
		}
		
		return sum;
	}
	
	
	public static void main(String[] args) {
		MesoMath mm = new MesoMath();
		System.out.println(mm.translate("AA"));
		System.out.println(mm.translate("AM"));
		System.out.println(mm.translate("MA"));
		System.out.println(mm.translate("MMMG"));
		System.out.println(mm.translate("CC"));
		System.out.println(mm.translate("CAG"));
		System.out.println(mm.translate("GMAM"));
		System.out.println(mm.translate("AMCGR"));
	}

}
