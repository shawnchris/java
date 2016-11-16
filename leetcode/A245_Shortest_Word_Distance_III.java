package leetcode;

public class A245_Shortest_Word_Distance_III {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int index1 = -1, index2 = -1, min = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1) && words[i].equals(word2)) {
                if (index1 >= 0) {
                    min = Math.min(min, i - index1);
                }
                index1 = i;
                index2 = i;
            }
            else if (words[i].equals(word1)) {
                if (index2 >= 0) {
                    min = Math.min(min, i - index2);
                }
                index1 = i;
            }
            else if (words[i].equals(word2)) {
                if (index1 >= 0) {
                    min = Math.min(min, i - index1);
                }
                index2 = i;
            }
        }
        return min;
    }
}
