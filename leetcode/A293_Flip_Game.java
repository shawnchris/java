package leetcode;
import java.util.*;

public class A293_Flip_Game {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> result = new ArrayList<String>();
        if (s.length() < 2) return result;
        
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                String res = s.substring(0, i) + "--";
                if (i + 2 < s.length()) {
                    res += s.substring(i + 2);
                }
                result.add(res);
            }
        }
        
        return result;
    }
}
