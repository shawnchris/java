package leetcode;
import java.util.*;

public class A411_Minimum_Unique_Word_Abbreviation {
    public String minAbbreviation(String target, List<String> dictionary) {
        if (!dictionary.stream().anyMatch(x -> x.length() == target.length())) {
            return String.valueOf(target.length());
        }
        int n = target.length();
        List<Integer> sames = new ArrayList<>();
        for (String s : dictionary) {
            if (s.length() == target.length()) {
                sames.add(same(target, s));
            }
        }
        int answer = Integer.MAX_VALUE;
        int masky = 0;
        tag:
        for (int possible = 0; possible < 1 << n; possible++) {
            for (Integer same : sames) {
                if ((possible | same) == same) {
                    continue tag;
                }
            }
            int res = calc(possible, n);
            if (answer > res) {
                answer = res;
                masky = possible;
            }
        }
        return turnToString(target, masky);
    }

    private String turnToString(String target, int masky) {
        int count = 0;
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < target.length(); i++) {
            if ((masky & (1 << i)) != 0) {
                if (count > 0) {
                    answer.append(count);
                    count = 0;
                }
                answer.append(target.charAt(i));
            } else {
                count++;
            }
        }
        if (count > 0) {
            answer.append(count);
        }
        return answer.toString();
    }

    private int calc(int possible, int n) {
        boolean inZero = false;
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if ((possible & (1 << i)) != 0) {
                if (inZero) {
                    answer++;
                    inZero = false;
                }
                answer++;
            } else {
                inZero = true;
            }
        }
        if (inZero) answer++;
        return answer;
    }

    private int same(String a, String b) {
        if (a.length() != b.length()) {
            throw new RuntimeException();
        }
        int answer = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == b.charAt(i)) {
                answer |= 1 << i;
            }
        }
        return answer;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
