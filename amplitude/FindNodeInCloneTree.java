package amplitude;

public class FindNodeInCloneTree {
  TreeNode result, target;

  public void traverse(TreeNode o, TreeNode c) {
    if (o != null) {
      if (o == target) {
        result = c;
      }
      traverse(o.left, c.left);
      traverse(o.right, c.right);
    }
  }

  public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
    this.target = target;
    traverse(original, cloned);
    return result;
  }
}
