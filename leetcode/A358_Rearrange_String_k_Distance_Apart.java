package leetcode;

public class A358_Rearrange_String_k_Distance_Apart {
	public static String rearrangeString(String str, int k) {
        if (str == null || str.length() == 0) return "";
        int[] count = new int[26];
        int[] valid = new int[26];
        for (int i = 0; i < str.length(); i++) {
        	count[str.charAt(i) - 'a']++;
        }
        
        char[] arr = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
        	int index = getValidMax(count, valid, i);
        	if (index == -1) {
        		return "";
        	}
        	count[index]--;
        	valid[index] += k;
        	arr[i] = (char)('a' + index);
        }
        
        return new String(arr);
    }
	
	private static int getValidMax(int[] count, int[] valid, int i) {
		int index = -1;
		int max = -1;
		for (int j = 0; j < 26; j++) {
			if (count[j] > 0 && i >= valid[j] && count[j] > max) {
				max = count[j];
				index = j;
			}
		}
		
		return index;
	}
	
	public static void main(String[] args) {
		System.out.println(rearrangeString("aabbcc", 3));
		System.out.println(rearrangeString("aaabc", 3));
		System.out.println(rearrangeString("aaadbbcc", 2));
	}

}
