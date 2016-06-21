package leetcode;

public class TrieNode {
    private static final int R = 26;
    public TrieNode[] next;
    public boolean isEnd;
    public TrieNode() {
        next = new TrieNode[R];
        isEnd = false;
    }
}
