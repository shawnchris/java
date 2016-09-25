package leetcode;

import java.util.*;

public class A406_Queue_Reconstruction_by_Height {
	private static class Node {
		Node left, right;
		int value;
		public final Person person;

		public Node(Person person) {
			this.value = 1;
			this.person = person;
		}
	}

	private static class Person {
		public final int height;
		public final int frontCount;

		Person(int height, int frontCount) {
			this.height = height;
			this.frontCount = frontCount;
		}
	}

	public int[][] reconstructQueue(int[][] people) {
		int n = people.length;
		int[][] result = new int[n][2];
		if (n == 0) return result;
		
		Person[] persons = new Person[n];

		for (int i = 0; i < n; i++)
			persons[i] = new Person(people[i][0], people[i][1]);

		// sort by height desc
		Arrays.sort(persons, (p1, p2) -> {
			if (p1.height != p2.height)
				return p2.height - p1.height;
			else
				return p1.frontCount - p2.frontCount;
		});

		Node root = new Node(persons[0]);
		for (int i = 1; i < n; i++) {
			insert(root, persons[i]);
		}
		
		inOrderTraverse(root, result, new int[1]);

		return result;
	}

	private void insert(Node root, Person p) {
		insert(root, p, p.frontCount);
	}

	private void insert(Node root, Person p, int value) {
		if (value < root.value) { // should insert to the left
			if (root.left == null) {
				root.left = new Node(p);
			} else {
				insert(root.left, p, value);
			}
			root.value++; // Increase the current node value while descending
							// left!
		} else { // insert to the right
			if (root.right == null) {
				root.right = new Node(p);
			} else {
				insert(root.right, p, value - root.value);
			}
		}
	}

	private void inOrderTraverse(Node root, int[][] result, int[] idx) {
		if (root == null) return;

		inOrderTraverse(root.left, result, idx);

		result[idx[0]][0] = root.person.height;
		result[idx[0]][1] = root.person.frontCount;
		idx[0]++;

		inOrderTraverse(root.right, result, idx);
	}

	
	private static void print(int[][] r) {
		for (int i = 0; i < r.length; i++) {
			System.out.println(r[i][0] + " " + r[i][1]);
		}
	}
	public static void main(String[] args) {
		A406_Queue_Reconstruction_by_Height rh = new A406_Queue_Reconstruction_by_Height();
		
		int[][] people1 = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
		print(rh.reconstructQueue(people1));
		
		System.out.println();
		
		int[][] people2 = {{9,0},{7,0},{1,9},{3,0},{2,7},{5,3},{6,0},{3,4},{6,2},{5,2}};
		print(rh.reconstructQueue(people2));
	}

}
