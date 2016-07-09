package com.tableau;

import java.util.*;

class UndirectedGraphNode {
	int label;
	List<UndirectedGraphNode> neighbors;

	UndirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<UndirectedGraphNode>();
	}
};

public class Library {
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
    
    public boolean knows(int a, int b) {return false;}
    public int findCelebrity(int n) {
    	int a = 0, b = n - 1;
    	while (a < b) {
    		if (knows(a, b))
    			a++;
    		else
    			b--;
    	}
    	for (int i = 0; i < n; i++) {
    		if (i != a && (knows(a, i) || !knows(i, a)))
    			return -1;
    	}
    	return a;
    }
}
