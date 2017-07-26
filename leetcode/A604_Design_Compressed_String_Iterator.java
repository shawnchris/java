package leetcode;

import java.util.ArrayList;
import java.util.List;

public class A604_Design_Compressed_String_Iterator {
    List<Character> chars = new ArrayList<>();
    List<Integer> counts = new ArrayList<>();
    int ptr = 0;

    public A604_Design_Compressed_String_Iterator(String str) {
        int i = 0;
        while (i < str.length()) {
            chars.add(str.charAt(i));
            int j = i + 1;
            while (j < str.length() && Character.isDigit(str.charAt(j))) j++;
            counts.add(Integer.parseInt(str.substring(i + 1, j)));
            i = j;
        }
    }

    public char next() {
        if (!hasNext()) return ' ';

        char result = chars.get(ptr);
        counts.set(ptr, counts.get(ptr) - 1);
        if (counts.get(ptr) == 0) ptr++;
        return result;
    }

    public boolean hasNext() {
        return ptr < chars.size();
    }
}
