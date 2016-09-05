package leetcode;
import java.util.*;

public class A166_Fraction_to_Recurring_Decimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0)
            throw new IllegalArgumentException("Argument 'denominator' is 0");
        if (numerator == 0)
            return "0";
        
        String result = "";
        long n = (long)numerator;
        long d = (long)denominator;
        long a = n / d;
        long b = n % d;
        
        result += a;
        if (b == 0) return result;
        
        // Corner cases like 7/-12, -1/-2147483648
        if (a == 0 && (n > 0 && d < 0 || n < 0 && d > 0)) 
            result = "-" + result;
        if (b < 0) b = -b;
        if (d < 0) d = -d;
        
        result += ".";
        Map<Long, Integer> map = new HashMap<>();
        map.put(b, result.length());
        
        while (true) {
            b = b * 10;
            a = b / d;
            b = b % d;
            result += a;
            
            if (b == 0) break;
            
            if (map.containsKey(b)) {
                int startIdx = map.get(b);
                result = result.substring(0, startIdx) + "(" +
                    result.substring(startIdx) + ")";
                break;
            }
            else {
                map.put(b, result.length());
            }
        }
        
        return result;
    }
}
