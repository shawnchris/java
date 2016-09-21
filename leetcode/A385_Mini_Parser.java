package leetcode;
import java.util.*;

public class A385_Mini_Parser {
    public void deserialize(String s) {
        //base case
        if (s.charAt(0) != '[') {
            System.out.println(s);
            return;
        }
        
        //remove the first level of [] pair
        s = s.substring(1, s.length() - 1);
        //parse by breaking string using ','
        for (int i = 0; i < s.length(); i++) {
            int j = i, level = 0;
            for (; j < s.length(); j++) {
                if (s.charAt(j) == '[') level++;
                else if (s.charAt(j) == ']') level--;
                else if (s.charAt(j) == ',' && level == 0) {
                    break;
                }
            }
            deserialize(s.substring(i, j));
            i = j;
        }
        
    }
	public static void main(String[] args) {
		A385_Mini_Parser mp = new A385_Mini_Parser();
		mp.deserialize("[123,[456,[789]]]");

	}

}
