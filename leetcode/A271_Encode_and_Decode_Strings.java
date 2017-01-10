package leetcode;
import java.util.*;

public class A271_Encode_and_Decode_Strings {
	static class Codec {

	    // Encodes a list of strings to a single string.
	    public String encode(List<String> strs) {
	        StringBuilder sb = new StringBuilder();
	        for (String s : strs) {
	            sb.append(s.length() + "|");
	            sb.append(s);
	        }
	        return sb.toString();
	    }

	    // Decodes a single string to a list of strings.
	    public List<String> decode(String s) {
	        List<String> result = new ArrayList<String>();
	        int start = 0;
	        while (start < s.length()) {
	            int separator = s.indexOf('|', start);
	            start = separator + Integer.parseInt(s.substring(start, separator)) + 1;
	            result.add(s.substring(separator + 1, start));
	        }
	        return result;
	    }
	}
	
	public static void main(String[] args) {
		Codec codec = new Codec();
		List<String> strs = new ArrayList<>();
		strs.add("");
		System.out.println(codec.decode(codec.encode(strs)));

	}

}
