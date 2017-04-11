package leetcode;
import java.util.stream.Stream;

public class A537_Complex_Number_Multiplication {
	public class Solution {
	    public String complexNumberMultiply(String a, String b) {
	        int[] coefs1 = Stream.of(a.split("\\+|i")).mapToInt(Integer::parseInt).toArray(), 
	              coefs2 = Stream.of(b.split("\\+|i")).mapToInt(Integer::parseInt).toArray();
	        return (coefs1[0]*coefs2[0] - coefs1[1]*coefs2[1]) + "+" + (coefs1[0]*coefs2[1] + coefs1[1]*coefs2[0]) + "i";
	    }
	}
}
