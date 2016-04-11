package lintcode;

import java.util.*;

public class Expression_Evalution {
	private int get(String a, Integer base) {
		if (a.equals("+") || a.equals("-"))
			return 1 + base;
		if (a.equals("*") || a.equals("/"))
			return 2 + base;

		return Integer.MAX_VALUE;
	}

	private double eval(ASTNode root) {
		if (root == null)
			return 0;
		switch (root.value) {
		case "+":
			return eval(root.left) + eval(root.right);
		case "-":
			return eval(root.left) - eval(root.right);
		case "*":
			return eval(root.left) * eval(root.right);
		case "/":
			return eval(root.left) / eval(root.right);
		default:
			return Double.parseDouble(root.value);
		}
	}

	public double evaluateExpression(ArrayList<String> expression) {
		Stack<ASTNode> stack = new Stack<ASTNode>();
		int weight = 0;
		Integer base = 0;
		for (int i = 0; i <= expression.size(); i++) {
			if (i != expression.size()) {
				if (expression.get(i).equals("(")) {
					base += 10;
					continue;
				}
				if (expression.get(i).equals(")")) {
					base -= 10;
					continue;
				}
				weight = get(expression.get(i), base);

			}
			ASTNode right;
			if (i == expression.size())
				right = new ASTNode(Integer.MIN_VALUE, "");
			else
				right = new ASTNode(weight, expression.get(i));
			
			// Key algorithm
			while (!stack.isEmpty()) {
				if (right.weight <= stack.peek().weight) {
					ASTNode nodeNow = stack.pop();
					if (stack.isEmpty()) {
						right.left = nodeNow;
					} else {
						ASTNode left = stack.peek();
						if (left.weight < right.weight) {
							right.left = nodeNow;
						} else {
							left.right = nodeNow;
						}
					}
				} else {
					break;
				}
			}
			stack.push(right);
		}
		return eval(stack.peek().left);
	}

	public double evaluateExpression(String expression) {
		ArrayList<String> exp = new ArrayList<>();
		int i = 0, j = 0;
		Set<Character> tokens = new HashSet<>();
		tokens.add('-');
		tokens.add('+');
		tokens.add('*');
		tokens.add('/');
		tokens.add('(');
		tokens.add(')');
		while (i < expression.length()) {
			if (expression.charAt(i) == ' ') {
				i++;
				continue;
			}
			j = i + 1;
			if (!tokens.contains(expression.charAt(i))) {
				while (j < expression.length()
						&& !tokens.contains(expression.charAt(j))) {
					j++;
				}
			}
			exp.add(expression.substring(i, j));
			i = j;
		}
		return evaluateExpression(exp);
	}

	public static void main(String[] args) {
		Expression_Evalution ee = new Expression_Evalution();
		System.out.println(ee.evaluateExpression("15/(1 + 2)"));
		System.out.println(ee.evaluateExpression("(15 - 1)/((1 + 2)*3)"));
	}

}
