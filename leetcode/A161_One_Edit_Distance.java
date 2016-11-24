package leetcode;

public class A161_One_Edit_Distance {
    public boolean isOneEditDistance(String s, String t) {
        if (s.length() > t.length()) return isOneEditDistance(t, s);
        
        if (t.length() - s.length() > 1) return false;
        
        int ps = 0, pt = 0;
        while (ps < s.length()) {
            if (s.charAt(ps) != t.charAt(pt)) {
                break;
            }
            ps++;
            pt++;
        }
        
        if (pt == t.length()) return false;
        
        if (s.length() < t.length()) {
            pt++;
        }
        else {
            ps++;
            pt++;
        }
        
        while (ps < s.length()) {
            if (s.charAt(ps) != t.charAt(pt)) {
                break;
            }
            ps++;
            pt++;
        }
        
        if (pt == t.length()) return true;
        return false;
    }
}
