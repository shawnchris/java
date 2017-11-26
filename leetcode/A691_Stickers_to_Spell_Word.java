package leetcode;

public class A691_Stickers_to_Spell_Word {
    int best;
    int[][] stickersCount;
    int[] targetCount;

    public void search(int ans, int row) {
        if (ans >= best) return;
        if (row == stickersCount.length) {
            for (int c: targetCount) if (c > 0) return;
            best = ans;
            return;
        }

        int used = 0;
        for (int i = 0; i < stickersCount[row].length; i++) {
            if (targetCount[i] > 0 && stickersCount[row][i] > 0) {
                used = Math.max(used, (targetCount[i] - 1) / stickersCount[row][i] + 1);
            }
        }
        for (int i = 0; i < stickersCount[row].length; i++) {
            targetCount[i] -= used * stickersCount[row][i];
        }

        search(ans + used, row + 1);
        while (used > 0) {
            for (int i = 0; i < stickersCount[row].length; i++) {
                targetCount[i] += stickersCount[row][i];
            }
            used--;
            search(ans + used, row + 1);
        }
    }

    public int minStickers(String[] stickers, String target) {
        int[] targetNaiveCount = new int[26];
        for (char c: target.toCharArray()) targetNaiveCount[c - 'a']++;

        int[] index = new int[26];
        int t = 0;
        for (int i = 0; i < 26; i++) {
            if (targetNaiveCount[i] > 0) {
                index[i] = t++;
            } else {
                index[i] = -1;
            }
        }

        targetCount = new int[t];
        t = 0;
        for (int c: targetNaiveCount) if (c > 0) {
            targetCount[t++] = c;
        }

        stickersCount = new int[stickers.length][t];
        for (int i = 0; i < stickers.length; i++) {
            for (char c: stickers[i].toCharArray()) {
                int j = index[c - 'a'];
                if (j >= 0) stickersCount[i][j]++;
            }
        }

        int anchor = 0;
        for (int i = 0; i < stickers.length; i++) {
            for (int j = anchor; j < stickers.length; j++) if (j != i) {
                boolean dominated = true;
                for (int k = 0; k < t; k++) {
                    if (stickersCount[i][k] > stickersCount[j][k]) {
                        dominated = false;
                        break;
                    }
                }

                if (dominated) {
                    int[] tmp = stickersCount[i];
                    stickersCount[i] = stickersCount[anchor];
                    stickersCount[anchor++] = tmp;
                    break;
                }
            }
        }

        best = target.length() + 1;
        search(0, anchor);
        return best <= target.length() ? best : -1;
    }
}
