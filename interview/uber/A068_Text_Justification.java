package interview.uber;
import java.util.*;

public class A068_Text_Justification {
	private String space(int n) {
        String res = "";
        for (int k = 0; k < n; k++)
            res += " ";
        return res;
    }
    
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        if (words == null) return null;
        int len = words.length;
        if (len == 0) return result;
        
        int i = 0, j = 0, curLen = 0;
        while (i < len) {
            curLen = words[i].length();
            j = i;
            while (j + 1 < len && curLen + words[j + 1].length() + 1 <= maxWidth) {
                j++;
                curLen += words[j].length() + 1;
            }
            String newLine = "";
            if (i == j) {
                newLine = words[i];
                newLine += space(maxWidth - curLen);
            }
            else {
                int space1 = 0, space2 = 0;
                if (j < len - 1) {
                    space1 = (maxWidth - curLen) / (j - i);
                    space2 = (maxWidth - curLen) % (j - i);
                }
                newLine = words[i];
                for (int k = i + 1; k <= j; k++) {
                    newLine += space(space1 + 1);
                    if (space2 > 0) {
                        newLine += " ";
                        space2--;
                    }
                    newLine += words[k];
                }
                if (j == len - 1)
                    newLine += space(maxWidth - curLen);
            }
            result.add(newLine);
            i = j + 1;
        }
        
        return result;
    }
}
