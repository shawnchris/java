package leetcode;
import java.util.*;

public class A399_Evaluate_Division {
	class Node {
		String name;
		List<Node> neighbor = new ArrayList<>();
		List<Double> dist = new ArrayList<>();
		public Node(String name) {
			this.name = name;
		}
	}
	Map<String, Node> map = new HashMap<>();
	public double[] calcEquation(String[][] equations, double[] values, String[][] query) {
		for (int i = 0; i < values.length; i++) {
			String x = equations[i][0];
	        String y = equations[i][1];
	        Node nx, ny;
	        if (!map.containsKey(x)) {
	        	nx = new Node(x);
	        }
	        else {
	        	nx = map.get(x);
	        }
	        if (!map.containsKey(y)) {
	        	ny = new Node(y);
	        }
	        else {
	        	ny = map.get(y);
	        }
	        nx.neighbor.add(ny);
	        nx.dist.add(values[i]);
	        ny.neighbor.add(nx);
	        ny.dist.add(1 / values[i]);
	        map.put(x, nx);
	        map.put(y, ny);
		}
		
		double[] result = new double[query.length];
        for (int i = 0; i < query.length; i++) {
        	String x = query[i][0];
    		String y = query[i][1];
    		if (map.containsKey(x) && map.containsKey(y)) {
    			result[i] = traverse(map.get(x), map.get(y), 1.0, new HashSet<Node>());
    		}
    		else {
    			result[i] = -1;
    		}
        }
        
        return result;
	}
	
	private double traverse(Node nx, Node ny, double res, HashSet<Node> visited) {
		// base case
		if (nx == ny) {
			return res;
		}
		visited.add(nx);
		for (int i = 0; i < nx.neighbor.size(); i++) {
			if (visited.contains(nx.neighbor.get(i))) continue;
			double result = traverse(nx.neighbor.get(i), ny, res * nx.dist.get(i), visited);
			if (result > 0) return result;
		}
		
		return -1.0;
	}
	public static void main(String[] args) {
		String[][] equations = {{"a", "b"}, {"b", "c"}};
		double[] values = {2.0, 3.0};
		String[][] query = {{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
		A399_Evaluate_Division ed = new A399_Evaluate_Division();
		double[] result = ed.calcEquation(equations, values, query);
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}

}
