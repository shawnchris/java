package other;

import java.util.*;

//Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
//
//
//Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//Output: 3
//Explanation: The LCA of nodes 5 and 1 is 3.
//Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//       3
//     5    1
//   6  2  0  8
//     7 4
//Output: 5
//Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition
public class B {

    public static A.TreeNode lca(A.TreeNode root, A.TreeNode p, A.TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }

        A.TreeNode left = lca(root.left, p, q);
        A.TreeNode right = lca(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    private static A.TreeNode createTree(Integer[] nodes) {
        if (nodes.length == 0) {
            return null;
        }
        A.TreeNode root = new A.TreeNode(nodes[0]);
        Queue<A.TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;
        while (index < nodes.length) {
            A.TreeNode node = queue.poll();
            if (nodes[index] != null) {
                node.left = new A.TreeNode(nodes[index]);
            }
            index++;
            if (index < nodes.length && nodes[index] != null) {
                node.right = new A.TreeNode(nodes[index]);
            }
            index++;
            queue.add(node.left);
            queue.add(node.right);
        }
        return root;
    }

    private static A.TreeNode find(A.TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        A.TreeNode left = find(root.left, val);
        if (left != null) {
            return left;
        }
        return find(root.right, val);
    }
    public static void main(String[] args) {
        Integer[] example2 = new Integer[] {3,5,1,6,2,0,8,null,null,7,4};
        A.TreeNode root = createTree(example2);
        A.TreeNode result = lca(root, find(root, 5), find(root, 4));
        System.out.println(result.val);
    }
}
