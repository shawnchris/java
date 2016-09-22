package leetcode;

public class A387_First_Unique_Character_in_a_String {
    public int firstUniqChar(String s) {
        int[] mark = new int[26];
        for (int i = 0; i < s.length(); i++) {
        	mark[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
        	if (mark[s.charAt(i) - 'a'] == 1)
        		return i;
        }
        return -1;
    }
	public static void main(String[] args) {
		A387_First_Unique_Character_in_a_String fu = new A387_First_Unique_Character_in_a_String();
		System.out.println(fu.firstUniqChar("leetcode"));
		System.out.println(fu.firstUniqChar("loveleetcode"));
	}

}
