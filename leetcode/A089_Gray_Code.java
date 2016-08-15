package leetcode;
import java.util.*;
/* Every time add a prefix, then reverse order add previous elements.
[0,1,3,2,6,7,5,4]
000 - 0
001 - 1
011 - 3
010 - 2
110 - 6
111 - 7
101 - 5
100 - 4
*/
public class A089_Gray_Code {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(0);
        for (int prefix = 1; prefix < 1 << n; prefix <<= 1) {
            for (int i = result.size() - 1; i >= 0; i--) { 
                // tip: result.size() == prefix
                result.add(prefix + result.get(i));
            }
        }
        
        return result;
    }
}
