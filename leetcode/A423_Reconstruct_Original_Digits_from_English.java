package leetcode;

public class A423_Reconstruct_Original_Digits_from_English {
	public String originalDigits(String s) {
        String[] nums = {"zero", "two", "four", "six", "eight", "seven", "three", "five", "nine", "one"};
        int[] index = {0, 2, 4, 6, 8, 7, 3, 5, 9, 1};
        int[] digit = new int[10];
        int[] letter = new int[26];
        
        for (int i = 0; i < s.length(); i++) {
            letter[s.charAt(i) - 'a']++;
        }
        
        for (int i = 0; i < 10; i++) {
            char[] num = nums[i].toCharArray();
            while (contain(letter, num)) {
                digit[index[i]]++;
                remove(letter, num);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < digit[i]; j++) {
                sb.append(i);
            }
        }
        
        return sb.toString();
    }
    
    private boolean contain(int[] letter, char[] num) {
        for (char c : num) {
            if (letter[c - 'a'] <= 0) {
                return false;
            }
        }
        return true;
    }
    
    private void remove(int[] letter, char[] num) {
        for (char c : num) {
            letter[c - 'a']--;
        }
    }
}
