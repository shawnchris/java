package leetcode;
import java.util.*;

public class A401_Binary_Watch {
    public List<String> readBinaryWatch(int num) {
        List<String> result = new ArrayList<>();
        for (int h = 0; h <= num; h++) {
        	int m = num - h;
        	List<String> hours = getHours(h, 0, 0, new ArrayList<String>());
        	List<String> minutes = getMinutes(m, 0, 0, new ArrayList<String>());
        	for (String sh: hours) {
        		for (String sm: minutes) {
        			result.add(sh + ":" + sm);
        		}
        	}
        }
        
        return result;
    }
    
    int[] hourMap = {8, 4, 2, 1};
    int[] minuteMap = {32, 16, 8, 4, 2, 1};
    
    private List<String> getHours(int n, int start, int current, ArrayList<String> res) {
    	if (n == 0) {
    		if (current < 12)
    			res.add(String.valueOf(current));
    	}
    	else {
    		for (int i = start; i < hourMap.length; i++) {
    			getHours(n - 1, i + 1, current + hourMap[i], res);
    		}
    	}
    	
    	return res;
    }
    
    private List<String> getMinutes(int n, int start, int current, ArrayList<String> res) {
    	if (n == 0) {
    		if (current < 60) {
	    		String s = String.valueOf(current);
	    		if (s.length() == 1) s = "0" + s;
	    		res.add(s);
    		}
    	}
    	else {
    		for (int i = start; i < minuteMap.length; i++) {
    			getMinutes(n - 1, i + 1, current + minuteMap[i], res);
    		}
    	}
    	
    	return res;
    }
    
	public static void main(String[] args) {
		A401_Binary_Watch bw = new A401_Binary_Watch();
		//System.out.println(bw.readBinaryWatch(1));
		//System.out.println(bw.readBinaryWatch(2));
		String[] s1 = {"0:48","0:40","0:36","0:34","0:33","0:24","0:20","0:18","0:17","0:12","0:10","0:09","0:06","0:05","0:03","8:32","8:16","8:08","8:04","8:02","8:01","4:32","4:16","4:08","4:04","4:02","4:01","2:32","2:16","2:08","2:04","2:02","2:01","1:32","1:16","1:08","1:04","1:02","1:01","12:00","10:00","9:00","6:00","5:00","3:00"};
		String[] s2 = {"0:03","0:05","0:06","0:09","0:10","0:12","0:17","0:18","0:20","0:24","0:33","0:34","0:36","0:40","0:48","1:01","1:02","1:04","1:08","1:16","1:32","2:01","2:02","2:04","2:08","2:16","2:32","3:00","4:01","4:02","4:04","4:08","4:16","4:32","5:00","6:00","8:01","8:02","8:04","8:08","8:16","8:32","9:00","10:00"};
		Arrays.sort(s1);
		Arrays.sort(s2);
		for (int i = 0; i < s2.length; i++) {
			System.out.println(s1[i] + " " + s2[i]);
		}
	}

}
