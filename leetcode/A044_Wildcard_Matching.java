package leetcode;

public class A044_Wildcard_Matching {
    public boolean isMatch(String s, String p) {
        int slen = s.length();
		int plen = p.length();
		int sp=0, pp=0, matchp=0, starp=-1;
		
		while (sp<slen) {
		    // advancing both pointers
			if (pp<plen && (s.charAt(sp)==p.charAt(pp) || p.charAt(pp)=='?')) {
				sp++; pp++;
			}
			// * found, only advancing pattern pointer
			else if (pp<plen && p.charAt(pp)=='*') {
				starp=pp;
				matchp=sp;
				//sp++;
				pp++;
			}
			// last pattern pointer was *, advancing string pointer
			else if(starp!=-1) {
				pp = starp+1;
				matchp++;
				sp=matchp;
			}
			// current pattern pointer is not star, last pattern pointer was not *
            // characters do not match
			else
				return false;
		}
		// check for remaining characters in pattern
		while (pp<plen && p.charAt(pp)=='*')
			pp++;
		
		return (pp==plen);
    }
}
