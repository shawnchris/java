package ebay;

/**
 * You are given three integers in the form of strings: firstnum, secondnum, and thirdnum. Your task is to check whether
 * it is possible to erase at most one digit from firstnum, so that the resulting string contains at least one digit,
 * has no leading zeros and the value of thirdnum is equal to the sum of the values of firstnum and secondnum.
 */
public class Oa2 {
    boolean solution(String firstnum, String secondnum, String thirdnum) {
        int first = Integer.parseInt(firstnum);
        int second = Integer.parseInt(secondnum);
        int third = Integer.parseInt(thirdnum);
        int sum = first + second;
        if (third == sum) return true;
        for (int i = 0; i < firstnum.length(); i++) {
            String newFirst = firstnum.substring(0, i) + firstnum.substring(i + 1);
            if (newFirst.length() == 0 || (newFirst.length() > 1 && newFirst.charAt(0) == '0')) {
                continue;
            }
            if (Integer.parseInt(newFirst) + second == third) {
                return true;
            }
        }
        return false;
    }
}
