package leetcode;
import java.util.*;

public class A133_Clone_Graph {
    Map<Integer, UndirectedGraphNode> created = new HashMap<>();
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        if (created.containsKey(node.label))
            return created.get(node.label);
        
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        created.put(newNode.label, newNode);
        for (UndirectedGraphNode neighbor : node.neighbors) {
            newNode.neighbors.add(cloneGraph(neighbor));
        }
        
        return newNode;
    }
}
