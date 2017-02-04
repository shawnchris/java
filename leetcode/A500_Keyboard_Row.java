package leetcode;
import java.util.*;

public class A500_Keyboard_Row {
private static String[] strs = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
    
    public String[] findWords(String[] words) {
        List<String> res = new ArrayList<>();
        
        for (String word : words) {
            if (word.length() == 0) continue;
            
            int row = 0;
            for (; row < 3; row++) {
                if (inRow(word.charAt(0), row)) break;
            }
            if (row >= 3) continue;
            
            boolean flag = true;
            for (int i = 1; i < word.length(); i++) {
                if (!inRow(word.charAt(i), row)) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                res.add(word);
            }
        }
        
        return res.toArray(new String[res.size()]);
    }
    
    private boolean inRow(char c, int row) {
        if (strs[row].contains(Character.toLowerCase(c)+"")) return true;
        else return false;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
