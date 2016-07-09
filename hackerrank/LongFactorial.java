package hackerrank;
import java.util.*;
import java.math.*;
public class LongFactorial {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		BigInteger f = new BigInteger("1");
		for (int i = 2; i <= n; i++)
			f = f.multiply(new BigInteger(i+""));
		
		System.out.println(f);
		in.close();

	}

}
