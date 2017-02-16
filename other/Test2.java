package other;
import java.util.*;

public class Test2 {

	public static void main(String[] args) {
		System.out.println(true ^ false);
		byte[] array;
		byte b = 0b1111111;
		byte mask = 0b1111;
		b = (byte)(b & mask);
		System.out.println(b);
		
		String pattern = "[+-]?(([0-9]+)|([0-9]*(\\.[0-9]+)))(e[0-9]+)?";
		
		String[] ss = {"0", // true
				" 0.1 ", // true
				"abc", // false
				"1 a", // false
				"2e10", // true
				"11", // true
				"-e.1", // false
				"-5.2e1", // true
				"+", // false
				};
		
		for (String s : ss) {
			System.out.println(s.trim().matches(pattern));
		}
	}

}
