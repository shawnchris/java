package leetcode;

public class A649_Dota2_Senate {
    public String predictPartyVictory(String senate) {
        int r = 0, d = 0, start = 0;
        char[] arr = senate.toCharArray();
        for (char c : arr) {
            if (c == 'R') r++;
            else d++;
        }

        while (r > 0 && d > 0) {
            while (arr[start] != 'R' && arr[start] != 'D') {
                start = (start + 1) % arr.length;
            }

            char ban = 'R';
            if (arr[start] == 'R') {
                ban = 'D';
                d--;
            }
            else {
                r--;
            }
            int idx = (start + 1) % arr.length;
            while (arr[idx] != ban) {
                idx = (idx + 1) % arr.length;
            }

            arr[idx] = ' ';
            start = (start + 1) % arr.length;
        }

        return d == 0 ? "Radiant" : "Dire";
    }
}
