package com.glassdoor;

public class LongestPrefix {
    public String longestPrefix(String s1, String s2) {
        int iCommon = 0;
        for (int i = 0; i <= Math.min(s1.length(),s2.length());i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                break;
            }else {
                iCommon++;
            }
        }
        return s1.substring(0,iCommon);
    }
	public static void main(String[] args) {
		LongestPrefix lp = new LongestPrefix();
		System.out.println(lp.longestPrefix("gaoshan", "gaofeng"));
	}

}
