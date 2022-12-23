package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Design an algorithm to encode an N-ary tree into a binary tree and decode the binary tree to get the original N-ary
 * tree. An N-ary tree is a rooted tree in which each node has no more than N children. Similarly, a binary tree is a
 * rooted tree in which each node has no more than 2 children. There is no restriction on how your encode/decode
 * algorithm should work. You just need to ensure that an N-ary tree can be encoded to a binary tree and this binary
 * tree can be decoded to the original N-nary tree structure.
 */

// Step 1). Link all siblings together, like a singly-linked list.
// Each node in the original N-ary tree would correspond uniquely to a node in the resulting binary tree.
// In the first step, we first chain up all the sibling nodes together, i.e. nodes that share the same parent. By
// chaining up, we would link the nodes via either left or right child pointers of the binary tree node. Here we choose
// to do with the right pointer.

// Step 2). Link the head of the obtained list of siblings with its parent node.
// Now that the siblings are chained up, we just need to link this sibling list with their parent node.
// As one can see, we don't have to link each one of the siblings to its parent, and we cannot do so either, since we
// only got two pointers for a node in binary tree. It suffices to pick one of the siblings. Naturally, we could link
// the head of the list with its parent node.
public class A431_Encode_Nary_Tree_To_Binary_Tree {
    static class Pair<U, V> {
        public U first;
        public V second;
        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }
    }

// Time Complexity: O(N) where N is the number of nodes in the N-ary tree. We traverse each node in the tree once and
// only once.
// Space Complexity: O(L) where L is the maximum number of nodes that reside at the same level. Since L is proportional
// to N in the worst case, we could further generalize the time complexity to O(N).
//   We use a queue data structure to do BFS traversal, i.e. visiting the nodes level by level.
//   At any given moment, the queue contains nodes that are at most spread into two levels. As a result, assuming the
//   maximum number of nodes at one level is LLL, the size of the queue would be less than 2L2L2L at any time.
//   Therefore, the space complexity of both encode() and decode() functions is O(L).
    static class Bfs {
        public TreeNode encode(Node root) {
            if (root == null) {
                return null;
            }
            TreeNode newRoot = new TreeNode(root.val);
            Pair<TreeNode, Node> head = new Pair<>(newRoot, root);

            // Add the first element to kickoff the loop
            Queue<Pair<TreeNode, Node>> queue = new ArrayDeque<>();
            queue.add(head);

            while (queue.size() > 0) {
                Pair<TreeNode, Node> pair = queue.remove();
                TreeNode bNode = pair.first;
                Node nNode = pair.second;

                // Encoding the children nodes into a list of TreeNode.
                TreeNode prevBNode = null, headBNode = null;
                for (Node nChild : nNode.children) {
                    TreeNode newBNode = new TreeNode(nChild.val);
                    if (prevBNode == null) {
                        headBNode = newBNode;
                    } else {
                        prevBNode.right = newBNode;
                    }
                    prevBNode = newBNode;

                    Pair<TreeNode, Node> nextEntry = new Pair<TreeNode, Node>(newBNode, nChild);
                    queue.add(nextEntry);
                }

                // Attach the list of children to the left node.
                bNode.left = headBNode;
            }

            return newRoot;
        }

        public Node decode(TreeNode root) {
            if (root == null) {
                return null;
            }
            Node newRoot = new Node(root.val, new ArrayList<>());

            // adding the first element to kickoff the loop
            Queue<Pair<Node, TreeNode>> queue = new ArrayDeque<>();
            Pair<Node, TreeNode> head = new Pair<>(newRoot, root);
            queue.add(head);

            while (queue.size() > 0) {
                Pair<Node, TreeNode> entry = queue.remove();
                Node nNode = entry.first;
                TreeNode bNode = entry.second;

                // Decoding the children list
                TreeNode firstChild = bNode.left;
                TreeNode sibling = firstChild;
                while (sibling != null) {
                    Node nChild = new Node(sibling.val, new ArrayList<>());
                    nNode.children.add(nChild);

                    // prepare the decoding the children of the child, by standing in the queue.
                    Pair<Node, TreeNode> nextEntry = new Pair<>(nChild, sibling);
                    queue.add(nextEntry);

                    sibling = sibling.right;
                }
            }
            return newRoot;
        }
    }


// Time Complexity: O(N) where N is the number of nodes in the N-ary tree. We traverse each node in the tree once and
// only once.
// Space Complexity: O(D) where D is the depth of the N-ary tree. Since D is proportional to N in the worst case, we
// could further generalize the time complexity to O(N).
//   Unlike the BFS algorithm, we don't use the queue data structure in the DFS algorithm. However, implicitly the
//   algorithm would consume more space in the function call stack due to the recursive function calls.
//   And this consumption of call stack space is the main space complexity for our DFS algorithm. As we can see, the
//   size of the call stack at any moment is exactly the number of level where the currently visited node resides, e.g.
//   for the root node (level 0), the recursive call stack is empty.
    static class Dfs {
        public TreeNode encode(Node root) {
            if (root == null) {
                return null;
            }

            TreeNode newRoot = new TreeNode(root.val);

            // Encode the first child of n-ary node to the left node of binary tree.
            if (root.children.size() > 0) {
                Node firstChild = root.children.get(0);
                newRoot.left = this.encode(firstChild);
            }

            // Encoding the rest of the sibling nodes.
            TreeNode sibling = newRoot.left;
            for (int i = 1; i < root.children.size(); ++i) {
                sibling.right = this.encode(root.children.get(i));
                sibling = sibling.right;
            }

            return newRoot;
        }

        public Node decode(TreeNode root) {
            if (root == null) {
                return null;
            }

            Node newRoot = new Node(root.val, new ArrayList<>());

            // Decoding all the children nodes
            TreeNode sibling = root.left;
            while (sibling != null) {
                newRoot.children.add(this.decode(sibling));
                sibling = sibling.right;
            }

            return newRoot;
        }
    }

    // Definition for a N-Ary Node.
    static class Node {
        public int val;
        public List<Node> children;
        public Node() {}
        public Node(int _val) {
            val = _val;
        }
        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

}
