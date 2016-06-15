package leetcode;

public class A010_Regular_Expression_Matching {
	/*
	Basically, the OPT[i][j] means preceding substring of length i of s and length j of p.
	For any two substrings, the value of OPT[i][j] can be from one of following four cases:
	    case 1: OPT[i-1][j-1] is true, and ith character of s is equal to jth character of p. Or jth character of p is '.'
	    case 2: OPT[i-1][j] is true, then my pattern now is '*' and preceding character is equal to incoming character of s
	    case 3: OPT[i][j-1] is true, then my pattern now is '*' which can match an empty string
	    case 4: OPT[i][j-2] is true, and the pattern like (a*) matches an empty string
	base case is the OPT[0][0], OPT[i][0], OPT[0][j].
	*/
	public boolean isMatch(String s, String p) {
        if (s == null && p == null) return true;
        if (s.length() == 0 && p.length() == 0) return true;

        boolean[][] matrix = new boolean[s.length()+1][p.length()+1];
        matrix[0][0]=true;
        for (int i = 1; i <= s.length(); i++)
            matrix[i][0]=false;

        for (int j = 1; j <= p.length(); j++)
            if (p.charAt(j-1) == '*' && j > 1)
                matrix[0][j] = matrix[0][j-2];
            else matrix[0][j] = false;

        for (int i = 1; i <= s.length(); i++)
            for (int j = 1; j <= p.length(); j++) {

                if (p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.')
                    matrix[i][j] = matrix[i-1][j-1];

                else if (p.charAt(j-1) == '*' && j > 1)                   
                    if (p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.')
                        matrix[i][j] = matrix[i-1][j] || matrix[i][j-2] || matrix[i][j-1];
                        //matrix[i-1][j]:abb vs ab*: depends on ab vs ab*
                        //matrix[i][j-2] a  vs ab*  depends on a vs a
                        //matrix[i][j-1] ab vs ab*: depends on ab vs ab 
                    else 
                        matrix[i][j] = matrix[i][j-2]; 

                else matrix[i][j] = false;
            }
        
        return matrix[s.length()][p.length()];
    }
}
