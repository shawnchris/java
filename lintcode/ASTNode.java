package lintcode;

public class ASTNode {
	public int weight;
	public String value;
	public ASTNode left, right;

	public ASTNode(int w, String v) {
		this.weight = w;
		this.value = v;
		this.left = this.right = null;
	}
}
