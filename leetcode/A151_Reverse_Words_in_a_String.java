package leetcode;

public class A151_Reverse_Words_in_a_String {
    public String reverseWords(String s) {
        if (s.length() > 0) {
            String[] parts = s.split("\\s+");
            StringBuilder sb = new StringBuilder();
            for (int i = parts.length - 1; i >= 0; i--)
                sb.append(parts[i] + " ");
            return sb.toString().trim();
        }
        else return "";
    }
}
