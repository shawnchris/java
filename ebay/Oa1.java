package ebay;

import java.util.ArrayList;
import java.util.List;

/**
 * You have a string s. Split s into the minimum possible number of increasing substrings. A substring is considered to
 * be increasing when the next symbol in the substring is also next in the English alphabet. This is case sensitive,
 * i.e. 'b' is next for 'a' but 'C' is not next for 'b'. Return an array of these substrings.
 */
public class Oa1 {
    String[] solution(String s) {
        List<String> result = new ArrayList<>();
        int start = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) - s.charAt(i - 1) != 1) {
                result.add(s.substring(start, i));
                start = i;
            }
        }
        result.add(s.substring(start));

        String[] res = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;
    }

}
