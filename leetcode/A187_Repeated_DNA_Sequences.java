package leetcode;
import java.util.*;

public class A187_Repeated_DNA_Sequences {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        int len = s.length();
        if (len < 10) return result;
        
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i <= len - 10; i++) {
            String ss = s.substring(i, i + 10);
            Integer count = map.get(ss);
            if (count == null)
                count = 1;
            else
                count += 1;
            map.put(ss, count);
        }
        
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1)
                result.add(entry.getKey());
        }
        
        return result;
    }
    
    public static List<String> findRepeatedDnaSequences2(String s) {
    	byte[] bitmap = new byte[1048576];
    	
        List<String> result = new ArrayList<String>();
        if (s == null || s.length() <= 10) return result;
        
        int key = 0, mask1 = (1 << 20) - 1, mask2 = 3;
        Map<Character, Integer> ctoi = new HashMap<>();
        Map<Integer, Character> itoc = new HashMap<>();
        
        ctoi.put('A', 0);
        ctoi.put('C', 1);
        ctoi.put('G', 2);
        ctoi.put('T', 3);
        itoc.put(0, 'A');
        itoc.put(1, 'C');
        itoc.put(2, 'G');
        itoc.put(3, 'T');
        
        for (int i = 0; i < s.length(); i++) {
            key <<= 2;
            key |= ctoi.get(s.charAt(i));
            if (i < 9) continue;
            key &= mask1;
            byte value = bitmap[key];
            if (value < 2) value++;
            bitmap[key] = value;
        }
        
        for (int i = 0; i < bitmap.length; i++) {
        	if (bitmap[i] == 2) {
                int key2 = i;
                String res = "";
                for (int j = 0; j < 10; j++) {
                    res = itoc.get(key2 & mask2) + res;
                    key2 >>= 2;
                }
                result.add(res);
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
    	System.out.println(findRepeatedDnaSequences2("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }
}
