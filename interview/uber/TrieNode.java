package interview.uber;

public class TrieNode {
	private static final int R = 26;
    public TrieNode[] next;
    public boolean isWord;
    public TrieNode() {
        next = new TrieNode[R];
        isWord = false;
    }
}
