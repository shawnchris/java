package leetcode;

import java.util.ArrayList;
import java.util.List;

class Node {
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

public class A428_Serialize_Deserialize_Nary_Tree {
    static class WrappableInt {
        private int value;
        public WrappableInt(int x) {
            this.value = x;
        }
        public int getValue() {
            return this.value;
        }
        public void increment() {
            this.value++;
        }
    }

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        this.serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(Node root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        // Add the value of the node
        sb.append((char) (root.val + '0'));
        // Add the number of children
        sb.append((char) (root.children.size() + '0'));
        // Recurse on the subtrees and build the string accordingly
        for (Node child : root.children) {
            this.serializeHelper(child, sb);
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.isEmpty())
            return null;
        return this.deserializeHelper(data, new WrappableInt(0));
    }

    private Node deserializeHelper(String data, WrappableInt index) {
        if (index.getValue() == data.length()) {
            return null;
        }
        // The invariant here is that the "index" always points to a node and the value next to it represents the number
        // of children it has.
        Node node = new Node(data.charAt(index.getValue()) - '0', new ArrayList<>());
        index.increment();
        int numChildren = data.charAt(index.getValue()) - '0';
        for (int i = 0; i < numChildren; i++) {
            index.increment();
            node.children.add(this.deserializeHelper(data, index));
        }
        return node;
    }
}
