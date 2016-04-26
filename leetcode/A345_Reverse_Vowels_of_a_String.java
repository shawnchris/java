package leetcode;
import java.util.*;

public class A345_Reverse_Vowels_of_a_String {
	public String reverseVowels(String s) {
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');
        char[] ca = s.toCharArray();
        int i = 0, j = ca.length - 1;
        while (i < j) {
            while(i < j && !vowels.contains(ca[i]))
                i++;
            while(i < j && !vowels.contains(ca[j]))
                j--;
            if (i < j) {
                char temp = ca[i];
                ca[i] = ca[j];
                ca[j] = temp;
            }
            i++;
            j--;
        }
        
        return new String(ca);
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
